package com.biblioteca.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainUI extends JFrame {

    private JTabbedPane tabbedPane;
    private PrestamoPanel prestamoPanel;
    private UsuarioCRUDPanel usuarioCRUDPanel;

    public MainUI() {
        setTitle("Sistema de Gestión Biblioteca - MVP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);

        // Configurar LookAndFeel
        LookAndFeelUtil.configurarLookAndFeel();

        inicializarComponentes();

        setVisible(true);
    }

    private void inicializarComponentes() {
        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 248, 255));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Título
        JLabel titulo = new JLabel("BIBLIOTECA UNIVERSITARIA - MVP", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setForeground(new Color(70, 130, 200));
        titulo.setBorder(new EmptyBorder(10, 0, 20, 0));
        mainPanel.add(titulo, BorderLayout.NORTH);

        // TabbedPane con las dos pantallas principales
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // Pantalla 1: Registrar Préstamo (Transacción de negocio)
        prestamoPanel = new PrestamoPanel();
        JScrollPane scrollPrestamo = new JScrollPane(prestamoPanel);
        scrollPrestamo.setBorder(null);
        tabbedPane.addTab("📚 REGISTRAR PRÉSTAMO", scrollPrestamo);

        // Pantalla 2: CRUD de Usuarios
        usuarioCRUDPanel = new UsuarioCRUDPanel();
        JScrollPane scrollCRUD = new JScrollPane(usuarioCRUDPanel);
        scrollCRUD.setBorder(null);
        tabbedPane.addTab("👤 CRUD USUARIOS", scrollCRUD);

        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        // Barra de estado
        JLabel statusBar = new JLabel("✅ Sistema listo | Transacción: Registrar Préstamo | CRUD: Usuarios");
        statusBar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        statusBar.setForeground(Color.GRAY);
        statusBar.setBorder(new EmptyBorder(10, 10, 5, 10));
        mainPanel.add(statusBar, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainUI();
        });
    }
}