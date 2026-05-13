package com.biblioteca.service;

import com.biblioteca.model.EstadoLibro;
import com.biblioteca.model.Libro;
import com.biblioteca.model.Prestamo;
import com.biblioteca.model.Usuario;
import com.biblioteca.repository.LibroRepository;
import com.biblioteca.repository.PrestamoRepository;
import com.biblioteca.repository.UsuarioRepository;

import java.time.LocalDate;
import java.util.List;

public class PrestamoService {

    private PrestamoRepository prestamoRepository;
    private LibroRepository libroRepository;
    private UsuarioRepository usuarioRepository;

    public PrestamoService() {
        this.prestamoRepository = new PrestamoRepository();
        this.libroRepository = new LibroRepository();
        this.usuarioRepository = new UsuarioRepository();
    }

    public List<Prestamo> listarPrestamos() {
        return prestamoRepository.listar();
    }

    public int contarPrestamosActivosPorUsuario(int idUsuario) {
        List<Prestamo> prestamos = prestamoRepository.listar();
        int contador = 0;

        for (Prestamo prestamo : prestamos) {
            if (prestamo.getIdUsuario() == idUsuario && prestamo.getEstado().equals("ACTIVO")) {
                contador++;
            }
        }

        return contador;
    }

    public String registrarPrestamo(int idUsuario, int idLibro, LocalDate fechaPrestamo, LocalDate fechaDevolucionEstimada) {
        Usuario usuario = usuarioRepository.buscarPorId(idUsuario);

        if (usuario == null) {
            return "ERROR: No existe un usuario con el ID " + idUsuario;
        }

        // Regla de negocio: Máximo 3 préstamos activos
        int prestamosActivos = contarPrestamosActivosPorUsuario(idUsuario);
        if (prestamosActivos >= 3) {
            return "ERROR: El usuario " + usuario.getNombre() + " ya tiene " + prestamosActivos +
                    " préstamos activos. Máximo permitido: 3 préstamos.";
        }

        Libro libro = libroRepository.buscarPorId(idLibro);

        if (libro == null) {
            return "ERROR: No existe un libro con el ID " + idLibro;
        }

        // Regla de negocio: No prestar libro ya prestado
        if (libro.getEstado() == EstadoLibro.PRESTADO) {
            return "ERROR: El libro '" + libro.getTitulo() + "' no está disponible para préstamo (ya está prestado).";
        }

        if (fechaPrestamo == null) {
            return "ERROR: La fecha de préstamo no puede estar vacía.";
        }

        if (fechaDevolucionEstimada == null) {
            return "ERROR: La fecha de devolución estimada no puede estar vacía.";
        }

        if (fechaDevolucionEstimada.isBefore(fechaPrestamo)) {
            return "ERROR: La fecha de devolución estimada no puede ser anterior a la fecha de préstamo.";
        }

        int nuevoId = prestamoRepository.obtenerSiguienteId();

        Prestamo nuevoPrestamo = new Prestamo(
                nuevoId,
                idUsuario,
                idLibro,
                fechaPrestamo,
                fechaDevolucionEstimada,
                "ACTIVO"
        );

        prestamoRepository.crear(nuevoPrestamo);

        libro.setEstado(EstadoLibro.PRESTADO);
        libroRepository.actualizar(libro);

        return "ÉXITO: Préstamo registrado correctamente con ID: " + nuevoId +
                "\nUsuario: " + usuario.getNombre() +
                "\nLibro: " + libro.getTitulo() +
                "\nFecha devolución: " + fechaDevolucionEstimada;
    }
}