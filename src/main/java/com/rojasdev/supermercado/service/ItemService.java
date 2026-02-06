package com.rojasdev.supermercado.service;

import com.rojasdev.supermercado.domain.EstatusVenta;
import com.rojasdev.supermercado.entity.ItemVenta;
import com.rojasdev.supermercado.entity.Producto;
import com.rojasdev.supermercado.entity.Venta;
import com.rojasdev.supermercado.repository.ItemRepository;
import com.rojasdev.supermercado.repository.ProductoRepository;
import com.rojasdev.supermercado.repository.VentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository repository;
    private final ProductoRepository productoRepository;
    private final VentaRepository ventaRepository;

    @Transactional
    public void addItemVenta(Long idProducto, Long idVenta, int cantidad){
        Venta venta = getVenta(idVenta);
        Producto producto = getProducto(idProducto, cantidad);

        ItemVenta item = repository.findByVentaAndProducto(venta, producto);

        if(item == null){
            item = new ItemVenta();
            item.setCantidad(cantidad);
            item.setProducto(producto);
            item.setVenta(venta);
            calcularSubtotal(item);
        }else{
            item.setCantidad(item.getCantidad() + cantidad);
            calcularSubtotal(item);
        }

        producto.setStock(producto.getStock() - cantidad);
        productoRepository.save(producto);

        repository.save(item);
        recalcularTotalVenta(venta);
    }

    @Transactional(readOnly = true)
    public List<ItemVenta> getCarrito(Long idVenta){
        Venta venta = ventaRepository.findById(idVenta).orElseThrow(
                ()-> new IllegalArgumentException("Esta venta no existe")
        );

        return repository.findByVenta(venta);
    }

    @Transactional
    public void eliminarItemVenta(Long idItem){
        ItemVenta item = repository.findById(idItem).orElseThrow(
                ()-> new IllegalArgumentException("Este item no existe")
        );

        Venta venta = item.getVenta();

        if(! venta.getEstatus().equals(EstatusVenta.ACTIVA)){
            throw new IllegalStateException("Esta venta ya no es editable");
        }
        Producto producto = item.getProducto();
        producto.setStock(producto.getStock() + item.getCantidad());
        productoRepository.save(producto);

        repository.delete(item);

        recalcularTotalVenta(venta);
    }

    private void recalcularTotalVenta(Venta venta){
        BigDecimal total = venta.getCarrito().stream().map(
            ItemVenta::getSubtotal
        ).reduce(BigDecimal.ZERO, BigDecimal::add);

        venta.setTotal(total);
        ventaRepository.save(venta);
    }

    private void calcularSubtotal(ItemVenta itemVenta){
        itemVenta.setSubtotal(
                itemVenta.getProducto().getPrecioVenta().multiply(
                        BigDecimal.valueOf(itemVenta.getCantidad())
                )
        );
    }

    private Producto getProducto(Long idProducto, int cantidad){
        Producto producto = productoRepository.findById(idProducto).orElseThrow(
                ()->new IllegalArgumentException("Este producto no existe")
        );

        if(!producto.isActivo()){
            throw new IllegalStateException("Este producto no se encuentra disponible para comercializacion");
        }

        if(producto.getStock() < cantidad){
            throw new IllegalStateException("Producto con stock insuficiente");
        }

        return producto;
    }

    private Venta getVenta(Long idVenta){
        Venta venta = ventaRepository.findById(idVenta).orElseThrow(
                ()-> new IllegalArgumentException("Esta venta no existe")
        );

        if(!venta.getEstatus().equals(EstatusVenta.ACTIVA)){
            throw new IllegalStateException("Esta venta ya ha finalizado o se ha cancelado");
        }

        return venta;
    }
}
