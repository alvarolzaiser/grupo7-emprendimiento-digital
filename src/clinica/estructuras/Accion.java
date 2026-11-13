package clinica.estructuras;

import clinica.modelo.Paciente;
import clinica.modelo.Turno;
import clinica.modelo.Urgencia;

/** Registro de una operación de recepción, guardado en el Historial para poder deshacerla. */
public class Accion {
    // Todos los tipos de Acciones que pueden ser desechas.
    public enum Tipo { 
        ALTA_PACIENTE, 
        TURNO_REGULAR, 
        INGRESO_URGENCIA 
    }

    private final Tipo tipo;
    private final Paciente paciente;
    private final Turno turno;       // solo en TURNO_REGULAR, null en los otros casos
    private final Urgencia urgencia; // solo en INGRESO_URGENCIA, null en los otros casos

    // es privado para que no se puede instanciar desde fuera esta clase, solamente llamando a los "constructores con nombre" (metodo estaticos que se encargan de generar la instancia con los datos que correspondan segun el metodo estatico que esta instanciando, y que tipo de accion genera).
    private Accion(Tipo tipo, Paciente paciente, Turno turno, Urgencia urgencia) {
        this.tipo = tipo;
        this.paciente = paciente;
        this.turno = turno;
        this.urgencia = urgencia;
    }

    // Un "constructor con nombre" por cada tipo: no hace falta pasar null a mano
    public static Accion alta(Paciente p)    { 
        return new Accion(Tipo.ALTA_PACIENTE, p, null, null); 
    }
    public static Accion turno(Turno t)      { 
        return new Accion(Tipo.TURNO_REGULAR, t.getPaciente(), t, null); 
    }
    public static Accion urgencia(Urgencia u){ 
        return new Accion(Tipo.INGRESO_URGENCIA, u.getPaciente(), null, u); 
    }

    // Getters de los atributos
    public Tipo getTipo() { // Obtener el tipo de accion que fue desecha.
        return tipo; 
    }
    public Paciente getPaciente() { // Obtener el paciente de la accion que fue desecha
        return paciente; 
    }
    public Turno getTurno() { // Obtener el turno de la accion que fue desecha, si corresponde (cuando el tipo es TURNO_REGULAR)
        return turno; 
    }
    public Urgencia getUrgencia() { // Obtener la urgencia de la accion que fue desecha, si corresponde (cuando el tipo es INGRESO_URGENCIA)
        return urgencia; 
    }
}