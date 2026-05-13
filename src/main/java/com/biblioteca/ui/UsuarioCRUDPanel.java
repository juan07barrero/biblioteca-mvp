package com.biblioteca.ui;

import com.biblioteca.model.Usuario;
import com.biblioteca.service.UsuarioService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UsuarioCRUDPanel extends JPanel {

    private UsuarioService usuarioService;

    private JTable tablaUsuarios;
    private DefaultTableModel modeloTabla;

    private JTextField txtId, txtNombre, txtCorreo;
    private JButton btnCrear, btnActualizar, btnEliminar, btnLimpiar;

    public UsuarioCRUDPanel() {
        usuarioService = new UsuarioService();

        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(15, 15, 15, 15));
        setBackground(new Color(240, 248, 255));

        inicializarComponentes();
        cargarTabla();
    }

    private void inicializarComponentes() {
        // Panel superior: Formulario CRUD
        JPanel panelFormulario = LookAndFeelUtil.crearPanelConBorde("Formulario de Usuario");
        panelFormulario.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campos
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFormulario.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        txtId = new JTextField(15);
        txtId.setEditable(false);
        panelFormulario.add(txtId, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 2;
        panelFormulario.add(new JLabel("(Autogenerado)"), gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFormulario.add(new JLabel("Nombre:*"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        txtNombre = new JTextField(30);
        panelFormulario.add(txtNombre, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelFormulario.add(new JLabel("Correo:*"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        txtCorreo = new JTextField(30);
        panelFormulario.add(txtCorreo, gbc);
        gbc.gridwidth = 1;

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBackground(new Color(240, 248, 255));

        btnCrear = LookAndFeelUtil.crearBoton("➕ CREAR", new Color(34, 139, 34));
        btnActualizar = LookAndFeelUtil.crearBoton("✏️ ACTUALIZAR", new Color(255, 140, 0));
        btnEliminar = LookAndFeelUtil.crearBoton("🗑️ ELIMINAR", new Color(220, 20, 60));
        btnLimpiar = LookAndFeelUtil.crearBoton("🧹 LIMPIAR", new Color(100, 100, 100));

        panelBotones.add(btnCrear);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        panelFormulario.add(panelBotones, gbc);

        // Panel central: Tabla de usuarios
        JPanel panelTabla = LookAndFeelUtil.crearPanelConBorde("Lista de Usuarios");
        panelTabla.setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Correo"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaUsuarios = new JTable(modeloTabla);
        tablaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaUsuarios.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = tablaUsuarios.getSelectedRow();
                if (row >= 0) {
                    cargarUsuarioSeleccionado(row);
                }
            }
        });

        JScrollPane scrollTabla = new JScrollPane(tablaUsuarios);
        scrollTabla.setPreferredSize(new Dimension(600, 300));
        panelTabla.add(scrollTabla, BorderLayout.CENTER);

        // Panel de instrucciones
        JPanel panelInstrucciones = new JPanel();
        panelInstrucciones.setBackground(new Color(240, 248, 255));
        JLabel instrucciones = new JLabel("💡 Consejo: Seleccione una fila de la tabla para cargar sus datos en el formulario");
        instrucciones.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        instrucciones.setForeground(Color.GRAY);
        panelInstrucciones.add(instrucciones);

        // Organizar
        JPanel panelSuperior = new JPanel(new BorderLayout(10, 10));
        panelSuperior.setBackground(new Color(240, 248, 255));
        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        panelSuperior.add(panelInstrucciones, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);
        add(panelTabla, BorderLayout.CENTER);

        // Acciones de botones
        btnCrear.addActionListener(e -> crearUsuario());
        btnActualizar.addActionListener(e -> actualizarUsuario());
        btnEliminar.addActionListener(e -> eliminarUsuario());
        btnLimpiar.addActionListener(e -> limpiarFormulario());
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        List<Usuario> usuarios = usuarioService.listarUsuarios();

        if (usuarios.isEmpty()) {
            cargarDatosPrueba();
            usuarios = usuarioService.listarUsuarios();
        }

        for (Usuario u : usuarios) {
            modeloTabla.addRow(new Object[]{u.getId(), u.getNombre(), u.getCorreo()});
        }
    }

    private void cargarDatosPrueba() {
        usuarioService.crearUsuario("Ana García", "ana@biblioteca.com");
        usuarioService.crearUsuario("Carlos López", "carlos@biblioteca.com");
        usuarioService.crearUsuario("María Rodríguez", "maria@biblioteca.com");
        usuarioService.crearUsuario("Juan Pérez", "juan@biblioteca.com");
        usuarioService.crearUsuario("Laura Martínez", "laura@biblioteca.com");
        usuarioService.crearUsuario("Pedro Sánchez", "pedro@biblioteca.com");
        usuarioService.crearUsuario("Sofia Ramírez", "sofia@biblioteca.com");
        usuarioService.crearUsuario("Diego Torres", "diego@biblioteca.com");
        usuarioService.crearUsuario("Valentina Castro", "valentina@biblioteca.com");
        usuarioService.crearUsuario("Andrés Mora", "andres@biblioteca.com");
    }

    private void cargarUsuarioSeleccionado(int row) {
        txtId.setText(modeloTabla.getValueAt(row, 0).toString());
        txtNombre.setText(modeloTabla.getValueAt(row, 1).toString());
        txtCorreo.setText(modeloTabla.getValueAt(row, 2).toString());
    }

    private void crearUsuario() {
        String nombre = txtNombre.getText().trim();
        String correo = txtCorreo.getText().trim();

        if (nombre.isEmpty()) {
            LookAndFeelUtil.mostrarMensaje(this, "El nombre es obligatorio", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (correo.isEmpty()) {
            LookAndFeelUtil.mostrarMensaje(this, "El correo es obligatorio", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String resultado = usuarioService.crearUsuario(nombre, correo);

        if (resultado.contains("correctamente")) {
            LookAndFeelUtil.mostrarMensaje(this, resultado, "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();
            cargarTabla();
        } else {
            LookAndFeelUtil.mostrarMensaje(this, resultado, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarUsuario() {
        if (txtId.getText().isEmpty()) {
            LookAndFeelUtil.mostrarMensaje(this, "Seleccione un usuario de la tabla", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = Integer.parseInt(txtId.getText());
        String nombre = txtNombre.getText().trim();
        String correo = txtCorreo.getText().trim();

        if (nombre.isEmpty()) {
            LookAndFeelUtil.mostrarMensaje(this, "El nombre es obligatorio", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (correo.isEmpty()) {
            LookAndFeelUtil.mostrarMensaje(this, "El correo es obligatorio", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String resultado = usuarioService.actualizarUsuario(id, nombre, correo);

        if (resultado.contains("correctamente")) {
            LookAndFeelUtil.mostrarMensaje(this, resultado, "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();
            cargarTabla();
        } else {
            LookAndFeelUtil.mostrarMensaje(this, resultado, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarUsuario() {
        if (txtId.getText().isEmpty()) {
            LookAndFeelUtil.mostrarMensaje(this, "Seleccione un usuario de la tabla", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = Integer.parseInt(txtId.getText());
        String nombre = txtNombre.getText();

        int confirm = LookAndFeelUtil.mostrarConfirmacion(this,
                "¿Está seguro de eliminar al usuario '" + nombre + "'?",
                "Confirmar Eliminación");

        if (confirm == JOptionPane.YES_OPTION) {
            String resultado = usuarioService.eliminarUsuario(id);

            if (resultado.contains("correctamente")) {
                LookAndFeelUtil.mostrarMensaje(this, resultado, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarFormulario();
                cargarTabla();
            } else {
                LookAndFeelUtil.mostrarMensaje(this, resultado, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void limpiarFormulario() {
        txtId.setText("");
        txtNombre.setText("");
        txtCorreo.setText("");
        tablaUsuarios.clearSelection();
    }
}