package com.andavian.forohub.domain.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Table(name="topicos")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Topico {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
   private String titulo;
   private String mensaje;
   @Column(name = "fecha_de_creacion", insertable = false, updatable = false)
   private LocalDateTime fechaDeCreacion;
   private String status;
   private String autor;
   private String curso;
   private Boolean activo;


   public Topico(DatosTopico datosTopico) {
      this.activo = true;
      this.titulo = datosTopico.titulo();
      this.mensaje = datosTopico.mensaje();
      this.status = datosTopico.status();
      this.autor = datosTopico.autor();
      this.curso = datosTopico.curso();

   }

   public void actualizarDatos(DatosActualizarTopico datos) {
      if (datos.titulo() != null) {
         this.titulo = datos.titulo();
      }
      if (datos.mensaje() != null) {
         this.mensaje = datos.mensaje();
      }
   }

   public void archivarTopico() {
      this.activo = false;
   }
}
