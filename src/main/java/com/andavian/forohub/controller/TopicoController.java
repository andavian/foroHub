package com.andavian.forohub.controller;

import com.andavian.forohub.Exceptions.ResourceNotFoundException;
import com.andavian.forohub.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
@Validated
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public void crearTopico (@RequestBody @Valid DatosTopico datosTopico){

        topicoRepository.save(new Topico(datosTopico));
    }

    @GetMapping(path = {"/", ""})
    public Page<DatosListadoTopico> listaDeTopicos
            (@PageableDefault(page = 0, size = 10, sort = {"fechaDeCreacion"}) Pageable paginacion){

        return topicoRepository.findByActivoTrue(paginacion).map(DatosListadoTopico::new);
    }

    @GetMapping("/{id}")
    public DatosListadoTopico detalleTopico(
            @PathVariable
            @NotNull(message = "El ID no puede ser nulo")
            @Positive(message = "El ID debe ser mayor a 0")
            Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tópico no encontrado con id: " + id));
        return new DatosListadoTopico(topico);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> actualizarTopico(
            @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        // Buscar el tópico en la base de datos
        Topico topico = topicoRepository.findById(datosActualizarTopico.id())
                .orElseThrow(() -> new ResourceNotFoundException("Tópico no encontrado con id: " + datosActualizarTopico.id()));

        // Actualizar los datos
        topico.actualizarDatos(datosActualizarTopico);

        // Devolver respuesta exitosa
        return ResponseEntity.ok("Tópico actualizado correctamente");
    }


    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarTopico(
            @PathVariable
            @NotNull(message = "El ID no puede ser nulo")
            @Positive(message = "El ID debe ser mayor a 0")
            Long id){
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tópico no encontrado con id: " + id));
        topico.archivarTopico();
    }
}
