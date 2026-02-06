package com.rojasdev.supermercado.controller;

import com.rojasdev.supermercado.DTO.VentaCreateDTO;
import com.rojasdev.supermercado.DTO.VentaReponse;
import com.rojasdev.supermercado.entity.Venta;
import com.rojasdev.supermercado.service.VentaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@AllArgsConstructor
public class VentaController {

    private VentaService service;

    @PostMapping("/{idSucursal}")
    public void empezarVenta(@PathVariable Long idSucursal){
        service.empezarVenta(idSucursal);
    }

    @GetMapping("/{idSucursal}")
    public List<Venta> getVentasSucursal(@PathVariable Long idSucursal){
        return service.consultarVentas(idSucursal);
    }

    @PatchMapping
    public VentaReponse finalizarVenta(@Valid @RequestBody VentaCreateDTO dto){
        return service.finalizarVenta(dto);
    }

    @GetMapping("/consultar/{idVenta}")
    public VentaReponse consultarVenta(@PathVariable Long idVenta){
        return  service.consultarVenta(idVenta);
    }

}
