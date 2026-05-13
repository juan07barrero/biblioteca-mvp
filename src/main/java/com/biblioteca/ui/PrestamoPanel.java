package com.biblioteca.ui;

import com.biblioteca.model.Libro;
import com.biblioteca.model.Usuario;
import com.biblioteca.service.LibroService;
import com.biblioteca.service.PrestamoService;
import com.biblioteca.service.UsuarioService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PrestamoPanel extends JPanel {

    private PrestamoService prestamoService;
    private LibroService libroService;
    private UsuarioService usuarioService;

    private JComboBox<String> comboBusquedaUsuario;
    private JTextField txtBusquedaUsuario;
    private JButton btnBuscarUsuario;
    private JLabel lblUsuarioInfo;

    private JComboBox<String> comboBusquedaLibro;
    private JTextField txtBusquedaLibro;
    private JButton btnBuscarLibro;
    private JLabel lblLibroInfo;

    private JButton btnConfirmarPrestamo;
    private JTextArea txtAreaResultado;

    private Usuario usuarioSeleccionado;
    private Libro libroSeleccionado;

    private JTable tablaUsuarios;
    private JTable tablavoluntarios;
    private DefaultTableModel modeloTablaUsuarios;
    private DefaultTableModel modeloTablaLibros;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public PrestamoPanel() {
        prestamoService = new PrestamoService();
        libroService = new LibroService();
        usuarioService = new UsuarioService();

        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(15, 15, 15, 15));
        setBackground(new Color(240, 248, 255));

        inicializarComponentes();
        cargarTablas();
    }

    private void inicializarComponentes() {
        // Panel superior: Búsqueda de Usuario
        JPanel panelUsuario = LookAndFeelUtil.crearPanelConBorde("1. BUSCAR USUARIO");
        panelUsuario.setLayout(new BorderLayout(10, 10));

        JPanel panelBusquedaUsuario = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelBusquedaUsuario.setBackground(new Color(240, 248, 255));

        comboBusquedaUsuario = new JComboBox<>(new String[]{"ID", "Correo"});
        txtBusquedaUsuario = new JTextField(20);
        btnBuscarUsuario = LookAndFeelUtil.crearBoton("Buscar Usuario", new Color(70, 130, 200));

        panelBusquedaUsuario.add(new JLabel("Buscar por:"));
        panelBusquedaUsuario.add(comboBusquedaUsuario);
        panelBusquedaUsuario.add(new JLabel("Valor:"));
        panelBusquedaUsuario.add(txtBusquedaUsuario);
        panelBusquedaUsuario.add(btnBuscarUsuario);

        lblUsuarioInfo = new JLabel(" ");
        lblUsuarioInfo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblUsuarioInfo.setForeground(new Color(0, 100, 0));

        panelUsuario.add(panelBusquedaUsuario, BorderLayout.NORTH);
        panelUsuario.add(lblUsuarioInfo, BorderLayout.SOUTH);

        // Panel Central: Lista de Usuarios y Libros
        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 10, 10));
        panelCentral.setBackground(new Color(240, 248, 255));

        // Tabla de Usuarios
        JPanel panelTablaUsuarios = LookAndFeelUtil.crearPanelConBorde("Usuarios Registrados");
        panelTablaUsuarios.setLayout(new BorderLayout());

        modeloTablaUsuarios = new DefaultTableModel(new String[]{"ID", "Nombre", "Correo"}, 0);
        tablaUsuarios = new JTable(modeloTablaUsuarios);
        tablaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaUsuarios.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = tablaUsuarios.getSelectedRow();
                if (row >= 0) {
                    int id = (int) modeloTablaUsuarios.getValueAt(row, 0);
                    usuarioSeleccionado = usuarioService.buscarUsuarioPorId(id);
                    actualizarInfoUsuario();
                }
            }
        });
        JScrollPane scrollUsuarios = new JScrollPane(tablaUsuarios);
        panelTablaUsuarios.add(scrollUsuarios, BorderLayout.CENTER);

        // Tabla de Libros
        JPanel panelTablaLibros = LookAndFeelUtil.crearPanelConBorde("Libros Disponibles");
        panelTablaLibros.setLayout(new BorderLayout());

        modeloTablaLibros = new DefaultTableModel(new String[]{"ID", "Título", "Autor", "Estado"}, 0);
        tablavoluntarios = new JTable(modeloTablaLibros);
        tablavoluntarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablavoluntarios.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = tablavoluntarios.getSelectedRow();
                if (row >= 0) {
                    int id = (int) modeloTablaLibros.getValueAt(row, 0);
                    libroSeleccionado = libroService.buscarLibroPorId(id);
                    actualizarInfoLibro();
                }
            }
        });
        JScrollPane scrollLibros = new JScrollPane(tablavoluntarios);
        panelTablaLibros.add(scrollLibros, BorderLayout.CENTER);

        panelCentral.add(panelTablaUsuarios);
        panelCentral.add(panelTablaLibros);

        // Panel Inferior: Botón Confirmar y Resultado
        JPanel panelAccion = LookAndFeelUtil.crearPanelConBorde("3. CONFIRMAR PRÉSTAMO");
        panelAccion.setLayout(new BorderLayout(10, 10));

        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(new Color(240, 248, 255));
        btnConfirmarPrestamo = LookAndFeelUtil.crearBoton("✓ CONFIRMAR PRÉSTAMO", new Color(34, 139, 34));
        btnConfirmarPrestamo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panelBoton.add(btnConfirmarPrestamo);

        txtAreaResultado = new JTextArea(5, 40);
        txtAreaResultado.setEditable(false);
        txtAreaResultado.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollResultado = new JScrollPane(txtAreaResultado);

        panelAccion.add(panelBoton, BorderLayout.NORTH);
        panelAccion.add(scrollResultado, BorderLayout.CENTER);

        // Panel de búsqueda de libro (similar al de usuario)
        JPanel panelLibro = LookAndFeelUtil.crearPanelConBorde("2. BUSCAR LIBRO");
        panelLibro.setLayout(new BorderLayout(10, 10));

        JPanel panelBusquedaLibro = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelBusquedaLibro.setBackground(new Color(240, 248, 255));

        comboBusquedaLibro = new JComboBox<>(new String[]{"ID", "Título"});
        txtBusquedaLibro = new JTextField(20);
        btnBuscarLibro = LookAndFeelUtil.crearBoton("Buscar Libro", new Color(70, 130, 200));

        panelBusquedaLibro.add(new JLabel("Buscar por:"));
        panelBusquedaLibro.add(comboBusquedaLibro);
        panelBusquedaLibro.add(new JLabel("Valor:"));
        panelBusquedaLibro.add(txtBusquedaLibro);
        panelBusquedaLibro.add(btnBuscarLibro);

        lblLibroInfo = new JLabel(" ");
        lblLibroInfo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblLibroInfo.setForeground(new Color(0, 100, 0));

        panelLibro.add(panelBusquedaLibro, BorderLayout.NORTH);
        panelLibro.add(lblLibroInfo, BorderLayout.SOUTH);

        // Organizar todo
        JPanel panelIzquierdo = new JPanel(new BorderLayout(10, 10));
        panelIzquierdo.setBackground(new Color(240, 248, 255));
        panelIzquierdo.add(panelUsuario, BorderLayout.NORTH);
        panelIzquierdo.add(panelTablaUsuarios, BorderLayout.CENTER);

        JPanel panelDerecho = new JPanel(new BorderLayout(10, 10));
        panelDerecho.setBackground(new Color(240, 248, 255));
        panelDerecho.add(panelLibro, BorderLayout.NORTH);
        panelDerecho.add(panelTablaLibros, BorderLayout.CENTER);

        add(panelIzquierdo, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.CENTER);
        add(panelAccion, BorderLayout.SOUTH);

        // Acciones de botones
        btnBuscarUsuario.addActionListener(e -> buscarUsuario());
        btnBuscarLibro.addActionListener(e -> buscarLibro());
        btnConfirmarPrestamo.addActionListener(e -> confirmarPrestamo());

        // Doble click en tablas
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int row = tablaUsuarios.getSelectedRow();
                    if (row >= 0) {
                        int id = (int) modeloTablaUsuarios.getValueAt(row, 0);
                        usuarioSeleccionado = usuarioService.buscarUsuarioPorId(id);
                        actualizarInfoUsuario();
                    }
                }
            }
        });

        tablavoluntarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int row = tablavoluntarios.getSelectedRow();
                    if (row >= 0) {
                        int id = (int) modeloTablaLibros.getValueAt(row, 0);
                        libroSeleccionado = libroService.buscarLibroPorId(id);
                        actualizarInfoLibro();
                    }
                }
            }
        });
    }

    private void cargarTablas() {
        // Cargar usuarios
        modeloTablaUsuarios.setRowCount(0);
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        for (Usuario u : usuarios) {
            modeloTablaUsuarios.addRow(new Object[]{u.getId(), u.getNombre(), u.getCorreo()});
        }

        // Cargar libros disponibles
        cargarLibrosDisponibles();
    }

    private void cargarLibrosDisponibles() {
        modeloTablaLibros.setRowCount(0);
        List<Libro> libros = libroService.listarLibros();
        for (Libro l : libros) {
            modeloTablaLibros.addRow(new Object[]{l.getId(), l.getTitulo(), l.getAutor(), l.getEstado()});
        }
    }

    private void buscarUsuario() {
        String tipo = (String) comboBusquedaUsuario.getSelectedItem();
        String valor = txtBusquedaUsuario.getText().trim();

        if (valor.isEmpty()) {
            LookAndFeelUtil.mostrarMensaje(this, "Ingrese un valor para buscar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (tipo.equals("ID")) {
            try {
                int id = Integer.parseInt(valor);
                usuarioSeleccionado = usuarioService.buscarUsuarioPorId(id);
            } catch (NumberFormatException e) {
                LookAndFeelUtil.mostrarMensaje(this, "ID inválido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            usuarioSeleccionado = usuarioService.buscarUsuarioPorCorreo(valor);
        }

        if (usuarioSeleccionado == null) {
            LookAndFeelUtil.mostrarMensaje(this, "Usuario no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            lblUsuarioInfo.setText(" ");
        } else {
            actualizarInfoUsuario();
            // Seleccionar en tabla
            for (int i = 0; i < modeloTablaUsuarios.getRowCount(); i++) {
                if ((int) modeloTablaUsuarios.getValueAt(i, 0) == usuarioSeleccionado.getId()) {
                    tablaUsuarios.setRowSelectionInterval(i, i);
                    break;
                }
            }
        }
    }

    private void buscarLibro() {
        String tipo = (String) comboBusquedaLibro.getSelectedItem();
        String valor = txtBusquedaLibro.getText().trim();

        if (valor.isEmpty()) {
            LookAndFeelUtil.mostrarMensaje(this, "Ingrese un valor para buscar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (tipo.equals("ID")) {
            try {
                int id = Integer.parseInt(valor);
                libroSeleccionado = libroService.buscarLibroPorId(id);
            } catch (NumberFormatException e) {
                LookAndFeelUtil.mostrarMensaje(this, "ID inválido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            // Búsqueda por título
            List<Libro> libros = libroService.listarLibros();
            for (Libro l : libros) {
                if (l.getTitulo().toLowerCase().contains(valor.toLowerCase())) {
                    libroSeleccionado = l;
                    break;
                }
            }
        }

        if (libroSeleccionado == null) {
            LookAndFeelUtil.mostrarMensaje(this, "Libro no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            lblLibroInfo.setText(" ");
        } else {
            actualizarInfoLibro();
            // Seleccionar en tabla
            for (int i = 0; i < modeloTablaLibros.getRowCount(); i++) {
                if ((int) modeloTablaLibros.getValueAt(i, 0) == libroSeleccionado.getId()) {
                    tablavoluntarios.setRowSelectionInterval(i, i);
                    break;
                }
            }
        }
    }

    private void actualizarInfoUsuario() {
        if (usuarioSeleccionado != null) {
            int prestamosActivos = prestamoService.contarPrestamosActivosPorUsuario(usuarioSeleccionado.getId());
            lblUsuarioInfo.setText(String.format("✓ Usuario seleccionado: %s (ID: %d, Correo: %s) - Préstamos activos: %d/3",
                    usuarioSeleccionado.getNombre(), usuarioSeleccionado.getId(), usuarioSeleccionado.getCorreo(), prestamosActivos));
            lblUsuarioInfo.setForeground(prestamosActivos >= 3 ? Color.RED : new Color(0, 100, 0));
        }
    }

    private void actualizarInfoLibro() {
        if (libroSeleccionado != null) {
            lblLibroInfo.setText(String.format("✓ Libro seleccionado: %s (ID: %d, Autor: %s) - Estado: %s",
                    libroSeleccionado.getTitulo(), libroSeleccionado.getId(), libroSeleccionado.getAutor(), libroSeleccionado.getEstado()));
            lblLibroInfo.setForeground(libroSeleccionado.getEstado().toString().equals("PRESTADO") ? Color.RED : new Color(0, 100, 0));
        }
    }

    private void confirmarPrestamo() {
        if (usuarioSeleccionado == null) {
            LookAndFeelUtil.mostrarMensaje(this, "Debe seleccionar un usuario", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (libroSeleccionado == null) {
            LookAndFeelUtil.mostrarMensaje(this, "Debe seleccionar un libro", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LocalDate fechaPrestamo = LocalDate.now();
        LocalDate fechaDevolucionEstimada = fechaPrestamo.plusDays(14); // 14 días según regla de negocio

        String resultado = prestamoService.registrarPrestamo(
                usuarioSeleccionado.getId(),
                libroSeleccionado.getId(),
                fechaPrestamo,
                fechaDevolucionEstimada
        );

        txtAreaResultado.setText("=== RESULTADO DEL PRÉSTAMO ===\n" + resultado + "\n\nFecha: " + fechaPrestamo.format(formatter));

        if (resultado.startsWith("ÉXITO")) {
            LookAndFeelUtil.mostrarMensaje(this, resultado, "Préstamo Exitoso", JOptionPane.INFORMATION_MESSAGE);
            // Recargar tablas
            cargarLibrosDisponibles();
            actualizarInfoUsuario();
            usuarioSeleccionado = null;
            libroSeleccionado = null;
            lblUsuarioInfo.setText(" ");
            lblLibroInfo.setText(" ");
            tablaUsuarios.clearSelection();
            tablavoluntarios.clearSelection();
        } else {
            LookAndFeelUtil.mostrarMensaje(this, resultado, "Error en Préstamo", JOptionPane.ERROR_MESSAGE);
        }
    }
}