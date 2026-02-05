package com.rojasdev.supermercado.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productos")

public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String nombre;
    private BigDecimal precioVenta;
    private BigDecimal precioCompra;
    private int stock;

    private boolean activo;
    private String operacion;
    private LocalDateTime ultimaModificacion;


    @OneToMany(mappedBy = "producto")
    @JsonBackReference
    private List<ItemVenta> ventas;

    @PrePersist
    public void prepersist(){
        this.activo = true;
        this.operacion = "Creado";
        this.ultimaModificacion = LocalDateTime.now();
    }

    @PreUpdate
    public void preupdate(){
        if(stock == 0){
            this.activo = false;
        }

        this.operacion = "Actualizado";
        this.ultimaModificacion = LocalDateTime.now();
    }
}
