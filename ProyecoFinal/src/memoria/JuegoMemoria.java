package memoria;

import javax.swing.*;

public class JuegoMemoria {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PantallaInicio inicio = new PantallaInicio();
            inicio.setVisible(true);
        });
    }
}