package com.biblioteca.repository;

import com.biblioteca.model.EstadoLibro;
import com.biblioteca.model.Libro;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LibroRepository {

    private static final String RUTA_ARCHIVO = "data/libros.csv";

    public List<Libro> listar() {
        List<Libro> libros = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                String[] datos = linea.split(",");

                if (datos.length >= 5) {
                    Libro libro = new Libro(
                            Integer.parseInt(datos[0]),
                            datos[1],
                            datos[2],
                            datos[3],
                            EstadoLibro.valueOf(datos[4])
                    );

                    libros.add(libro);
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer libros: " + e.getMessage());
        }

        return libros;
    }

    public void guardarTodos(List<Libro> libros) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ARCHIVO))) {
            bw.write("id,titulo,autor,categoria,estado");
            bw.newLine();

            for (Libro libro : libros) {
                bw.write(
                        libro.getId() + "," +
                        libro.getTitulo() + "," +
                        libro.getAutor() + "," +
                        libro.getCategoria() + "," +
                        libro.getEstado()
                );
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error al guardar libros: " + e.getMessage());
        }
    }

    public Libro buscarPorId(int id) {
        List<Libro> libros = listar();

        for (Libro libro : libros) {
            if (libro.getId() == id) {
                return libro;
            }
        }

        return null;
    }

    public void crear(Libro nuevoLibro) {
        List<Libro> libros = listar();
        libros.add(nuevoLibro);
        guardarTodos(libros);
    }

    public void actualizar(Libro libroActualizado) {
        List<Libro> libros = listar();

        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getId() == libroActualizado.getId()) {
                libros.set(i, libroActualizado);
                break;
            }
        }

        guardarTodos(libros);
    }

    public void eliminar(int id) {
        List<Libro> libros = listar();
        libros.removeIf(libro -> libro.getId() == id);
        guardarTodos(libros);
    }

    public int obtenerSiguienteId() {
        List<Libro> libros = listar();
        int mayorId = 0;

        for (Libro libro : libros) {
            if (libro.getId() > mayorId) {
                mayorId = libro.getId();
            }
        }

        return mayorId + 1;
    }
}