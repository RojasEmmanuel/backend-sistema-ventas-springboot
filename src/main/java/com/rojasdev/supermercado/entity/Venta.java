package com.rojasdev.supermercado.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rojasdev.supermercado.domain.EstatusCompra;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaVenta;
    private BigDecimal total;
    private EstatusCompra estatus;

    @Column(name = "nombre_cliente")
    private String nombreCliente;

    @Column(name = "apellidos_cliente")
    private String apellidosCliente;

    @ManyToOne
    @JsonBackReference
    private Sucursal sucursal;

    @OneToMany(mappedBy = "venta")
    private List<ItemVenta> carrito;


    @PrePersist
    public void prepersist(){
        this.estatus = EstatusCompra.ACTIVA;
        this.total = BigDecimal.ZERO;
        fechaVenta = LocalDateTime.now();
    }
}
