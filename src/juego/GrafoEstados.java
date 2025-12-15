package juego;

import java.util.*;

public class GrafoEstados {
    private Map<String, List<String>> grafo = new HashMap<>();

    public void agregarTransicion(String origen, String destino) {
        grafo.putIfAbsent(origen, new ArrayList<>());
        grafo.get(origen).add(destino);
    }

    public Map<String, List<String>> getGrafo() {
        return grafo;
    }
}
