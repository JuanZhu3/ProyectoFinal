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
    private int indicePrimeraCarta = -1;
    private int indiceSegundaCarta = -1;

    public PantallaJuego(String nombreJugador) {
        this.nombreJugador = nombreJugador;
        setTitle("Juego de Memoria");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Definir URLs de las imágenes
        String[] urls = {
            "https://w7.pngwing.com/pngs/722/1011/png-transparent-logo-icon-instagram-logo-instagram-logo-purple-violet-text-thumbnail.png",
            "https://w7.pngwing.com/pngs/110/230/png-transparent-whatsapp-application-software-message-icon-whatsapp-logo-whats-app-logo-logo-grass-mobile-phones-thumbnail.png",
            "https://w7.pngwing.com/pngs/208/269/png-transparent-youtube-play-button-computer-icons-youtube-youtube-logo-angle-rectangle-logo-thumbnail.png",
            "https://w7.pngwing.com/pngs/213/828/png-transparent-facebook-logo-facebook-messenger-logo-social-media-icon-facebook-icon-blue-text-rectangle-thumbnail.png",
            "https://w7.pngwing.com/pngs/975/52/png-transparent-white-telephone-logo-computer-icons-telephone-mobile-phones-telephone-number-miscellaneous-text-telephone-call-thumbnail.png",
            "https://w7.pngwing.com/pngs/814/840/png-transparent-tiktok-tiktok-logo-tiktok-icon-thumbnail.png",
            "https://w7.pngwing.com/pngs/708/311/png-transparent-icon-logo-twitter-logo-twitter-logo-blue-social-media-area-thumbnail.png",
            "https://w7.pngwing.com/pngs/745/644/png-transparent-email-logo-icon-email-black-envelope-logo-miscellaneous-text-mobile-phones-thumbnail.png",
            "https://w7.pngwing.com/pngs/654/821/png-transparent-swoosh-nike-just-do-it-logo-nike-angle-adidas-symbol-thumbnail.png",
            "https://w7.pngwing.com/pngs/752/373/png-transparent-computer-icons-facebook-logo-facebook-logo-fine-art-thumbnail.png",
        };

        // Duplicar las URLs si es necesario para alcanzar las 20 cartas
        while (imagenes.size() < 20) {
            imagenes.addAll(Arrays.asList(urls));
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
                    manejarClickCarta(indice, carta);
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

    private void manejarClickCarta(int indice, JButton carta) {
        if (descubiertas[indice]) {
            return;
        }

        carta.setIcon(new ImageIcon(new ImageIcon(imagenes.get(indice)).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH))); // Mostrar imagen desde URL
        descubiertas[indice] = true;

        if (indicePrimeraCarta == -1) {
            indicePrimeraCarta = indice;
        } else if (indiceSegundaCarta == -1) {
            indiceSegundaCarta = indice;
            verificarPares();
        }
    }

    private void verificarPares() {
        if (imagenes.get(indicePrimeraCarta).equals(imagenes.get(indiceSegundaCarta))) {
            indicePrimeraCarta = -1;
            indiceSegundaCarta = -1;
        } else {
            Timer temporizador = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cartas.get(indicePrimeraCarta).setIcon(null);
                    cartas.get(indiceSegundaCarta).setIcon(null);
                    descubiertas[indicePrimeraCarta] = false;
                    descubiertas[indiceSegundaCarta] = false;
                    indicePrimeraCarta = -1;
                    indiceSegundaCarta = -1;
                }
            });
            temporizador.setRepeats(false);
            temporizador.start();
        }
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
}
