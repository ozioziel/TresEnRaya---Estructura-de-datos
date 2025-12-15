package juego;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class HistorialUI extends JFrame {

    private JTextArea areaHistorial = new JTextArea();
    private JButton btnEliminar = new JButton("Eliminar última partida");
    private JuegoLogica juegoLogica;

    public HistorialUI(JuegoLogica juegoLogica) {
        this.juegoLogica = juegoLogica;

        setTitle("Historial de partidas");
        setSize(400, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        areaHistorial.setEditable(false);
        areaHistorial.setBackground(new Color(230, 230, 250)); // lavanda pastel
        areaHistorial.setForeground(new Color(75,0,130));       // morado oscuro
        areaHistorial.setFont(new Font("Arial", Font.PLAIN, 14));
        add(new JScrollPane(areaHistorial), BorderLayout.CENTER);

        btnEliminar.setBackground(new Color(176, 196, 222)); // azul pastel
        btnEliminar.setForeground(new Color(75,0,130));      // morado oscuro
        btnEliminar.addActionListener(e -> {
            juegoLogica.eliminarUltimaPartida();
            mostrarHistorial();
        });

        add(btnEliminar, BorderLayout.SOUTH);

        mostrarHistorial();
        setVisible(true);
    }

    private void mostrarHistorial() {
        areaHistorial.setText("");
        Stack<Partida> pila = juegoLogica.getHistorialPartidas();
        if (pila.isEmpty()) {
            areaHistorial.setText("No hay partidas jugadas.");
            return;
        }

        int num = 1;
        for (Partida p : pila) {
            areaHistorial.append("Partida " + num + " - Ganador: " + p.getGanador() + "\n");
            for (Movimiento m : p.getMovimientos()) {
                areaHistorial.append("  " + m.jugador + " en (" + m.fila + "," + m.columna + ")\n");
            }
            num++;
            areaHistorial.append("\n");
        }
    }
}
