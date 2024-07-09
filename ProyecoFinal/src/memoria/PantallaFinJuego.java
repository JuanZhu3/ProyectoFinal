package memoria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class PantallaFinJuego extends JFrame {
    private ArrayList<Integer> rankingTiempos = new ArrayList<>();

    public PantallaFinJuego(int tiempo) {
        setTitle("Fin del Juego");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Cargar ranking de un archivo
        try (BufferedReader reader = new BufferedReader(new FileReader("ranking.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                rankingTiempos.add(Integer.parseInt(linea));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el ranking: " + e.getMessage());
        }

        // Agregar tiempo al ranking
        rankingTiempos.add(tiempo);
        Collections.sort(rankingTiempos);

        // Guardar ranking en un archivo
        try (FileWriter writer = new FileWriter("ranking.txt")) {
            for (int t : rankingTiempos) {
                writer.write(t + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el ranking: " + e.getMessage());
        }

        // Panel de contenido
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Mostrar mensaje de felicitaciones
        JLabel mensaje = new JLabel("¡Felicidades! Completaste el juego en " + tiempo + " segundos.", JLabel.CENTER);
        panel.add(mensaje, BorderLayout.NORTH);

        // Mostrar ranking
        JTextArea rankingArea = new JTextArea();
        rankingArea.setEditable(false);
        StringBuilder rankingTexto = new StringBuilder("Ranking de tiempos:\n");
        for (int t : rankingTiempos) {
            rankingTexto.append(t).append(" segundos\n");
        }
        rankingArea.setText(rankingTexto.toString());
        panel.add(new JScrollPane(rankingArea), BorderLayout.CENTER);

        // Panel para botones
        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new FlowLayout());

        // Botón para salir
        JButton salirButton = new JButton("Salir");
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        botonesPanel.add(salirButton);

        // Botón para volver a jugar
        JButton jugarDeNuevoButton = new JButton("Jugar de nuevo");
        jugarDeNuevoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaDescripcion descripcion = new PantallaDescripcion();
                descripcion.setVisible(true);
                dispose();
            }
        });
        botonesPanel.add(jugarDeNuevoButton);

        // Añadir botones al panel principal
        panel.add(botonesPanel, BorderLayout.SOUTH);

        // Añadir panel al frame
        add(panel);
    }
}
