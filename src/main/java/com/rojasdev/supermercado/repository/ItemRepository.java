package com.rojasdev.supermercado.repository;

import com.rojasdev.supermercado.entity.ItemVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemVenta, Long> {
}
