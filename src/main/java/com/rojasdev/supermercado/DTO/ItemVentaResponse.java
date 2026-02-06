package com.rojasdev.supermercado.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ItemVentaResponse {

    private final Long id;
    private final String producto;
    private final int cantidad;
    private final BigDecimal subtotal;
}
