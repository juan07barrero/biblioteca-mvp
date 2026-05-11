package com.biblioteca.repository;

import com.biblioteca.model.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

    private static final String RUTA_ARCHIVO = "data/usuarios.csv";

    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                String[] datos = linea.split(",");

                if (datos.length >= 3) {
                    Usuario usuario = new Usuario(
                            Integer.parseInt(datos[0]),
                            datos[1],
                            datos[2]
                    );

                    usuarios.add(usuario);
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer usuarios: " + e.getMessage());
        }

        return usuarios;
    }

    public Usuario buscarPorId(int id) {
        List<Usuario> usuarios = listar();

        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }

        return null;
    }
}