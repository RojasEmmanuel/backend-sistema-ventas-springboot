package com.rojasdev.supermercado.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SucursalResponse {
    private final Long id;
    private final String nombre;
    private final String direccion;
}
