package com.biblioteca.model;

import java.time.LocalDate;

public class Prestamo {

    private int id;
    private int idUsuario;
    private int idLibro;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionEstimada;
    private String estado;

    public Prestamo() {
    }

    public Prestamo(int id, int idUsuario, int idLibro, LocalDate fechaPrestamo,
                    LocalDate fechaDevolucionEstimada, String estado) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucionEstimada = fechaDevolucionEstimada;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }   

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucionEstimada() {
        return fechaDevolucionEstimada;
    }

    public void setFechaDevolucionEstimada(LocalDate fechaDevolucionEstimada) {
        this.fechaDevolucionEstimada = fechaDevolucionEstimada;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", idLibro=" + idLibro +
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucionEstimada=" + fechaDevolucionEstimada +
                ", estado='" + estado + '\'' +
                '}';
    }
}