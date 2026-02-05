package com.rojasdev.supermercado.controller;

import com.rojasdev.supermercado.DTO.SucursalCreate;
import com.rojasdev.supermercado.DTO.SucursalResponse;
import com.rojasdev.supermercado.entity.Sucursal;
import com.rojasdev.supermercado.service.SucursalService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/sucursales")
public class SucursalController {

    private SucursalService service;

    @GetMapping
    public List<SucursalResponse> listarSucursales(){
        return service.getSucursales();
    }

    @PostMapping
    public void crearSucursal(@Valid @RequestBody SucursalCreate dto){
        service.registrarSucursal(dto);
    }

    @DeleteMapping("/{idSucursal}")
    public  void eliminarSucursal(@PathVariable Long idSucursal){
        service.eliminarSucursal(idSucursal);
    }

}
