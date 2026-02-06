package com.rojasdev.supermercado.service;

import com.rojasdev.supermercado.DTO.ItemVentaResponse;
import com.rojasdev.supermercado.DTO.VentaCreateDTO;
import com.rojasdev.supermercado.DTO.VentaReponse;
import com.rojasdev.supermercado.domain.EstatusVenta;
import com.rojasdev.supermercado.entity.Sucursal;
import com.rojasdev.supermercado.entity.Venta;
import com.rojasdev.supermercado.repository.SucursalRepository;
import com.rojasdev.supermercado.repository.VentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class VentaService {

    private final VentaRepository repository;
    private final SucursalRepository sucursalRepository;

    @Transactional(readOnly = true)
    public List<Venta> consultarVentas(Long idSucursal){
        Sucursal sucursal = sucursalRepository.findById(idSucursal).orElseThrow(
                ()->new RuntimeException("Este sucursal no existe")
        );

        return repository.findBySucursal(sucursal);
    }

    @Transactional
    public void empezarVenta(Long idSucursal){
        Sucursal sucursal = sucursalRepository.findById(idSucursal).orElseThrow(
                ()->new RuntimeException("Este sucursal no existe")
        );

        Venta venta = new Venta();
        venta.setSucursal(sucursal);
        repository.save(venta);
    }

    public VentaReponse finalizarVenta(VentaCreateDTO dto){
        Venta venta = repository.findById(dto.getId()).orElseThrow(
                () -> new IllegalArgumentException("Esta venta no existe")
        );

        if( ! venta.getEstatus().equals(EstatusVenta.ACTIVA)){
            throw new IllegalStateException("No se ha podido finalizar esta venta");
        }

        venta.setNombreCliente(dto.getNombreCliente());
        venta.setApellidosCliente(dto.getApellidosCliente());
        venta.setFechaVenta(LocalDateTime.now());
        venta.setEstatus(EstatusVenta.FINALIZADA);
        repository.save(venta);

        return consultarVenta(venta.getId());
    }

    public VentaReponse consultarVenta(Long idVenta){
        Venta venta = repository.findById(idVenta).orElseThrow(
                ()->new IllegalArgumentException("Esta venta no existe")
        );

        VentaReponse ventaReponse = new VentaReponse();
        ventaReponse.setSucursal(venta.getSucursal().getNombre());

        ventaReponse.setDireccionSucursal(venta.getSucursal().getCalle()+", col. "+
                venta.getSucursal().getColonia()+", "+venta.getSucursal().getCiudad()+":"+
                venta.getSucursal().getCp()
        );

        ventaReponse.setCliente(venta.getNombreCliente()+" "+venta.getApellidosCliente());
        ventaReponse.setId(venta.getId());
        List<ItemVentaResponse> listaVenta = venta.getCarrito().stream()
                .map(itemVenta -> new ItemVentaResponse(
                        itemVenta.getId(),
                        itemVenta.getProducto().getNombre(),
                        itemVenta.getCantidad(),
                        itemVenta.getSubtotal()
                )
        ).toList();

        ventaReponse.setListaVenta(listaVenta);
        ventaReponse.setFecha(venta.getFechaVenta());
        ventaReponse.setTotal(venta.getTotal());
        ventaReponse.setEstatus(venta.getEstatus());
        return ventaReponse;
    }

}