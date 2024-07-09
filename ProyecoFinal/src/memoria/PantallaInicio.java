package memoria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PantallaInicio extends JFrame {
    private JTextField nombreJugador;

    public PantallaInicio() {
        setTitle("Pantalla de Inicio");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel de contenido
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("<html><h1>Nombre de la Universidad</h1><h2>Facultad</h2></html>", JLabel.CENTER);
        panel.add(titulo, BorderLayout.NORTH);

        // Logos (puedes añadir tus imágenes aquí)
        JPanel logosPanel = new JPanel();
        logosPanel.setLayout(new GridLayout(1, 2));
        JLabel logoUni = new JLabel(new ImageIcon("logo_universidad.png")); // Reemplaza con la ruta de tu imagen
        JLabel logoFac = new JLabel(new ImageIcon("logo_facultad.png")); // Reemplaza con la ruta de tu imagen
        logosPanel.add(logoUni);
        logosPanel.add(logoFac);
        panel.add(logosPanel, BorderLayout.CENTER);

        // Nombre del jugador
        JPanel nombrePanel = new JPanel();
        nombrePanel.setLayout(new GridLayout(2, 1));
        JLabel nombreLabel = new JLabel("Ingrese su nombre:", JLabel.CENTER);
        nombreJugador = new JTextField();
        nombrePanel.add(nombreLabel);
        nombrePanel.add(nombreJugador);
        panel.add(nombrePanel, BorderLayout.SOUTH);

        // Botón para empezar el juego
        JButton empezarButton = new JButton("Empezar Juego");
        empezarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreJugador.getText();
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese su nombre.");
                } else {
                    PantallaJuego pantallaJuego = new PantallaJuego(nombre);
                    pantallaJuego.setVisible(true);
                    dispose();
                }
            }
        });
        panel.add(empezarButton, BorderLayout.EAST);

        // Añadir panel al frame
        add(panel);
    }
}
