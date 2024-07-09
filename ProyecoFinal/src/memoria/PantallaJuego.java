package memoria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PantallaJuego extends JFrame {
    private String nombreJugador;
    private List<JButton> cartas = new ArrayList<>();
    private List<String> imagenes = new ArrayList<>();
    private boolean[] descubiertas;
    private JLabel cronometroLabel;
    private Timer timer;
    private int segundosTranscurridos;

    public PantallaJuego(String nombreJugador) {
        this.nombreJugador = nombreJugador;
        setTitle("Juego de Memoria");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Definir URLs de las imágenes
        String[] urls = {
                "https://cdn.tutsplus.com/cdn-cgi/image/width=600/psd/uploads/legacy/psdtutsarticles/linkb_60vgamecovers/42.jpg",
                "https://cdn.tutsplus.com/cdn-cgi/image/width=600/psd/uploads/legacy/psdtutsarticles/linkb_60vgamecovers/40.jpg",
                "https://cdn.tutsplus.com/cdn-cgi/image/width=600/psd/uploads/legacy/psdtutsarticles/linkb_60vgamecovers/24.jpg",
                "https://cdn.tutsplus.com/cdn-cgi/image/width=630/psd/uploads/legacy/psdtutsarticles/linkb_60vgamecovers/22.jpg",
                "https://cdn.tutsplus.com/cdn-cgi/image/width=630/psd/uploads/legacy/psdtutsarticles/linkb_60vgamecovers/21.jpg",
                "https://cdn.tutsplus.com/cdn-cgi/image/width=630/psd/uploads/legacy/psdtutsarticles/linkb_60vgamecovers/14.jpg",
                "https://cdn.tutsplus.com/cdn-cgi/image/width=630/psd/uploads/legacy/psdtutsarticles/linkb_60vgamecovers/15.jpg",
                "https://cdn.tutsplus.com/cdn-cgi/image/width=600/psd/uploads/legacy/psdtutsarticles/linkb_60vgamecovers/16.jpg",
                "https://cdn.tutsplus.com/cdn-cgi/image/width=600/psd/uploads/legacy/psdtutsarticles/linkb_60vgamecovers/doomfordos.jpg",
                "https://cdn.tutsplus.com/cdn-cgi/image/width=630/psd/uploads/legacy/psdtutsarticles/linkb_60vgamecovers/7.jpg",
        };

        // Agregar URLs a la lista de imágenes
        imagenes.addAll(Arrays.asList(urls));

        // Validar que se hayan definido suficientes URLs
        if (imagenes.size() < 10) {
            JOptionPane.showMessageDialog(null, "No se han definido suficientes URLs de imágenes.");
            dispose();
            return;
        }

        // Mezclar las imágenes
        Collections.shuffle(imagenes);

        // Inicializar el array de descubiertas
        descubiertas = new boolean[20];

        // Panel de contenido
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 5)); // Para una cuadrícula de 20 cartas

        // Añadir botones de cartas
        for (int i = 0; i < 20; i++) {
            JButton carta = new JButton();
            final int indice = i;
            carta.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Lógica para descubrir la carta
                    if (!descubiertas[indice]) {
                        carta.setIcon(new ImageIcon(imagenes.get(indice))); // Mostrar imagen desde URL
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

        // Añadir etiqueta para el cronómetro
        cronometroLabel = new JLabel("Tiempo: 00:00:00");
        cronometroLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cronometroLabel.setFont(new Font("Serif", Font.BOLD, 20));
        add(cronometroLabel, BorderLayout.NORTH);

        // Añadir botón para salir
        JButton salirButton = new JButton("Salir");
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detenerCronometro();
                System.exit(0);
            }
        });
        add(salirButton, BorderLayout.SOUTH);

        // Iniciar el cronómetro
        iniciarCronometro();
    }

    private void iniciarCronometro() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                segundosTranscurridos++;
                actualizarCronometro();
            }
        });
        timer.start();
    }

    private void detenerCronometro() {
        if (timer != null) {
            timer.stop();
        }
    }

    private void actualizarCronometro() {
        int horas = segundosTranscurridos / 3600;
        int minutos = (segundosTranscurridos % 3600) / 60;
        int segundos = segundosTranscurridos % 60;
        cronometroLabel.setText(String.format("Tiempo: %02d:%02d:%02d", horas, minutos, segundos));
    }

    // Métodos adicionales para la lógica del juego
}
