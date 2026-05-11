package com.biblioteca;

import com.biblioteca.model.EstadoLibro;
import com.biblioteca.model.Libro;
import com.biblioteca.model.Prestamo;
import com.biblioteca.service.LibroService;
import com.biblioteca.service.PrestamoService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static LibroService libroService = new LibroService();
    private static PrestamoService prestamoService = new PrestamoService();

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenuPrincipal();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    registrarPrestamo();
                    break;
                case 2:
                    menuLibros();
                    break;
                case 3:
                    listarPrestamos();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n====================================");
        System.out.println("     SISTEMA MVP BIBLIOTECA");
        System.out.println("====================================");
        System.out.println("1. Registrar préstamo");
        System.out.println("2. Gestionar libros");
        System.out.println("3. Listar préstamos");
        System.out.println("0. Salir");
        System.out.println("====================================");
    }

    private static void registrarPrestamo() {
        System.out.println("\n--- Registrar préstamo ---");

        listarLibros();

        int idUsuario = leerEntero("Ingrese ID del usuario: ");
        int idLibro = leerEntero("Ingrese ID del libro: ");

        LocalDate fechaPrestamo = LocalDate.now();
        LocalDate fechaDevolucionEstimada = fechaPrestamo.plusDays(7);

        String resultado = prestamoService.registrarPrestamo(
                idUsuario,
                idLibro,
                fechaPrestamo,
                fechaDevolucionEstimada
        );

        System.out.println(resultado);
    }

    private static void menuLibros() {
        int opcion;

        do {
            mostrarMenuLibros();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    listarLibros();
                    break;
                case 2:
                    crearLibro();
                    break;
                case 3:
                    actualizarLibro();
                    break;
                case 4:
                    eliminarLibro();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    private static void mostrarMenuLibros() {
        System.out.println("\n------------------------------------");
        System.out.println("          GESTIÓN DE LIBROS");
        System.out.println("------------------------------------");
        System.out.println("1. Listar libros");
        System.out.println("2. Crear libro");
        System.out.println("3. Actualizar libro");
        System.out.println("4. Eliminar libro");
        System.out.println("0. Volver");
        System.out.println("------------------------------------");
    }

    private static void listarLibros() {
        System.out.println("\n--- Lista de libros ---");

        List<Libro> libros = libroService.listarLibros();

        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }

        for (Libro libro : libros) {
            System.out.println(
                    "ID: " + libro.getId() +
                    " | Título: " + libro.getTitulo() +
                    " | Autor: " + libro.getAutor() +
                    " | Categoría: " + libro.getCategoria() +
                    " | Estado: " + libro.getEstado()
            );
        }
    }

    private static void crearLibro() {
        System.out.println("\n--- Crear libro ---");

        String titulo = leerTexto("Título: ");
        String autor = leerTexto("Autor: ");
        String categoria = leerTexto("Categoría: ");

        String resultado = libroService.crearLibro(titulo, autor, categoria);
        System.out.println(resultado);
    }

    private static void actualizarLibro() {
        System.out.println("\n--- Actualizar libro ---");

        listarLibros();

        int id = leerEntero("Ingrese el ID del libro a actualizar: ");
        String titulo = leerTexto("Nuevo título: ");
        String autor = leerTexto("Nuevo autor: ");
        String categoria = leerTexto("Nueva categoría: ");

        System.out.println("Estado:");
        System.out.println("1. DISPONIBLE");
        System.out.println("2. PRESTADO");

        int opcionEstado = leerEntero("Seleccione estado: ");

        EstadoLibro estado;

        if (opcionEstado == 1) {
            estado = EstadoLibro.DISPONIBLE;
        } else if (opcionEstado == 2) {
            estado = EstadoLibro.PRESTADO;
        } else {
            System.out.println("Estado no válido. Se asignará DISPONIBLE por defecto.");
            estado = EstadoLibro.DISPONIBLE;
        }

        String resultado = libroService.actualizarLibro(id, titulo, autor, categoria, estado);
        System.out.println(resultado);
    }

    private static void eliminarLibro() {
        System.out.println("\n--- Eliminar libro ---");

        listarLibros();

        int id = leerEntero("Ingrese el ID del libro a eliminar: ");

        String resultado = libroService.eliminarLibro(id);
        System.out.println(resultado);
    }

    private static void listarPrestamos() {
        System.out.println("\n--- Lista de préstamos ---");

        List<Prestamo> prestamos = prestamoService.listarPrestamos();

        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
            return;
        }

        for (Prestamo prestamo : prestamos) {
            System.out.println(
                    "ID: " + prestamo.getId() +
                    " | Usuario: " + prestamo.getIdUsuario() +
                    " | Libro: " + prestamo.getIdLibro() +
                    " | Fecha préstamo: " + prestamo.getFechaPrestamo() +
                    " | Fecha devolución estimada: " + prestamo.getFechaDevolucionEstimada() +
                    " | Estado: " + prestamo.getEstado()
            );
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int numero = Integer.parseInt(scanner.nextLine());
                return numero;
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }
        }
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }
}