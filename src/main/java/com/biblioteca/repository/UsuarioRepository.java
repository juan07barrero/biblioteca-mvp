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

    public void guardarTodos(List<Usuario> usuarios) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ARCHIVO))) {
            bw.write("id,nombre,correo");
            bw.newLine();

            for (Usuario usuario : usuarios) {
                bw.write(
                        usuario.getId() + "," +
                                usuario.getNombre() + "," +
                                usuario.getCorreo()
                );
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error al guardar usuarios: " + e.getMessage());
        }
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

    public Usuario buscarPorCorreo(String correo) {
        List<Usuario> usuarios = listar();

        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equalsIgnoreCase(correo)) {
                return usuario;
            }
        }

        return null;
    }

    public void crear(Usuario nuevoUsuario) {
        List<Usuario> usuarios = listar();
        usuarios.add(nuevoUsuario);
        guardarTodos(usuarios);
    }

    public void actualizar(Usuario usuarioActualizado) {
        List<Usuario> usuarios = listar();

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId() == usuarioActualizado.getId()) {
                usuarios.set(i, usuarioActualizado);
                break;
            }
        }

        guardarTodos(usuarios);
    }

    public void eliminar(int id) {
        List<Usuario> usuarios = listar();
        usuarios.removeIf(usuario -> usuario.getId() == id);
        guardarTodos(usuarios);
    }

    public int obtenerSiguienteId() {
        List<Usuario> usuarios = listar();
        int mayorId = 0;

        for (Usuario usuario : usuarios) {
            if (usuario.getId() > mayorId) {
                mayorId = usuario.getId();
            }
        }

        return mayorId + 1;
    }
}