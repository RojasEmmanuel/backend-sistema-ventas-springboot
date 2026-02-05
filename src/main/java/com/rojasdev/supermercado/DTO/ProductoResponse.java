package com.rojasdev.supermercado.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ProductoResponse {

    private final Long id;
    private final String nombre;
    private final BigDecimal precioVenta;
    private final int stock;
}
