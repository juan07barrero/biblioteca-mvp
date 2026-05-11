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

    public String registrarPrestamo(int idUsuario, int idLibro, LocalDate fechaPrestamo, LocalDate fechaDevolucionEstimada) {
        Usuario usuario = usuarioRepository.buscarPorId(idUsuario);

        if (usuario == null) {
            return "No existe un usuario con el ID indicado.";
        }

        Libro libro = libroRepository.buscarPorId(idLibro);

        if (libro == null) {
            return "No existe un libro con el ID indicado.";
        }

        if (libro.getEstado() == EstadoLibro.PRESTADO) {
            return "El libro no está disponible para préstamo.";
        }

        if (fechaPrestamo == null) {
            return "La fecha de préstamo no puede estar vacía.";
        }

        if (fechaDevolucionEstimada == null) {
            return "La fecha de devolución estimada no puede estar vacía.";
        }

        if (fechaDevolucionEstimada.isBefore(fechaPrestamo)) {
            return "La fecha de devolución estimada no puede ser anterior a la fecha de préstamo.";
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

        return "Préstamo registrado correctamente con ID: " + nuevoId;
    }
}