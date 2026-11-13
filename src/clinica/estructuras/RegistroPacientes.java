package clinica.estructuras;

import clinica.modelo.Paciente;
import java.util.HashSet;
import java.util.Set;

/** Padrón único de pacientes (Set): no admite DNI repetidos. */
public class RegistroPacientes {
    private final Set<Paciente> padron = new HashSet<>();

    /** add() devuelve false si ya hay un paciente con ese DNI (usa equals/hashCode). O(1). */
    public boolean registrar(Paciente p) {
        return padron.add(p);
    }

    /** contains() también usa hashCode/equals: O(1), sin recorrer el Set. */
    public boolean existe(String dni) {
        if (dni == null || dni.isBlank()) return false; // un DNI vacío no puede estar registrado
        return padron.contains(new Paciente(dni, "", ""));
    }

    /**
     * Para recuperar el objeto hay que recorrer (O(n)): un Set responde "¿está?", no "dámelo".
     * Si se necesitara buscar muy rápido por DNI, lo indicado sería un Map<String, Paciente> (Clase 7).
     */
    public Paciente buscar(String dni) {
        if (!existe(dni)) return null;
        for (Paciente p : padron) {
            if (p.getDni().equals(dni.trim())) return p;
        }
        return null;
    }

    /** Usado por "Deshacer" para revertir un alta. */
    public boolean eliminar(Paciente p) {
        return padron.remove(p);
    }

    public int cantidad() {
        return padron.size();
    }
}
