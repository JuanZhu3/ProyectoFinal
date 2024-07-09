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
        StringBuilder rankingTexto = new StringBuilder("Ranking:\n");
        for (int i = 0; i < rankingTiempos.size(); i++) {
            rankingTexto.append((i + 1) + ". " + rankingTiempos.get(i) + " segundos\n");
        }
        rankingArea.setText(rankingTexto.toString());
        rankingArea.setEditable(false);
        panel.add(new JScrollPane(rankingArea), BorderLayout.CENTER);

        // Botón para salir
        JButton salirButton = new JButton("Salir");
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panel.add(salirButton, BorderLayout.SOUTH);

        // Añadir panel al frame
        add(panel);
    }
}
