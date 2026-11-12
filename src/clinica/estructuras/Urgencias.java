package clinica.estructuras;

import clinica.modelo.Paciente;
import clinica.modelo.Urgencia;
import java.util.Comparator;
import java.util.PriorityQueue;

/** Guardia ordenada por gravedad (PriorityQueue). A igual gravedad, se usa el orden de llegada. */
public class Urgencias {
    private final PriorityQueue<Urgencia> guardia = new PriorityQueue<>(
            Comparator.comparingInt(Urgencia::getGravedad).reversed()   // Urgencia::getGravedad indica donde esta el dato por el cual se ordena la cola de prioridad, en este caso la gravedad del paciente. Se usa reversed() para que el paciente con mayor gravedad tenga mayor prioridad, sino ordenaría de menor a mayor gravedad
                      .thenComparingLong(Urgencia::getOrdenLlegada));  // Urgencia::getOrdenLlegada indica donde esta el dato por el cual se ordena la cola de prioridad, en este caso el orden de llegada del paciente. Se usa thenComparingLong() para que a igual gravedad, el paciente que llegó primero tenga mayor prioridad

    private long contadorLlegadas = 0; // Contador para asignar un orden de llegada a cada urgencia, en caso que dos pacientes tengan la misma gravedad, se usa el menor orden de llegada para determinar quien se atiende primero.

    // Lanza IllegalArgumentException si la gravedad no está entre 1 y 5. Eso esta definido en la clase Urgencia.
    public Urgencia ingresar(Paciente p, int nivelGravedad) {
        Urgencia u = new Urgencia(p, nivelGravedad, contadorLlegadas++);
        guardia.offer(u); // O(log n)
        return u;
    }

    // Saca siempre al más grave, o null si no hay nadie.
    // poll() devuelve y elimina el elemento de mayor prioridad de la cola, o null si la cola está vacía. En este caso, devuelve la urgencia del paciente más grave que está en la guardia y lo elimina de la cola
    public Urgencia atenderUrgencia() {
        return guardia.poll(); // O(log n)
    }

    // peek() devuelve el elemento de mayor prioridad sin eliminarlo de la cola, o null si la cola está vacía. En este caso, devuelve la urgencia del paciente más grave que está en la guardia.
    public Urgencia verSiguiente() {
        return guardia.peek(); // O(1)
    }

    // Recibe un paciente y devuelve true si está en la guardia, false si no. O(n) porque hay que recorrer toda la cola de prioridad para buscar al paciente.
    public boolean estaEnGuardia(Paciente p) {
        for (Urgencia u : guardia) {
            if (u.getPaciente().equals(p)) return true;
        }
        return false;
    }

    // Va a ser utilizado para la funcion "Deshacer". Devuelve false si ya fue atendido.
    // el metodo remove() de PriorityQueue elimina el elemento especificado de la cola, si está presente. Devuelve true si la cola contenía el elemento especificado (es decir, si se eliminó), o false si no estaba presente.
    public boolean quitar(Urgencia u) {
        return guardia.remove(u);
    }

    // Sirve para determinar si la guardia está vacía o no mediante el método isEmpty() de PriorityQueue, que devuelve true si la cola está vacía y false si contiene elementos.
    public boolean estaVacia() {
        return guardia.isEmpty();
    }

    // Devulve la cantidad de urgencias que tiene la PriorityQueue guardia mediante el método size() de PriorityQueue, que devuelve el número de elementos en la cola.
    public int cantidad() {
        return guardia.size();
    }
}