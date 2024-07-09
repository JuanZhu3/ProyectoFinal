package memoria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        // Título e información
        JLabel informacion = new JLabel("<html><div style='text-align: center;'>"
                + "Universidad Tecnológica de Panamá<br>"
                + "Facultad de Sistemas Computacionales<br>"
                + "Ingeniería de Software<br>"
                + "1SF123<br><br>"
                + "Integrantes:<br>"
                + "Juan Zhu   8-1010-701<br>"
                + "Jeremy Martínez   8-1024-1470<br>"
                + "Rafael Gómez   8-1011-1757<br>"
                + "Alex De Boutad   8-1015-1644<br><br>"
                + "Profesor: Rodrigo Yángüez<br>"
                + "12/07/2024<br><br>"
                + "</div></html>", JLabel.CENTER);
        panel.add(informacion, BorderLayout.CENTER);

        // Logos
        JPanel logosPanel = new JPanel();
        logosPanel.setLayout(new GridLayout(1, 2));
        JLabel logoUni = new JLabel(new ImageIcon("/mnt/data/logo_universidad.png")); // Ruta de la imagen
        JLabel logoFac = new JLabel(new ImageIcon("/mnt/data/logo_facultad.png")); // Ruta de la imagen
        logosPanel.add(logoUni);
        logosPanel.add(logoFac);
        panel.add(logosPanel, BorderLayout.NORTH);

        // Botón para avanzar
        JButton avanzarButton = new JButton("Avanzar");
        avanzarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaDescripcion descripcion = new PantallaDescripcion();
                descripcion.setVisible(true);
                dispose();
            }
        });
        panel.add(avanzarButton, BorderLayout.SOUTH);

        // Añadir panel al frame
        add(panel);
    }
}
