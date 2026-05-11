# Sistema MVP de Préstamo de Libros - Biblioteca

Producto Mínimo Viable desarrollado en Java para apoyar el proceso de préstamo de libros en una biblioteca.  
El sistema permite registrar préstamos de libros y gestionar libros mediante operaciones CRUD, usando persistencia simple en archivos CSV.

---

## 1. Instrucciones para ejecutar el proyecto

### 1.1. Requisitos previos

Para ejecutar el proyecto se necesita tener instalado:

- Java 17 o superior.
- Maven.
- Visual Studio Code o cualquier editor compatible con Java.

Para verificar las versiones instaladas:

```bash
java -version
mvn -version
```

---

### 1.2. Clonar o descargar el proyecto

Si el proyecto está en GitHub, se puede clonar con:

```bash
git clone https://github.com/juan07barrero/biblioteca-mvp.git
```

Luego entrar a la carpeta del proyecto:

```bash
cd biblioteca-mvp
```

Si el proyecto se entrega comprimido, simplemente se debe descomprimir y abrir la carpeta `biblioteca-mvp`.

---

### 1.3. Compilar el proyecto

Desde la raíz del proyecto, donde se encuentra el archivo `pom.xml`, ejecutar:

```bash
mvn clean package
```

Si todo está correcto, Maven debe mostrar:

```text
BUILD SUCCESS
```

---

### 1.4. Ejecutar el proyecto

Después de compilar, ejecutar:

```bash
mvn exec:java
```

El sistema mostrará un menú por consola con las opciones principales:

```text
====================================
     SISTEMA MVP BIBLIOTECA
====================================
1. Registrar préstamo
2. Gestionar libros
3. Listar préstamos
0. Salir
====================================
```

---

## 2. Usuarios de prueba demo

El sistema no cuenta con autenticación, ya que es un MVP sencillo ejecutado por consola.  
Sin embargo, para registrar préstamos se deben usar usuarios existentes en el archivo `data/usuarios.csv`.

Usuarios disponibles para pruebas:

| ID | Nombre | Correo |
|---|---|---|
| 1 | Ana Pérez | ana@demo.com |
| 2 | Carlos Gómez | carlos@demo.com |
| 3 | Laura Martínez | laura@demo.com |
| 4 | Juan Rodríguez | juan@demo.com |
| 5 | Sofía Torres | sofia@demo.com |

Ejemplo para probar la transacción:

```text
ID usuario: 1
ID libro: 1
```

Si el libro está disponible, el sistema registrará el préstamo y cambiará el estado del libro a `PRESTADO`.

---

## 3. Descripción general del proyecto

Este proyecto corresponde a la implementación de un Producto Mínimo Viable relacionado con el proceso de préstamo de libros en una biblioteca.

El objetivo principal es representar una solución sencilla que permita:

- Registrar préstamos de libros.
- Validar la existencia de usuarios.
- Validar la existencia de libros.
- Verificar la disponibilidad de los libros.
- Cambiar el estado de un libro cuando es prestado.
- Gestionar libros mediante operaciones CRUD.
- Mantener datos mínimos de prueba usando archivos CSV.

La aplicación fue desarrollada de manera simple para facilitar su comprensión, ejecución y sustentación.

---

## 4. Alcance del MVP

El alcance de esta versión incluye dos funcionalidades principales solicitadas para la entrega:

### 4.1. Transacción de negocio

La transacción de negocio implementada es:

**Registrar préstamo de libro**

Esta transacción representa la entrega de valor principal del sistema, ya que permite que un usuario pueda tomar prestado un libro disponible.

El sistema realiza las siguientes validaciones:

1. Verifica que el usuario exista.
2. Verifica que el libro exista.
3. Verifica que el libro esté disponible.
4. Registra el préstamo.
5. Cambia el estado del libro a `PRESTADO`.
6. Guarda los cambios en los archivos CSV.

---

### 4.2. CRUD de una entidad del dominio

La entidad seleccionada para el CRUD es:

**Libro**

Operaciones implementadas:

- Listar libros.
- Crear libro.
- Actualizar libro.
- Eliminar libro.

Esta entidad fue seleccionada porque es una de las más importantes dentro del dominio de la biblioteca y está directamente relacionada con la transacción de préstamo.

---

## 5. Tecnologías utilizadas

El proyecto utiliza tecnologías libres y sencillas de ejecutar:

- Java.
- Maven.
- Persistencia en archivos CSV.
- Ejecución por consola.
- Visual Studio Code como entorno de desarrollo sugerido.

---

