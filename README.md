# Sistema MVP de Préstamo de Libros - Biblioteca

Producto Mínimo Viable desarrollado en Java con Maven para apoyar el proceso de préstamo de libros en una biblioteca.

El sistema cuenta con una interfaz gráfica sencilla desarrollada con Swing, desde la cual se puede registrar la transacción principal de préstamo de libros y gestionar una entidad del dominio mediante operaciones CRUD. La persistencia se realiza mediante archivos CSV para mantener una implementación simple y fácil de ejecutar.

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

La aplicación abrirá una interfaz gráfica desarrollada con Swing.

La clase principal configurada para la ejecución es:

```text
com.biblioteca.ui.MainUI
```

La interfaz cuenta con dos pantallas principales:

```text
1. Registrar préstamo
2. CRUD usuarios
```

La primera pantalla corresponde a la transacción de negocio principal.  
La segunda pantalla corresponde al CRUD de una entidad del dominio.

---

## 2. Usuarios de prueba demo

El sistema no cuenta con autenticación, ya que es un MVP sencillo.  
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
- Gestionar usuarios mediante operaciones CRUD.
- Mantener datos mínimos de prueba usando archivos CSV.
- Usar una interfaz gráfica simple para facilitar la interacción con el sistema.

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

La entidad seleccionada para el CRUD en la interfaz gráfica es:

**Usuario**

Operaciones implementadas:

- Listar usuarios.
- Crear usuario.
- Actualizar usuario.
- Eliminar usuario.

Esta entidad es importante dentro del dominio porque los usuarios son quienes realizan préstamos dentro del sistema de biblioteca.

---

## 5. Tecnologías utilizadas

El proyecto utiliza tecnologías libres y sencillas de ejecutar:

- Java 17.
- Maven.
- Swing para la interfaz gráfica.
- Persistencia en archivos CSV.
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
                    ├── service/
                    │   ├── LibroService.java
                    │   ├── PrestamoService.java
                    │   └── UsuarioService.java
                    │
                    └── ui/
                        ├── MainUI.java
                        ├── PrestamoPanel.java
                        ├── UsuarioCRUDPanel.java
                        └── LookAndFeelUtil.java
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
- `UsuarioService`

Aquí se implementan las validaciones, reglas y operaciones principales del MVP.

---

### 7.4. Capa `ui`

Contiene las clases encargadas de la interfaz gráfica del sistema.

Archivos principales:

- `MainUI.java`: ventana principal de la aplicación.
- `PrestamoPanel.java`: pantalla para registrar préstamos de libros.
- `UsuarioCRUDPanel.java`: pantalla para gestionar usuarios mediante CRUD.
- `LookAndFeelUtil.java`: clase auxiliar para configurar estilos visuales y mensajes.

La interfaz gráfica está construida con Swing y permite interactuar con los servicios del sistema sin modificar la lógica de negocio.

---

### 7.5. Clase principal

La clase principal del sistema es:

```text
com.biblioteca.ui.MainUI
```

Esta clase inicializa la ventana principal y organiza las pantallas funcionales del MVP.

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

1. El usuario abre la pantalla `Registrar préstamo`.
2. El sistema permite ingresar o seleccionar el usuario.
3. El sistema permite ingresar o seleccionar el libro.
4. El sistema busca el usuario en `usuarios.csv`.
5. El sistema busca el libro en `libros.csv`.
6. El sistema valida que el libro esté en estado `DISPONIBLE`.
7. El sistema crea un nuevo préstamo.
8. El sistema guarda el préstamo en `prestamos.csv`.
9. El sistema actualiza el estado del libro a `PRESTADO`.
10. El sistema guarda los cambios en `libros.csv`.
11. El sistema muestra un mensaje de confirmación.

---

## 10. Flujo del CRUD de usuarios

El CRUD de usuarios permite gestionar una entidad principal del dominio relacionada directamente con el proceso de préstamo.

### 10.1. Listar usuarios

Muestra todos los usuarios registrados en el archivo `usuarios.csv`.

### 10.2. Crear usuario

Permite registrar un nuevo usuario con:

- Nombre.
- Correo.

El sistema asigna automáticamente un nuevo ID.

### 10.3. Actualizar usuario

Permite modificar la información de un usuario existente.

### 10.4. Eliminar usuario

Permite eliminar un usuario existente a partir de su ID.

---

## 11. Trazabilidad desde modelos hasta código

La trazabilidad se evidencia relacionando los elementos del análisis con su implementación en código.

