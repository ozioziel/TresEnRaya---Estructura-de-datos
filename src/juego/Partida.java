package juego;

import java.util.ArrayList;
import java.util.List;

public class Partida {
    private List<Movimiento> movimientos;
    private char ganador;

    public Partida() {
        movimientos = new ArrayList<>();
        ganador = ' '; 
    }

    public void agregarMovimiento(Movimiento m) {
        movimientos.add(m);
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setGanador(char g) {
        ganador = g;
    }

    public char getGanador() {
        return ganador;
    }
}
