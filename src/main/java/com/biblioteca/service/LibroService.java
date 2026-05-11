package com.biblioteca.service;

import com.biblioteca.model.EstadoLibro;
import com.biblioteca.model.Libro;
import com.biblioteca.repository.LibroRepository;

import java.util.List;

public class LibroService {

    private LibroRepository libroRepository;

    public LibroService() {
        this.libroRepository = new LibroRepository();
    }

    public List<Libro> listarLibros() {
        return libroRepository.listar();
    }

    public Libro buscarLibroPorId(int id) {
        return libroRepository.buscarPorId(id);
    }

    public String crearLibro(String titulo, String autor, String categoria) {
        if (titulo == null || titulo.isBlank()) {
            return "El título del libro no puede estar vacío.";
        }

        if (autor == null || autor.isBlank()) {
            return "El autor del libro no puede estar vacío.";
        }

        if (categoria == null || categoria.isBlank()) {
            return "La categoría del libro no puede estar vacía.";
        }

        int nuevoId = libroRepository.obtenerSiguienteId();

        Libro nuevoLibro = new Libro(
                nuevoId,
                titulo,
                autor,
                categoria,
                EstadoLibro.DISPONIBLE
        );

        libroRepository.crear(nuevoLibro);

        return "Libro creado correctamente con ID: " + nuevoId;
    }

    public String actualizarLibro(int id, String titulo, String autor, String categoria, EstadoLibro estado) {
        Libro libroExistente = libroRepository.buscarPorId(id);

        if (libroExistente == null) {
            return "No existe un libro con el ID indicado.";
        }

        if (titulo == null || titulo.isBlank()) {
            return "El título del libro no puede estar vacío.";
        }

        if (autor == null || autor.isBlank()) {
            return "El autor del libro no puede estar vacío.";
        }

        if (categoria == null || categoria.isBlank()) {
            return "La categoría del libro no puede estar vacía.";
        }

        Libro libroActualizado = new Libro(
                id,
                titulo,
                autor,
                categoria,
                estado
        );

        libroRepository.actualizar(libroActualizado);

        return "Libro actualizado correctamente.";
    }

    public String eliminarLibro(int id) {
        Libro libroExistente = libroRepository.buscarPorId(id);

        if (libroExistente == null) {
            return "No existe un libro con el ID indicado.";
        }

        libroRepository.eliminar(id);

        return "Libro eliminado correctamente.";
    }
}