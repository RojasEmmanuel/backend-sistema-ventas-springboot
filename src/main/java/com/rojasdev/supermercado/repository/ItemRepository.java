package com.rojasdev.supermercado.repository;

import com.rojasdev.supermercado.entity.ItemVenta;
import com.rojasdev.supermercado.entity.Producto;
import com.rojasdev.supermercado.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemVenta, Long> {
    ItemVenta findByVentaAndProducto(Venta venta, Producto producto);
    List<ItemVenta> findByVenta(Venta venta);

    Long id(Long id);
}