| Elemento del análisis | Implementación en código |
|---|---|
| Libro | `model/Libro.java` |
| Usuario | `model/Usuario.java` |
| Préstamo | `model/Prestamo.java` |
| Estado del libro | `model/EstadoLibro.java` |
| Registro de préstamo | `service/PrestamoService.java` y `ui/PrestamoPanel.java` |
| CRUD de usuarios | `service/UsuarioService.java` y `ui/UsuarioCRUDPanel.java` |
| Persistencia de libros | `repository/LibroRepository.java` |
| Persistencia de usuarios | `repository/UsuarioRepository.java` |
| Persistencia de préstamos | `repository/PrestamoRepository.java` |
| Ventana principal | `ui/MainUI.java` |
| Utilidades visuales | `ui/LookAndFeelUtil.java` |
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

## 13. Interfaz gráfica del sistema

Al ejecutar la aplicación se abre una ventana principal llamada:

```text
Sistema de Gestión Biblioteca - MVP
```

La ventana cuenta con dos pantallas principales:

### 13.1. Registrar préstamo

Esta pantalla corresponde a la transacción principal de negocio.

Permite:

- Ingresar o seleccionar un usuario.
- Ingresar o seleccionar un libro.
- Confirmar el préstamo.
- Mostrar mensajes de éxito o error.
- Actualizar el estado del libro después de registrar el préstamo.

### 13.2. CRUD usuarios

Esta pantalla permite gestionar los usuarios del sistema mediante operaciones CRUD.

Permite:

- Listar usuarios.
- Crear usuarios.
- Actualizar usuarios.
- Eliminar usuarios.

---

## 14. Reglas de negocio implementadas

El sistema implementa las siguientes reglas de negocio:

1. No se puede registrar un préstamo si el usuario no existe.
2. No se puede registrar un préstamo si el libro no existe.
3. No se puede prestar un libro que ya se encuentra en estado `PRESTADO`.
4. Cuando se registra un préstamo, el libro cambia automáticamente a estado `PRESTADO`.
5. La fecha estimada de devolución no puede ser anterior a la fecha de préstamo.
6. No se pueden crear o actualizar registros con campos obligatorios vacíos.
7. El sistema debe guardar los cambios realizados en los archivos CSV correspondientes.

---

## 15. Requisitos no funcionales mínimos

Además de las funcionalidades principales, el MVP contempla algunos requisitos no funcionales básicos relacionados con rendimiento, validaciones y accesibilidad.

### 15.1. Rendimiento

El sistema está diseñado para trabajar con un volumen pequeño de datos, adecuado para un Producto Mínimo Viable.

Requisitos definidos:

- El sistema debe permitir consultar, crear, actualizar y eliminar registros de forma rápida usando archivos CSV.
- El sistema debe operar correctamente con un conjunto inicial de entre 10 y 20 registros de prueba.
- Las operaciones principales deben ejecutarse sin requerir conexión a internet ni base de datos externa.
- La aplicación debe poder ejecutarse en un equipo básico con Java y Maven instalados.

Implementación:

- La persistencia se realiza mediante archivos CSV ubicados en la carpeta `data/`.
- Los repositorios cargan los datos desde archivos locales.
- No se requiere servidor externo ni motor de base de datos.

Limitación:

- Al usar CSV, el sistema no está pensado para grandes volúmenes de datos ni concurrencia de múltiples usuarios al mismo tiempo.

---

### 15.2. Validaciones en cliente y servidor

Aunque el MVP funciona como aplicación de escritorio, se manejan validaciones tanto en la interfaz gráfica como en la lógica de negocio.

#### Validaciones en cliente

Estas validaciones se realizan desde la capa `ui`, donde el usuario interactúa con el sistema:

- Se solicita información obligatoria antes de registrar operaciones.
- Se muestran mensajes claros cuando ocurre un error.
- Se evita continuar el flujo cuando faltan datos requeridos.
- Se informa al usuario cuando una operación se completa correctamente.

#### Validaciones en servidor o lógica de negocio

Estas validaciones se realizan en la capa `service`, principalmente en las clases `PrestamoService`, `LibroService` y `UsuarioService`.

Validaciones implementadas:

- No se puede registrar un préstamo si el usuario no existe.
- No se puede registrar un préstamo si el libro no existe.
- No se puede prestar un libro que ya está en estado `PRESTADO`.
- No se pueden registrar o actualizar datos obligatorios vacíos.
- La fecha estimada de devolución no puede ser anterior a la fecha de préstamo.
- El sistema actualiza el estado del libro cuando se registra un préstamo exitoso.

---

### 15.3. Accesibilidad básica

El sistema cuenta con accesibilidad básica a nivel de interfaz gráfica, procurando que la interacción sea clara y entendible para el usuario.

Requisitos definidos:

