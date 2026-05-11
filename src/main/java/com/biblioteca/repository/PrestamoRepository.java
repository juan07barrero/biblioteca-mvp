package com.biblioteca.repository;

import com.biblioteca.model.Prestamo;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrestamoRepository {

    private static final String RUTA_ARCHIVO = "data/prestamos.csv";

    public List<Prestamo> listar() {
        List<Prestamo> prestamos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                String[] datos = linea.split(",");

                if (datos.length >= 6) {
                    Prestamo prestamo = new Prestamo(
                            Integer.parseInt(datos[0]),
                            Integer.parseInt(datos[1]),
                            Integer.parseInt(datos[2]),
                            LocalDate.parse(datos[3]),
                            LocalDate.parse(datos[4]),
                            datos[5]
                    );

                    prestamos.add(prestamo);
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer préstamos: " + e.getMessage());
        }

        return prestamos;
    }

    public void guardarTodos(List<Prestamo> prestamos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ARCHIVO))) {
            bw.write("id,idUsuario,idLibro,fechaPrestamo,fechaDevolucionEstimada,estado");
            bw.newLine();

            for (Prestamo prestamo : prestamos) {
                bw.write(
                        prestamo.getId() + "," +
                        prestamo.getIdUsuario() + "," +
                        prestamo.getIdLibro() + "," +
                        prestamo.getFechaPrestamo() + "," +
                        prestamo.getFechaDevolucionEstimada() + "," +
                        prestamo.getEstado()
                );
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error al guardar préstamos: " + e.getMessage());
        }
    }

    public void crear(Prestamo nuevoPrestamo) {
        List<Prestamo> prestamos = listar();
        prestamos.add(nuevoPrestamo);
        guardarTodos(prestamos);
    }

    public int obtenerSiguienteId() {
        List<Prestamo> prestamos = listar();
        int mayorId = 0;

        for (Prestamo prestamo : prestamos) {
            if (prestamo.getId() > mayorId) {
                mayorId = prestamo.getId();
            }
        }

        return mayorId + 1;
    }
}