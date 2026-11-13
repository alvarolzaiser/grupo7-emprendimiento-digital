package clinica.estructuras;

import java.util.ArrayDeque;
import java.util.Deque;

/** Pila de acciones (Stack, LIFO) para "Deshacer". */
public class Historial {
    private final Deque<Accion> pila = new ArrayDeque<>();
    // se usó Deque y no Stack porque investigando sobre Java, vimos que Stack es una clase obsoleta, y Deque es la recomendada para implementar pilas y colas.

    // suma una Acción a la pila
    public void apilar(Accion a) {
        pila.push(a); // arriba de la pila, O(1)
    }

    /** Saca la última acción, o null si no hay ninguna. */
    // devuelve una Accion, para luego con esa Accion poder acceder a los datos que se guardaron de la Accion (si fue un turno, alta o urgencia), y asi deshacer realmente esa acción de la estructura que corresponda (alta, turno o urgencia).
    public Accion desapilar() {
        return pila.isEmpty() ? null : pila.pop(); // O(1)
    }

    // devuelve true si la pila esta vacia, false si tiene al menos una accion
    public boolean estaVacio() {
        return pila.isEmpty();
    }

    // devuelve la cantidad de "Acciones" que hay en la pila
    public int cantidad() {
        return pila.size();
    }
}