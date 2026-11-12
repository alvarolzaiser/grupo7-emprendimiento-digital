package clinica.modelo;

/**
 * Ingreso a guardia. La gravedad es un dato de esta clase y no del Paciente:
 * así un dato de la guardia no modifica al paciente mientras está dentro de la PriorityQueue.
 * Un paciente no necesariamente tiene que tener una urgencia, pero una urgencia siempre tiene una gravedad y un paciente asociado.
 */
public class Urgencia {
    private final Paciente paciente; // Aun no definido el tipo Paciente, pero estara en el futuro.
    private final int gravedad;     // 1 (mas bajo) a 5 (código rojo o mas alto)
    private final long ordenLlegada; // desempate: a igual gravedad, primero el que llegó antes

    public Urgencia(Paciente paciente, int gravedad, long ordenLlegada) {
        if (gravedad < 1 || gravedad > 5) {
            throw new IllegalArgumentException("La gravedad debe estar entre 1 y 5");
        }
        this.paciente = paciente;
        this.gravedad = gravedad;
        this.ordenLlegada = ordenLlegada;
    }

    // Metodos getters  
    public Paciente getPaciente() { 
        return paciente; 
    }
    public int getGravedad() { 
        return gravedad; 
    }
    public long getOrdenLlegada() { 
        return ordenLlegada; 
    }

    // Sobreescritura del método toString para mostrar la información de la urgencia cuando se imprime un objeto o instancia de esta clase
    // Cuando imprimimos un objeto o instancia de Urgencia, se mostrará el string que retornamos en este método, en lugar del comportamiento por defecto que muestra el nombre de la clase y el hashcode.
    @Override
    public String toString() {
        return "Gravedad " + gravedad + " - " + paciente;
    }
}