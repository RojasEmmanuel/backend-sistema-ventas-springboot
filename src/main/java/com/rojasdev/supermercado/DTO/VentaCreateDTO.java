package com.rojasdev.supermercado.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
public class VentaCreateDTO {
    @NotNull
    private final Long id;
    @NotBlank
    @Length(min = 2, max = 50)
    private final String nombreCliente;
    @NotBlank
    @Length(min = 2, max = 100)
    private final String apellidosCliente;
}
