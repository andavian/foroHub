# ForoHub API

## Descripción
Esta API gestiona los tópicos de un foro, permitiendo su creación, consulta, actualización y eliminación. Está desarrollada en Java con Spring Boot y sigue principios RESTful.

## Endpoints

### Crear un Tópico

**Descripción:** Permite crear un nuevo tópico.

- **URL:** `/topicos`
- **Método:** `POST`
- **Request Body:**
  ```json
  {
    "titulo": "Título del tópico",
    "mensaje": "Mensaje del tópico",
    "autor": "Nombre del autor",
    "curso": "Curso relacionado"
  }
  ```
- **Respuesta:** `200 OK` (si la creación es exitosa)

### Listar Tópicos

**Descripción:** Recupera una lista paginada de tópicos activos.

- **URL:** `/topicos`
- **Método:** `GET`
- **Parámetros opcionales:**
    - `page`: Número de página (por defecto: 0).
    - `size`: Tamaño de la página (por defecto: 10).
    - `sort`: Campo de ordenamiento (por defecto: `fechaDeCreacion`).
- **Respuesta:**
  ```json
  {
    "content": [
      {
        "id": 1,
        "titulo": "Título",
        "mensaje": "Mensaje",
        "autor": "Autor",
        "curso": "Curso",
        "fechaDeCreacion": "2024-12-22T15:11:43.765+00:00"
      }
    ],
    "pageable": {...},
    "totalPages": 1,
    "totalElements": 1
  }
  ```

### Obtener Detalles de un Tópico

**Descripción:** Recupera los detalles de un tópico específico por su ID.

- **URL:** `/topicos/{id}`
- **Método:** `GET`
- **Parámetros de ruta:**
    - `id`: ID del tópico (debe ser mayor a 0 y no nulo).
- **Respuesta:**
  ```json
  {
    "id": 1,
    "titulo": "Título",
    "mensaje": "Mensaje",
    "autor": "Autor",
    "curso": "Curso",
    "fechaDeCreacion": "2024-12-22T15:11:43.765+00:00"
  }
  ```
- **Errores:**
    - `404 Not Found`: Si el ID no existe.

### Actualizar un Tópico

**Descripción:** Permite actualizar los datos de un tópico existente.

- **URL:** `/topicos`
- **Método:** `PUT`
- **Request Body:**
  ```json
  {
    "id": 1,
    "titulo": "Nuevo Título",
    "mensaje": "Nuevo Mensaje"
  }
  ```
- **Respuesta:**
  ```json
  {
    "mensaje": "Tópico actualizado correctamente"
  }
  ```
- **Errores:**
    - `404 Not Found`: Si el ID no existe.

### Eliminar un Tópico

**Descripción:** Archiva un tópico marcándolo como inactivo.

- **URL:** `/topicos/{id}`
- **Método:** `DELETE`
- **Parámetros de ruta:**
    - `id`: ID del tópico (debe ser mayor a 0 y no nulo).
- **Respuesta:** `204 No Content` (si la eliminación es exitosa).
- **Errores:**
    - `404 Not Found`: Si el ID no existe.

## Manejo de Errores

### Formato de Respuesta de Error

- **Validación:**
  ```json
  {
    "error": "Error de validación",
    "detalle": "El ID no puede ser nulo"
  }
  ```
- **Recurso no encontrado:**
  ```json
  {
    "error": "Recurso no encontrado",
    "detalle": "Tópico no encontrado con id: {id}"
  }
  ```

## Clases y Componentes

### TopicoController

Maneja las solicitudes relacionadas con los tópicos. Los métodos principales incluyen:

- `crearTopico`: Crea un nuevo tópico.
- `listaDeTopicos`: Lista los tópicos activos con paginación.
- `detalleTopico`: Recupera los detalles de un tópico específico.
- `actualizarTopico`: Actualiza los datos de un tópico existente.
- `eliminarTopico`: Archiva un tópico existente.

### Validaciones

- `@NotNull`: Valida que un campo no sea nulo.
- `@Positive`: Valida que un número sea mayor a 0.
- `@Valid`: Valida un objeto completo según las reglas definidas.

### Excepciones Personalizadas

- `ResourceNotFoundException`: Excepción personalizada para manejar recursos no encontrados. Devuelve un error `404 Not Found`.

## Configuración

### application.properties

Incluye configuraciones como:
```properties
spring.mvc.trailing-slash-match=true
```
Esto permite manejar rutas con y sin barra inclinada al final.

## Requisitos

- Java 17 o superior
- Spring Boot 3.x
- Base de datos configurada (ej. MySQL, PostgreSQL)
- Dependencias de Maven:
    - Spring Web
    - Spring Data JPA
    - Base de datos H2 (o la que elijas)

## Instrucciones para Ejecutar

1. Clona el repositorio.
2. Configura la base de datos en `application.properties`.
3. Ejecuta la aplicación con tu IDE o usando Maven:
   ```sh
   mvn spring-boot:run
   ```
4. Accede a los endpoints desde un cliente como Postman o tu navegador.

---

Si necesitas soporte adicional, no dudes en contactarnos.

