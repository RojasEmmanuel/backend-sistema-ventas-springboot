package com.rojasdev.supermercado.service;

import com.rojasdev.supermercado.DTO.ProductoCreate;
import com.rojasdev.supermercado.DTO.ProductoResponse;
import com.rojasdev.supermercado.DTO.ProductoUpdate;
import com.rojasdev.supermercado.entity.Producto;
import com.rojasdev.supermercado.repository.ProductoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductoService {

    private final ProductoRepository repository;

    @Transactional
    public void registrarProducto(ProductoCreate dto){

        validarPrecios(dto.getPrecioVenta(), dto.getPrecioCompra());

        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setPrecioCompra(dto.getPrecioCompra());
        producto.setPrecioVenta(dto.getPrecioVenta());
        producto.setStock(dto.getStock());

        repository.save(producto);
    }


    public void validarPrecios(BigDecimal precioVenta, BigDecimal precioCompra){
        if(precioVenta.compareTo(precioCompra) < 1){
            throw new IllegalArgumentException("El precio de compra no puede ser mayor al de venta");
        }
    }

    @Transactional(readOnly = true)
    public List<ProductoResponse> getProductos(){

       return repository.findAll().stream().map(
            producto -> new ProductoResponse(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecioVenta(),
                producto.getStock()
            )
        ).toList();
    }


    @Transactional
    public void actualizarProducto(ProductoUpdate dto){

        Producto producto = repository.findById(dto.getId()).orElseThrow(
                ()->new IllegalArgumentException("Este producto no existe")
        );

        validarPrecios(dto.getPrecioVenta(), dto.getPrecioCompra());

        producto.setNombre(dto.getNombre());
        producto.setPrecioVenta(dto.getPrecioVenta());
        producto.setPrecioCompra(dto.getPrecioCompra());
        producto.setStock(dto.getStock());

        repository.save(producto);
    }

    public void eliminarProducto(Long idProducto){

        if(! repository.existsById(idProducto)){
            throw new IllegalArgumentException("No existe un producto con este ID");
        }

        repository.deleteById(idProducto);
    }
}
