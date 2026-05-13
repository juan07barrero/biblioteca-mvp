package com.biblioteca.service;

import com.biblioteca.model.Usuario;
import com.biblioteca.repository.UsuarioRepository;

import java.util.List;

public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService() {
        this.usuarioRepository = new UsuarioRepository();
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.listar();
    }

    public Usuario buscarUsuarioPorId(int id) {
        return usuarioRepository.buscarPorId(id);
    }

    public Usuario buscarUsuarioPorCorreo(String correo) {
        return usuarioRepository.buscarPorCorreo(correo);
    }

    public String crearUsuario(String nombre, String correo) {
        if (nombre == null || nombre.isBlank()) {
            return "El nombre del usuario no puede estar vacío.";
        }

        if (correo == null || correo.isBlank()) {
            return "El correo del usuario no puede estar vacío.";
        }

        if (!correo.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return "El formato del correo electrónico no es válido.";
        }

        if (usuarioRepository.buscarPorCorreo(correo) != null) {
            return "Ya existe un usuario con este correo electrónico.";
        }

        int nuevoId = usuarioRepository.obtenerSiguienteId();

        Usuario nuevoUsuario = new Usuario(
                nuevoId,
                nombre,
                correo
        );

        usuarioRepository.crear(nuevoUsuario);

        return "Usuario creado correctamente con ID: " + nuevoId;
    }

    public String actualizarUsuario(int id, String nombre, String correo) {
        Usuario usuarioExistente = usuarioRepository.buscarPorId(id);

        if (usuarioExistente == null) {
            return "No existe un usuario con el ID indicado.";
        }

        if (nombre == null || nombre.isBlank()) {
            return "El nombre del usuario no puede estar vacío.";
        }

        if (correo == null || correo.isBlank()) {
            return "El correo del usuario no puede estar vacío.";
        }

        if (!correo.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return "El formato del correo electrónico no es válido.";
        }

        Usuario usuarioConMismoCorreo = usuarioRepository.buscarPorCorreo(correo);
        if (usuarioConMismoCorreo != null && usuarioConMismoCorreo.getId() != id) {
            return "Ya existe otro usuario con este correo electrónico.";
        }

        Usuario usuarioActualizado = new Usuario(
                id,
                nombre,
                correo
        );

        usuarioRepository.actualizar(usuarioActualizado);

        return "Usuario actualizado correctamente.";
    }

    public String eliminarUsuario(int id) {
        Usuario usuarioExistente = usuarioRepository.buscarPorId(id);

        if (usuarioExistente == null) {
            return "No existe un usuario con el ID indicado.";
        }

        usuarioRepository.eliminar(id);

        return "Usuario eliminado correctamente.";
    }
}