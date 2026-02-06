package com.rojasdev.supermercado.DTO;

import com.rojasdev.supermercado.domain.EstatusVenta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class VentaReponse {
    private String sucursal;
    private String direccionSucursal;

    private Long id;
    private String cliente;
    private LocalDateTime fecha;
    private EstatusVenta estatus;
    private List<ItemVentaResponse> listaVenta;
    private BigDecimal total;
}
