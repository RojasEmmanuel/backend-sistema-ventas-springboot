package com.rojasdev.supermercado.repository;

import com.rojasdev.supermercado.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
}
