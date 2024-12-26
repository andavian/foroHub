package com.andavian.forohub.controller;

import com.andavian.forohub.domain.topico.*;
import com.andavian.forohub.infra.errors.ResourceNotFoundException;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@Validated
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<DatosResponseTopico> crearTopico (
            @RequestBody
            @Valid
            DatosTopico datosTopico,
            UriComponentsBuilder uriComponentsBuilder){

        Topico topico = topicoRepository.save(new Topico(datosTopico));

        // Construir la URL del recurso creado
        URI url = uriComponentsBuilder
                .path("/medicos/{id}")
                .buildAndExpand(topico.getId())
                .toUri();

        // Retornar la respuesta con código 201 (Created)
        return ResponseEntity.created(url).body(new DatosResponseTopico(topico));

    }

    @GetMapping(path = {"/", ""})
    public ResponseEntity<Page<DatosListadoTopico>> listaDeTopicos
            (@PageableDefault(page = 0, size = 10, sort = {"fechaDeCreacion"}) Pageable paginacion){

        return ResponseEntity.ok(topicoRepository.findByActivoTrue(paginacion).map(DatosListadoTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosResponseTopico> detalleTopico(
            @PathVariable
            @NotNull(message = "El ID no puede ser nulo")
            @Positive(message = "El ID debe ser mayor a 0")
            Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tópico no encontrado con id: " + id));
        return ResponseEntity.ok(new DatosResponseTopico(topico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosResponseTopico> actualizarTopico(
            @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        // Buscar el tópico en la base de datos
        Topico topico = topicoRepository.findById(datosActualizarTopico.id())
                .orElseThrow(() -> new ResourceNotFoundException("Tópico no encontrado con id: " + datosActualizarTopico.id()));

        // Actualizar los datos
        topico.actualizarDatos(datosActualizarTopico);

        // Devolver respuesta exitosa
        return ResponseEntity.ok(new DatosResponseTopico(topico));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosResponseTopico> eliminarTopico(
            @PathVariable
            @NotNull(message = "El ID no puede ser nulo")
            @Positive(message = "El ID debe ser mayor a 0")
            Long id){
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tópico no encontrado con id: " + id));
        topico.archivarTopico();
        return ResponseEntity.noContent().build();
    }


}
