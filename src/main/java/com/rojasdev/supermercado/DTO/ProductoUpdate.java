package com.rojasdev.supermercado.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ProductoUpdate {

    @NotNull(message = "El id del producto no debe ser nulo")
    private final Long id;

    @NotBlank
    @Length(min = 3, max = 50, message = "El nombre debe estar entre 3 y 50 caracteres")
    private final String nombre;

    @NotNull
    @DecimalMin("10.0")
    private final BigDecimal precioVenta;

    @NotNull
    @DecimalMin("5.0")
    private final BigDecimal precioCompra;

    @NotNull
    @Min(value = 1, message = "El stock es insuficiente")
    private final int stock;
}