## 6. Estructura del proyecto

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
```

---

## 7. Explicación de la arquitectura

El proyecto está organizado por capas para separar responsabilidades y facilitar el mantenimiento.

### 7.1. Capa `model`

Contiene las clases que representan las entidades principales del dominio:

- `Libro`
- `Usuario`
- `Prestamo`
- `EstadoLibro`

Estas clases representan los elementos principales identificados en el análisis del sistema.

---

### 7.2. Capa `repository`

Contiene las clases encargadas de leer y guardar información en archivos CSV:

- `LibroRepository`
- `UsuarioRepository`
- `PrestamoRepository`

Esta capa funciona como una persistencia simple, evitando la necesidad de instalar o configurar una base de datos.

---

### 7.3. Capa `service`

Contiene la lógica de negocio del sistema:

- `LibroService`
- `PrestamoService`

Aquí se implementan las validaciones, reglas y operaciones principales del MVP.

---

### 7.4. Clase `Main`

Contiene el menú por consola que permite probar las funcionalidades principales del sistema.

Desde esta clase se puede:

- Registrar préstamos.
- Gestionar libros.
- Listar préstamos registrados.

---

## 8. Datos mínimos de prueba

El proyecto incluye datos iniciales en la carpeta `data`.

### 8.1. Archivo `libros.csv`

Contiene los libros registrados inicialmente.

Campos:

```text
id,titulo,autor,categoria,estado
```

Estados posibles:

```text
DISPONIBLE
PRESTADO
```

El archivo incluye 10 libros iniciales para realizar pruebas.

---

### 8.2. Archivo `usuarios.csv`

Contiene los usuarios disponibles para registrar préstamos.

Campos:

```text
id,nombre,correo
```

El archivo incluye 5 usuarios de prueba.

---

### 8.3. Archivo `prestamos.csv`

Contiene los préstamos registrados.

Campos:

```text
id,idUsuario,idLibro,fechaPrestamo,fechaDevolucionEstimada,estado
```

Estados usados para préstamos:

```text
ACTIVO
```

El archivo incluye préstamos iniciales para validar la persistencia del sistema.

---

## 9. Flujo de la transacción de préstamo

El registro de préstamo funciona de la siguiente manera:

1. El usuario selecciona la opción `Registrar préstamo`.
2. El sistema muestra la lista de libros.
3. El usuario ingresa el ID del usuario.
4. El usuario ingresa el ID del libro.
5. El sistema busca el usuario en `usuarios.csv`.
6. El sistema busca el libro en `libros.csv`.
7. El sistema valida que el libro esté en estado `DISPONIBLE`.
8. El sistema crea un nuevo préstamo.
9. El sistema guarda el préstamo en `prestamos.csv`.
10. El sistema actualiza el estado del libro a `PRESTADO`.
11. El sistema guarda los cambios en `libros.csv`.
12. El sistema muestra un mensaje de confirmación.

---

## 10. Flujo del CRUD de libros

El CRUD de libros permite gestionar la entidad principal seleccionada para la entrega.

### 10.1. Listar libros

Muestra todos los libros registrados en el archivo `libros.csv`.

### 10.2. Crear libro

Permite registrar un nuevo libro con:

- Título.
- Autor.
- Categoría.

El sistema asigna automáticamente un nuevo ID y el estado inicial `DISPONIBLE`.

### 10.3. Actualizar libro

Permite modificar:

- Título.
- Autor.
- Categoría.
- Estado.

### 10.4. Eliminar libro

Permite eliminar un libro existente a partir de su ID.

---

## 11. Trazabilidad desde modelos hasta código

La trazabilidad se evidencia relacionando los elementos del análisis con su implementación en código.

| Elemento del análisis | Implementación en código |
|---|---|
| Libro | `model/Libro.java` |
| Usuario | `model/Usuario.java` |
| Préstamo | `model/Prestamo.java` |
| Estado del libro | `model/EstadoLibro.java` |
| Registro de préstamo | `service/PrestamoService.java` |
| CRUD de libros | `service/LibroService.java` |
| Persistencia de libros | `repository/LibroRepository.java` |
| Persistencia de usuarios | `repository/UsuarioRepository.java` |
| Persistencia de préstamos | `repository/PrestamoRepository.java` |
| Menú de interacción | `Main.java` |
| Datos mínimos de prueba | Carpeta `data/` |

---

## 12. Relación con el proceso de biblioteca

El sistema se relaciona directamente con el proceso de préstamo de libros, ya que permite controlar:

- Qué libros están disponibles.
- Qué libros se encuentran prestados.
- Qué usuario realizó un préstamo.
- Cuándo se realizó el préstamo.
- Cuál es la fecha estimada de devolución.
- Qué registros quedan almacenados para consulta posterior.

De esta manera, el MVP ayuda a reducir problemas como falta de control, pérdida de trazabilidad y dificultad para consultar el historial de préstamos.

---

## 13. Menú principal del sistema

Al ejecutar la aplicación se muestra el siguiente menú:

```text
====================================
     SISTEMA MVP BIBLIOTECA
