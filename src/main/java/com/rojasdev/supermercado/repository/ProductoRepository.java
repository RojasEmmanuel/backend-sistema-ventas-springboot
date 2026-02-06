package com.rojasdev.supermercado.repository;

import com.rojasdev.supermercado.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByActivo(boolean activo);

    Long id(Long id);
}
