package juego;

import java.util.*;

public class JuegoLogica {

    private char[][] tablero = new char[3][3];
    private boolean turnoX = true;

    private Partida partidaActual;
    private Stack<Partida> historialPartidas = new Stack<>();
    private GrafoEstados grafo = new GrafoEstados();

    public JuegoLogica() {
        reiniciar();
    }

    public boolean jugar(int f, int c) {
        try {
            if (tablero[f][c] != ' ')
                return false;

            char jugador = turnoX ? 'X' : 'O';
            tablero[f][c] = jugador;

            Movimiento m = new Movimiento(f, c, jugador);
            partidaActual.agregarMovimiento(m);

            turnoX = !turnoX;
            return true;
        } catch (Exception e) {
            System.out.println("Error en jugar: " + e.getMessage());
            return false;
        }
    }

    public void finalizarPartida(char ganador) {
        try {
            partidaActual.setGanador(ganador);
            historialPartidas.push(partidaActual);
        } catch (Exception e) {
            System.out.println("Error al finalizar partida: " + e.getMessage());
        }
    }

    public Stack<Partida> getHistorialPartidas() {
        return historialPartidas;
    }

    public char[][] getTablero() {
        return tablero;
    }

    public char getJugadorActual() {
        return turnoX ? 'X' : 'O';
    }

    public boolean hayGanador(char j) {
        try {
            for (int i = 0; i < 3; i++)
                if (tablero[i][0] == j && tablero[i][1] == j && tablero[i][2] == j)
                    return true;

            for (int i = 0; i < 3; i++)
                if (tablero[0][i] == j && tablero[1][i] == j && tablero[2][i] == j)
                    return true;

            return (tablero[0][0] == j && tablero[1][1] == j && tablero[2][2] == j)
                || (tablero[0][2] == j && tablero[1][1] == j && tablero[2][0] == j);
        } catch (Exception e) {
            System.out.println("Error al verificar ganador: " + e.getMessage());
            return false;
        }
    }

    public boolean empate() {
        try {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (tablero[i][j] == ' ')
                        return false;
            return true;
        } catch (Exception e) {
            System.out.println("Error al verificar empate: " + e.getMessage());
            return false;
        }
    }

    public void reiniciar() {
        try {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    tablero[i][j] = ' ';

            turnoX = true;
            partidaActual = new Partida();
        } catch (Exception e) {
            System.out.println("Error al reiniciar: " + e.getMessage());
        }
    }

    public void eliminarUltimaPartida() {
        try {
            if (!historialPartidas.isEmpty())
                historialPartidas.pop();
        } catch (Exception e) {
            System.out.println("Error al eliminar última partida: " + e.getMessage());
        }
    }

    public int contarMovimientosRecursivo(int index) {
        if (index >= partidaActual.getMovimientos().size())
            return 0;
        return 1 + contarMovimientosRecursivo(index + 1);
    }

    public void ordenarMovimientos() {
        try {
            List<Movimiento> lista = partidaActual.getMovimientos();
            for (int i = 0; i < lista.size() - 1; i++) {
                for (int j = 0; j < lista.size() - i - 1; j++) {
                    if (lista.get(j).fila > lista.get(j + 1).fila) {
                        Movimiento temp = lista.get(j);
                        lista.set(j, lista.get(j + 1));
                        lista.set(j + 1, temp);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al ordenar movimientos: " + e.getMessage());
        }
    }
}
