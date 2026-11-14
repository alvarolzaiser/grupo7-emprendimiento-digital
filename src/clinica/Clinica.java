package clinica;

import clinica.estructuras.*;
import clinica.modelo.*;

/**
 * Fachada: coordina las cuatro estructuras. No imprime nada;
 * devuelve mensajes y Main decide cómo mostrarlos.
 */
public class Clinica {
    // Instancias de las estructuras de datos
    private final RegistroPacientes registro = new RegistroPacientes();
    private final ColaTurnos turnos = new ColaTurnos();
    private final Urgencias urgencias = new Urgencias();
    private final Historial historial = new Historial();
    // Contador de turnos: se incrementa cada vez que se asigna un turno regular.
    private int proximoNumeroTurno = 1;

    // Metodos que son llamados por Main para realizar las operaciones de la clínica. Devuelven mensajes de éxito o error.
    // La logica dura vive en las estructuras de datos, no en Clinica.

    public String altaPaciente(String dni, String nombre, String apellido) {
        Paciente p;
        try {
            p = new Paciente(dni, nombre, apellido);
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
        if (!registro.registrar(p)) {
            return "Error: ya existe un paciente con DNI " + p.getDni();
        }
        historial.apilar(Accion.alta(p));
        return "Paciente registrado: " + p;
    }

    public String buscarPaciente(String dni) {
        Paciente p = registro.buscar(dni);
        return p == null ? "No hay ningún paciente con DNI " + dni : p.toString();
    }

    public String darTurnoRegular(String dni) {
        Paciente p = registro.buscar(dni);
        if (p == null) return "Error: primero hay que registrar al paciente";
        Turno t = new Turno(proximoNumeroTurno++, p);
        turnos.encolar(t);
        historial.apilar(Accion.turno(t));
        return "Asignado: " + t;
    }

    public String verProximoTurno() {
        Turno t = turnos.verSiguiente();
        return t == null ? "No hay turnos en espera" : "Sigue: " + t;
    }

    public String atenderTurno() {
        Turno t = turnos.atenderSiguiente();
        return t == null ? "No hay turnos en espera" : "Atendiendo: " + t;
    }

    public String ingresarUrgencia(String dni, int gravedad) {
        Paciente p = registro.buscar(dni);
        if (p == null) return "Error: primero hay que registrar al paciente";
        if (urgencias.estaEnGuardia(p)) return "Error: el paciente ya está en guardia";
        try {
            Urgencia u = urgencias.ingresar(p, gravedad);
            historial.apilar(Accion.urgencia(u));
            return "Ingresó a guardia: " + u;
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String atenderUrgencia() {
        Urgencia u = urgencias.atenderUrgencia();
        return u == null ? "No hay pacientes en guardia" : "Atendiendo urgencia: " + u;
    }

    /** Revierte la última acción. Atender NO se registra: un paciente atendido no se "des-atiende". */
    public String deshacerUltimaAccion() {
        Accion a = historial.desapilar();
        if (a == null) return "No hay acciones para deshacer";

        switch (a.getTipo()) {
            case ALTA_PACIENTE:
                registro.eliminar(a.getPaciente());
                return "Se deshizo el alta de " + a.getPaciente();
            case TURNO_REGULAR:
                if (turnos.quitarTurno(a.getTurno())) {
                    return "Se canceló el " + a.getTurno();
                }
                return "El " + a.getTurno() + " ya fue atendido; no se puede cancelar";
            case INGRESO_URGENCIA:
                if (urgencias.quitar(a.getUrgencia())) {
                    return "Se quitó de guardia a " + a.getPaciente();
                }
                return a.getPaciente() + " ya fue atendido en guardia; no se puede quitar";
            default:
                return "Acción desconocida";
        }
    }
}