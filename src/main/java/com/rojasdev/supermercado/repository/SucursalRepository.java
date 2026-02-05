package com.rojasdev.supermercado.repository;

import com.rojasdev.supermercado.entity.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends JpaRepository <Sucursal, Long>{
}
