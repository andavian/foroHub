package com.andavian.forohub.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DatosActualizarTopico(
        @NotNull(message = "El ID no puede ser nulo")
        @Positive(message = "El ID debe ser mayor a 0")
        Long id,
        String titulo,
        String mensaje
        ) {
}
