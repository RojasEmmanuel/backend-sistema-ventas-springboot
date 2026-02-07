package com.rojasdev.supermercado.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
public class SucursalCreate {
    @NotBlank
    @Length(min = 3, max = 50)
    private final String nombre;
    @Length(min=4)
    private final String calle;
    @Length(min=4)
    private final String colonia;
    @Length(min=4)
    private final String ciudad;
    @Length(min=4)
    private final String cp;
}
