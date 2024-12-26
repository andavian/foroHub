package com.andavian.forohub.domain.topico;


import java.time.LocalDateTime;

public record DatosResponseTopico(
        Long id,
        String titulo,
        String mensaje,
        String status,
        String autor,
        String curso,
        LocalDateTime fechaDeCreacion
) {
    // Constructor adicional que recibe un objeto Topico
    public DatosResponseTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso(),
                topico.getFechaDeCreacion()
        );
    }
}
