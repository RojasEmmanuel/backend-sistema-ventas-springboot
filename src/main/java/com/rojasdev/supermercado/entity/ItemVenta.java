package com.rojasdev.supermercado.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "itemVenta")
public class ItemVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cantidad;
    private BigDecimal subtotal;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    @JsonManagedReference
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_venta")
    @JsonBackReference
    private Venta venta;
}
