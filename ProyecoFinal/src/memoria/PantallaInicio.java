package memoria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

public class PantallaInicio extends JFrame {
    private JTextField nombreJugador;

    public PantallaInicio() {
        setTitle("Pantalla de Inicio");
        setSize(800, 600); // Tamaño más grande para visualizar correctamente los logos
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel de contenido
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Título e información
        JLabel informacion = new JLabel("<html><div style='text-align: center;'>"
                + "<span style='font-size: 15px;'>Universidad Tecnológica de Panamá<br>"
                + "Facultad de Sistemas Computacionales<br>"
                + "Ingeniería de Software<br>"
                + "1SF123<br><br>"
                + "Integrantes:<br>"
                + "Juan Zhu - 8-1010-701<br>"
                + "Jeremy Martínez - 8-1024-14701<br>"
                + "Rafael Gómez - 8-1011-1757<br>"
                + "Alex De Bouaud - 8-1015-1644<br><br>"
                + "Profesor: Rodrigo Yángüez<br>"
                + "12/07/2024<br><br>"
                + "</span></div></html>", JLabel.CENTER);
        panel.add(informacion, BorderLayout.CENTER);

        // Logos
        JPanel logosPanel = new JPanel();
        logosPanel.setLayout(new BorderLayout());

        // Logo de la universidad (izquierda)
        try {
            URL urlLogoUni = new URL("https://utp.ac.pa/documentos/2015/imagen/logo_utp_1_72.png"); // URL de la imagen
            ImageIcon iconUni = new ImageIcon(urlLogoUni);
            Image imageUni = iconUni.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Ajustar tamaño
            ImageIcon scaledIconUni = new ImageIcon(imageUni);
            JLabel logoUni = new JLabel(scaledIconUni);
            logoUni.setPreferredSize(new Dimension(100, 100)); // Tamaño preferido
            logosPanel.add(logoUni, BorderLayout.WEST);
        } catch (MalformedURLException e) {
            e.printStackTrace(); // Manejar el error de URL mal formada
        }

        // Logo de la facultad (derecha)
        try {
            URL urlLogoFac = new URL("https://fisc.utp.ac.pa/sites/fisc.utp.ac.pa/files/documentos/2020/imagen/logo_en_contactenos.png"); // URL de la imagen
            ImageIcon iconFac = new ImageIcon(urlLogoFac);
            Image imageFac = iconFac.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Ajustar tamaño
            ImageIcon scaledIconFac = new ImageIcon(imageFac);
            JLabel logoFac = new JLabel(scaledIconFac);
            logoFac.setPreferredSize(new Dimension(100, 100)); // Tamaño preferido
            logosPanel.add(logoFac, BorderLayout.EAST);
        } catch (MalformedURLException e) {
            e.printStackTrace(); // Manejar el error de URL mal formada
        }

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PantallaInicio inicio = new PantallaInicio();
            inicio.setVisible(true);
        });
    }
}
