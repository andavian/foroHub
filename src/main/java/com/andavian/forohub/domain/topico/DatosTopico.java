package com.andavian.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DatosTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        String status,
        @NotBlank
        String autor,
        @NotBlank
        String curso
) {
}
