# Sistema MVP de Préstamo de Libros - Biblioteca

Este proyecto corresponde a un Producto Mínimo Viable (MVP) para apoyar el proceso de préstamo de libros en una biblioteca.  
El sistema permite registrar préstamos de libros y gestionar una entidad principal del dominio: los libros.

## 1. Descripción general

El MVP busca representar de forma sencilla una solución para el proceso de préstamo de libros, permitiendo:

- Registrar préstamos.
- Validar usuarios existentes.
- Validar libros existentes.
- Verificar disponibilidad de libros.
- Cambiar el estado de un libro a PRESTADO cuando se registra un préstamo.
- Gestionar libros mediante operaciones CRUD.

La persistencia se realiza mediante archivos CSV para mantener una implementación simple y fácil de ejecutar.

## 2. Funcionalidades implementadas

### Transacción de negocio

La transacción principal implementada es:

**Registrar préstamo de libro**

Esta transacción permite asociar un usuario con un libro disponible, registrar la fecha de préstamo, calcular una fecha estimada de devolución y actualizar el estado del libro.

### CRUD de entidad del dominio

La entidad seleccionada para el CRUD es:

**Libro**

Operaciones disponibles:

- Listar libros.
- Crear libro.
- Actualizar libro.
- Eliminar libro.

## 3. Tecnologías utilizadas

- Java
- Maven
- Persistencia en archivos CSV
- Ejecución por consola

## 4. Estructura del proyecto

```text
biblioteca-mvp/
│
├── pom.xml
├── README.md
│
├── data/
│   ├── libros.csv
│   ├── usuarios.csv
│   └── prestamos.csv
│
└── src/
    └── main/
        └── java/
            └── com/
                └── biblioteca/
                    ├── Main.java
                    │
                    ├── model/
                    │   ├── EstadoLibro.java
                    │   ├── Libro.java
                    │   ├── Usuario.java
                    │   └── Prestamo.java
                    │
                    ├── repository/
                    │   ├── LibroRepository.java
                    │   ├── UsuarioRepository.java
                    │   └── PrestamoRepository.java
                    │
                    └── service/
                        ├── LibroService.java
                        └── PrestamoService.java