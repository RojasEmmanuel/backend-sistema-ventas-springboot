package com.rojasdev.supermercado.controller;

import com.rojasdev.supermercado.DTO.ProductoCreate;
import com.rojasdev.supermercado.DTO.ProductoResponse;
import com.rojasdev.supermercado.DTO.ProductoUpdate;
import com.rojasdev.supermercado.service.ProductoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/productos")
@AllArgsConstructor
public class ProductoController {

    //instancia de mi servicio
    private ProductoService servicio;

    @PostMapping
    public void registrarProducto(@Valid @RequestBody ProductoCreate dto){
        servicio.registrarProducto(dto);
    }

    @GetMapping
    public List<ProductoResponse> consultarProductos(){
        return servicio.getProductos();
    }

    @PatchMapping
    public void actualizarProducto(@Valid @RequestBody ProductoUpdate dto){
        servicio.actualizarProducto(dto);
    }

    @DeleteMapping("/{idProducto}")
    public void eliminarProducto(@PathVariable long idProducto){
        servicio.eliminarProducto(idProducto);
    }
}
