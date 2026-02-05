package com.rojasdev.supermercado.service;

import com.rojasdev.supermercado.entity.Sucursal;
import com.rojasdev.supermercado.entity.Venta;
import com.rojasdev.supermercado.repository.SucursalRepository;
import com.rojasdev.supermercado.repository.VentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class VentaService {

    private final VentaRepository repository;
    private final SucursalRepository sucursalRepository;

    public List<Venta> consultarVentas(Long idSucursal){
        Sucursal sucursal = sucursalRepository.findById(idSucursal).orElseThrow(
                ()->new RuntimeException("Este sucursal no existe")
        );

        return repository.findBySucursal(sucursal);
    }
}
