package clinica.modelo;

import java.util.Objects;

/** Paciente de la clínica. Dos pacientes son iguales si tienen el mismo DNI. */
public class Paciente {
    private final String dni;
 private final String nombre;
    private final String apellido;

    public Paciente(String dni, String nombre, String apellido) {
        if (dni == null || dni.isBlank()) {
            throw new IllegalArgumentException("El DNI no puede estar vacío");
        }
        this.dni = dni.trim();
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getDni() { return dni; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }

    // CLAVE para el HashSet: la igualdad depende solo del DNI
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente otro = (Paciente) o;
        return dni.equals(otro.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return apellido + ", " + nombre + " (DNI " + dni + ")";
    }
}
