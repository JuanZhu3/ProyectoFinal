package memoria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class PantallaJuego extends JFrame {
    private String nombreJugador;
    private ArrayList<JButton> cartas = new ArrayList<>();
    private String[] imagenes = new String[20]; // Array para las imágenes
    private boolean[] descubiertas = new boolean[20]; // Para controlar las cartas descubiertas

    public PantallaJuego(String nombreJugador) {
        this.nombreJugador = nombreJugador;
        setTitle("Juego de Memoria");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Cargar imágenes (manejo de excepciones)
        try (BufferedReader reader = new BufferedReader(new FileReader("imagenes.txt"))) {
            for (int i = 0; i < 20; i++) {
                imagenes[i] = reader.readLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las imágenes: " + e.getMessage());
        }

        // Mezclar las cartas
        Collections.shuffle(Arrays.asList(imagenes));

        // Panel de contenido
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 5)); // Para una cuadrícula de 20 cartas

        // Añadir botones de cartas
        for (int i = 0; i < 20; i++) {
            JButton carta = new JButton();
            int indice = i;
            carta.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Lógica para descubrir la carta
                    if (!descubiertas[indice]) {
                        carta.setIcon(new ImageIcon(imagenes[indice])); // Muestra la imagen
                        descubiertas[indice] = true;
                        // Añadir lógica para verificar pares, etc.
                    }
                }
            });
            cartas.add(carta);
            panel.add(carta);
        }

        // Añadir panel al frame
        add(panel);

        // Añadir botón para salir
        JButton salirButton = new JButton("Salir");
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(salirButton, BorderLayout.SOUTH);
    }

    // Métodos adicionales para la lógica del juego
}
