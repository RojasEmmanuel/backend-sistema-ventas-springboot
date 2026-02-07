package com.rojasdev.supermercado.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sucursales")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String calle;
    private String colonia;
    private String ciudad;
    private String cp;

    private String operacion;
    private LocalDateTime ultimaModificacion;

    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Venta> ventas;

    @PrePersist
    public void prepersist(){
        this.operacion = "Creado";
        this.ultimaModificacion = LocalDateTime.now();
    }

    @PreUpdate
    public void preupdate(){
        this.operacion = "Actualizado";
        this.ultimaModificacion = LocalDateTime.now();
    }
}