====================================
1. Registrar préstamo
2. Gestionar libros
3. Listar préstamos
0. Salir
====================================
```

### Opción 1: Registrar préstamo

Corresponde a la transacción de negocio principal.

### Opción 2: Gestionar libros

Permite acceder al CRUD de libros.

### Opción 3: Listar préstamos

Permite consultar los préstamos registrados.

### Opción 0: Salir

Finaliza la ejecución del sistema.

---

## 14. Reglas de negocio implementadas

El sistema implementa las siguientes reglas de negocio:

1. No se puede registrar un préstamo si el usuario no existe.
2. No se puede registrar un préstamo si el libro no existe.
3. No se puede prestar un libro que ya se encuentra en estado `PRESTADO`.
4. Todo libro nuevo se crea inicialmente con estado `DISPONIBLE`.
5. Cuando se registra un préstamo, el libro cambia automáticamente a estado `PRESTADO`.
6. La fecha estimada de devolución no puede ser anterior a la fecha de préstamo.
7. No se pueden crear o actualizar libros con campos vacíos.

---

## 15. Convenciones de ramas sugeridas

Para evidenciar el trabajo en equipo, se recomienda usar ramas pequeñas y claras.

Ramas sugeridas:

```text
main
develop
feature/modelos
feature/repositorios
feature/servicios
feature/menu-consola
feature/readme
```

La idea es que cada integrante trabaje en una rama específica y luego se realicen pull requests cortos hacia `develop`.

---

## 16. Flujo de trabajo sugerido con Git

### 16.1. Crear rama de desarrollo

```bash
git checkout -b develop
```

### 16.2. Crear una rama para una funcionalidad

Ejemplo:

```bash
git checkout -b feature/modelos
```

### 16.3. Agregar cambios

```bash
git add .
```

### 16.4. Crear commit

```bash
git commit -m "Agrega modelos del dominio"
```

### 16.5. Subir rama

```bash
git push origin feature/modelos
```

Luego se puede crear un Pull Request desde GitHub hacia la rama `develop`.

---

## 17. Ejemplos de commits sugeridos

```text
Agrega estructura base del proyecto Maven
Agrega modelos del dominio
Agrega repositorios con persistencia CSV
Agrega servicios de negocio
Agrega menú por consola
Agrega datos mínimos de prueba
Actualiza README con instrucciones del proyecto
```

---

## 18. Limitaciones del MVP

Esta versión es un MVP, por lo tanto tiene algunas limitaciones:

- No cuenta con autenticación real.
- No usa base de datos relacional.
- No maneja interfaz gráfica todavía.
- No tiene control avanzado de multas o devoluciones.
- No maneja concurrencia ni múltiples usuarios al mismo tiempo.
- La persistencia en CSV es básica y no permite datos con comas en los campos.

Estas limitaciones son aceptables para esta primera versión, ya que el objetivo principal es demostrar la transacción de negocio, el CRUD y la trazabilidad desde el modelo hasta el código.

---

## 19. Posibles mejoras futuras

Algunas mejoras que podrían implementarse después del MVP son:

- Agregar interfaz gráfica con JavaFX o Swing.
- Implementar autenticación de usuarios.
- Agregar módulo de devolución de libros.
- Agregar cálculo de multas.
- Usar una base de datos como MySQL, PostgreSQL o SQLite.
- Agregar búsqueda de libros por título, autor o categoría.
- Generar reportes de préstamos activos.
- Registrar historial completo de préstamos por usuario.

---

## 20. Estado actual del proyecto

El MVP cuenta con:

- Proyecto Java creado con Maven.
- Ejecución funcional desde consola.
- Modelos del dominio implementados.
- Repositorios con persistencia CSV.
- Servicios con lógica de negocio.
- Transacción de préstamo implementada.
- CRUD de libros implementado.
- Datos mínimos de prueba.
- README con instrucciones de ejecución.
- Trazabilidad entre análisis, modelos y código.

---

## 21. Autores

Proyecto desarrollado por el equipo de trabajo para la asignatura correspondiente.

Integrantes:

```text
Juan José Barrero Jaramillo
Juan David Torres Arango
Juan Diego Pérez Upegui 
```

---

## 22. Conclusión

Este MVP permite representar de forma sencilla el proceso principal de préstamo de libros en una biblioteca.  
Aunque la implementación es básica, cumple con los elementos solicitados: una transacción de negocio, un CRUD funcional, persistencia simple, datos de prueba, documentación de ejecución y trazabilidad desde los modelos hasta el código.

La solución sirve como primera versión funcional y puede ser ampliada posteriormente con interfaz gráfica, base de datos, autenticación y nuevos módulos relacionados con devoluciones, multas e historial de préstamos.