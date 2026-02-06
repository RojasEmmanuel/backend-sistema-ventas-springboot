package com.rojasdev.supermercado.DTO;

import com.rojasdev.supermercado.domain.EstatusVenta;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class VentaResponseGral {

    private final Long id;
    private final String sucursal;
    private final LocalDate fecha;
    private final EstatusVenta estatus;
    private final String cliente;
    private final BigDecimal total;
}
