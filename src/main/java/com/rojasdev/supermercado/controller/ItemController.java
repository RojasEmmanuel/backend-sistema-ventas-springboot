package com.rojasdev.supermercado.controller;

import com.rojasdev.supermercado.entity.ItemVenta;
import com.rojasdev.supermercado.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/item")
@AllArgsConstructor
public class ItemController {

    private ItemService service;

    @PostMapping("/{idProducto}/{idVenta}/{cantidad}")
    public void addItemVenta(@PathVariable long idProducto,
                             @PathVariable Long idVenta, @PathVariable int cantidad){
        service.addItemVenta(idProducto, idVenta, cantidad);
    }

    @GetMapping("/{idVenta}")
    public List<ItemVenta> getItemVenta(@PathVariable Long idVenta){
        return service.getCarrito(idVenta);
    }

    @DeleteMapping("/{idItem}")
    public void eliminarItemVenta(@PathVariable Long idItem){
        service.eliminarItemVenta(idItem);
    }
}
