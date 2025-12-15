package juego;

import javax.swing.*;
import java.awt.*;

public class JuegoUI extends JFrame {

    private JButton[][] botones = new JButton[3][3];
    private JLabel estado = new JLabel("Turno: X", SwingConstants.CENTER);
    private JuegoLogica juego;

    public JuegoUI(JuegoLogica juego) {
        this.juego = juego;

        setTitle("Tres en Raya");
        setSize(400, 450);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 3));
        panel.setBackground(new Color(230, 230, 250)); // lavanda pastel

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                final int f = i, c = j;
                botones[i][j] = new JButton(" ");
                botones[i][j].setFont(new Font("Arial", Font.BOLD, 40));
                botones[i][j].setBackground(new Color(176, 196, 222)); // azul pastel
                botones[i][j].setForeground(new Color(75,0,130));       // morado oscuro
                botones[i][j].addActionListener(e -> jugar(f, c));
                panel.add(botones[i][j]);
            }

        JButton reset = new JButton("Reiniciar");
        reset.setBackground(new Color(221,160,221)); // lila pastel
        reset.setForeground(new Color(75,0,130));
        reset.addActionListener(e -> {
            juego.reiniciar();
            actualizarTablero();
        });

        estado.setFont(new Font("Arial", Font.BOLD, 18));
        estado.setForeground(new Color(138, 43, 226)); // morado pastel

        add(estado, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(reset, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void jugar(int f, int c) {
        try {
            if (!juego.jugar(f, c)) {
                JOptionPane.showMessageDialog(this, "Casilla ocupada");
                return;
            }

            actualizarTablero();

            if (juego.hayGanador('X')) {
                JOptionPane.showMessageDialog(this, "¡Ganó X!");
                juego.finalizarPartida('X');
                return;
            }

            if (juego.hayGanador('O')) {
                JOptionPane.showMessageDialog(this, "¡Ganó O!");
                juego.finalizarPartida('O');
                return;
            }

            if (juego.empate()) {
                JOptionPane.showMessageDialog(this, "Empate");
                juego.finalizarPartida('E');
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al jugar: " + e.getMessage());
        }
    }

    private void actualizarTablero() {
        char[][] t = juego.getTablero();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                botones[i][j].setText(String.valueOf(t[i][j]));

        estado.setText("Turno: " + juego.getJugadorActual());
    }
}
