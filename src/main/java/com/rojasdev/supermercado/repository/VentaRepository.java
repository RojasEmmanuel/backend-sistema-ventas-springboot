package com.rojasdev.supermercado.repository;

import com.rojasdev.supermercado.entity.Sucursal;
import com.rojasdev.supermercado.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findBySucursal(Sucursal sucursal);
}
