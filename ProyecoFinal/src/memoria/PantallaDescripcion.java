package memoria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaDescripcion extends JFrame {
    private JTextField nombreJugador;

    public PantallaDescripcion() {
        setTitle("Memoria");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Memoria", JLabel.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 24));
        panel.add(titulo, BorderLayout.NORTH);

        // Panel dividido para descripción y nombre
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setResizeWeight(0.7); // Ajusta el tamaño relativo de las áreas divididas

        // Descripción del juego
        JTextArea descripcion = new JTextArea(
                "Descripción del juego:\n\n"
                        + "El objetivo del juego de memoria es encontrar los pares de cartas idénticas. "
                        + "En cada turno, el jugador voltea dos cartas. Si las cartas coinciden, se quedan "
                        + "volteadas hacia arriba. Si no coinciden, se voltean nuevamente. El juego continúa "
                        + "hasta que se hayan encontrado todos los pares de cartas."
        );
        descripcion.setEditable(false);
        descripcion.setWrapStyleWord(true);
        descripcion.setLineWrap(true);

        // Panel para ingresar el nombre
        JPanel nombrePanel = new JPanel();
        nombrePanel.setLayout(new FlowLayout());
        JLabel nombreLabel = new JLabel("Ingrese su nombre:");
        nombreJugador = new JTextField(20);
        nombrePanel.add(nombreLabel);
        nombrePanel.add(nombreJugador);

        // Agregar componentes al panel dividido
        splitPane.setTopComponent(new JScrollPane(descripcion));
        splitPane.setBottomComponent(nombrePanel);

        panel.add(splitPane, BorderLayout.CENTER);

        // Botón para comenzar el juego
        JButton comenzarButton = new JButton("Comenzar");
        comenzarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreJugador.getText();
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese su nombre.");
                } else {
                    PantallaJuego pantallaJuego = new PantallaJuego(nombre);
                    pantallaJuego.setVisible(true);
                    dispose();  // Cierra la pantalla actual
                }
            }
        });
        panel.add(comenzarButton, BorderLayout.SOUTH);

        // Agregar panel al frame
        add(panel);
    }
}