- La interfaz debe presentar opciones visibles y fáciles de identificar.
- Los botones deben tener textos claros.
- Los mensajes de error y confirmación deben ser comprensibles.
- La navegación entre pantallas debe ser simple.
- La aplicación debe permitir realizar las acciones principales sin pasos innecesarios.

Implementación:

- La ventana principal organiza las funciones en pantallas separadas.
- La pantalla de préstamo se enfoca en la transacción principal.
- La pantalla de usuarios se enfoca en las operaciones CRUD.
- El sistema muestra mensajes para informar si una acción fue exitosa o si fue bloqueada por una validación.

Limitación:

- Al ser un MVP académico, no se implementan características avanzadas de accesibilidad como lector de pantalla, alto contraste personalizado o navegación completamente optimizada para tecnologías asistivas.

---

## 16. Convenciones de ramas sugeridas

Para evidenciar el trabajo en equipo, se recomienda usar ramas pequeñas y claras.

Ramas sugeridas:

```text
main
develop
feature/modelos
feature/repositorios
feature/servicios
feature/interfaz-grafica
feature/readme
```

La idea es que cada integrante trabaje en una rama específica y luego se realicen pull requests cortos hacia `develop` o hacia la rama definida por el equipo.

---

## 17. Flujo de trabajo sugerido con Git

### 17.1. Crear rama de desarrollo

```bash
git checkout -b develop
```

### 17.2. Crear una rama para una funcionalidad

Ejemplo:

```bash
git checkout -b feature/interfaz-grafica
```

### 17.3. Agregar cambios

```bash
git add .
```

### 17.4. Crear commit

```bash
git commit -m "Agrega interfaz grafica del MVP"
```

### 17.5. Subir rama

```bash
git push origin feature/interfaz-grafica
```

Luego se puede crear un Pull Request desde GitHub hacia la rama definida por el equipo.

---

## 18. Ejemplos de commits sugeridos

```text
Agrega estructura base del proyecto Maven
Agrega modelos del dominio
Agrega repositorios con persistencia CSV
Agrega servicios de negocio
Agrega interfaz grafica con Swing
Agrega datos mínimos de prueba
Actualiza README con instrucciones del proyecto
```

---

## 19. Limitaciones del MVP

Esta versión es un MVP, por lo tanto tiene algunas limitaciones:

- No cuenta con autenticación real.
- No usa base de datos relacional.
- No tiene control avanzado de multas o devoluciones.
- No maneja concurrencia ni múltiples usuarios al mismo tiempo.
- La persistencia en CSV es básica y no permite datos con comas en los campos.
- La interfaz gráfica es sencilla y está enfocada únicamente en las funcionalidades solicitadas.

Estas limitaciones son aceptables para esta primera versión, ya que el objetivo principal es demostrar la transacción de negocio, el CRUD y la trazabilidad desde el modelo hasta el código.

---

## 20. Posibles mejoras futuras

Algunas mejoras que podrían implementarse después del MVP son:

- Implementar autenticación de usuarios.
- Agregar módulo de devolución de libros.
- Agregar cálculo de multas.
- Usar una base de datos como MySQL, PostgreSQL o SQLite.
- Agregar búsqueda de libros por título, autor o categoría.
- Generar reportes de préstamos activos.
- Registrar historial completo de préstamos por usuario.
- Mejorar la interfaz gráfica con más componentes visuales.
- Agregar validaciones visuales más detalladas.

---

## 21. Estado actual del proyecto

El MVP cuenta con:

- Proyecto Java creado con Maven.
- Ejecución funcional mediante interfaz gráfica Swing.
- Modelos del dominio implementados.
- Repositorios con persistencia CSV.
- Servicios con lógica de negocio.
- Transacción de préstamo implementada.
- CRUD de usuarios implementado en interfaz gráfica.
- Datos mínimos de prueba.
- README con instrucciones de ejecución.
- Trazabilidad entre análisis, modelos, servicios, repositorios e interfaz.
- Requisitos no funcionales mínimos documentados.

---

## 22. Autores

Proyecto desarrollado por el equipo de trabajo para la asignatura correspondiente.

Integrantes:

```text
Juan José Barrero Jaramillo
Juan David Torres Arango
Juan Diego Pérez Upegui
```

---

## 23. Conclusión

Este MVP permite representar de forma sencilla el proceso principal de préstamo de libros en una biblioteca.  
Aunque la implementación es básica, cumple con los elementos solicitados: una transacción de negocio, un CRUD funcional, persistencia simple, datos de prueba, documentación de ejecución, interfaz gráfica y trazabilidad desde los modelos hasta el código.

La solución sirve como primera versión funcional y puede ser ampliada posteriormente con base de datos, autenticación y nuevos módulos relacionados con devoluciones, multas e historial de préstamos.