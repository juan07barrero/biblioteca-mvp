package com.biblioteca.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class LookAndFeelUtil {

    public static void configurarLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            // Colores personalizados - SIN forzar colores de texto
            UIManager.put("Panel.background", new Color(240, 248, 255));
            UIManager.put("Button.background", new Color(70, 130, 200));
            UIManager.put("Button.foreground", Color.BLACK);  // ← Letras NEGRAS en botones
            UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 12));

            UIManager.put("Label.foreground", Color.BLACK);    // ← Letras NEGRAS en etiquetas
            UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 12));

            UIManager.put("TextField.foreground", Color.BLACK); // ← Texto NEGRO en campos
            UIManager.put("TextField.font", new Font("Segoe UI", Font.PLAIN, 12));

            UIManager.put("TextArea.foreground", Color.BLACK);  // ← Texto NEGRO en áreas de texto
            UIManager.put("TextArea.font", new Font("Segoe UI", Font.PLAIN, 12));

            UIManager.put("Table.foreground", Color.BLACK);     // ← Texto NEGRO en tablas
            UIManager.put("Table.font", new Font("Segoe UI", Font.PLAIN, 12));
            UIManager.put("TableHeader.foreground", Color.BLACK); // ← Texto NEGRO en cabeceras
            UIManager.put("TableHeader.font", new Font("Segoe UI", Font.BOLD, 12));

            UIManager.put("TabbedPane.foreground", Color.BLACK); // ← Texto NEGRO en pestañas
            UIManager.put("TabbedPane.font", new Font("Segoe UI", Font.BOLD, 12));

            // Colores de fondo para inputs
            UIManager.put("TextField.background", Color.WHITE);
            UIManager.put("TextArea.background", Color.WHITE);
            UIManager.put("Table.background", Color.WHITE);

        } catch (Exception e) {
            System.out.println("No se pudo configurar el LookAndFeel: " + e.getMessage());
        }
    }

    public static JButton crearBoton(String texto, Color colorFondo) {
        JButton boton = new JButton(texto);
        boton.setBackground(colorFondo);
        boton.setForeground(Color.BLACK);  // ← Letras NEGRAS en botones
        boton.setFocusPainted(false);
        boton.setBorder(new EmptyBorder(8, 15, 8, 15));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        return boton;
    }

    public static JPanel crearPanelConBorde(String titulo) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(new Color(70, 130, 200), 2),
                titulo,
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 14),
                Color.BLACK  // ← Título del borde en NEGRO
        ));
        panel.setBackground(new Color(240, 248, 255));
        return panel;
    }

    public static void mostrarMensaje(Component parent, String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(parent, mensaje, titulo, tipo);
    }

    public static int mostrarConfirmacion(Component parent, String mensaje, String titulo) {
        return JOptionPane.showConfirmDialog(parent, mensaje, titulo, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }
}