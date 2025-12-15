package juego;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static JuegoLogica juegoLogica = new JuegoLogica(); 
    public static void main(String[] args) {
        JFrame inicio = new JFrame("¡Bienvenidos a 3 en Raya!");
        inicio.setSize(400, 300);
        inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicio.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("¡Bienvenidos a 3 en Raya!", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(new Color(138, 43, 226)); 

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(230, 230, 250)); 

        JButton btnJuego = new JButton("Empezar Juego");
        JButton btnHistorial = new JButton("Ver Historial");

        btnJuego.setBackground(new Color(176, 196, 222)); 
        btnJuego.setForeground(new Color(75,0,130));       

        btnHistorial.setBackground(new Color(221,160,221));
        btnHistorial.setForeground(new Color(75,0,130));    

        panelBotones.add(btnJuego);
        panelBotones.add(btnHistorial);

        inicio.add(titulo, BorderLayout.NORTH);
        inicio.add(panelBotones, BorderLayout.CENTER);
        inicio.setVisible(true);

        btnJuego.addActionListener(e -> new JuegoUI(juegoLogica));

        btnHistorial.addActionListener(e -> new HistorialUI(juegoLogica));
    }
}
