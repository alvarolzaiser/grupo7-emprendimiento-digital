package clinica.estructuras;

import clinica.modelo.Turno;
import java.util.ArrayDeque;
import java.util.Queue;


/** Turnos regulares por orden de llegada(Queue,FIFO).*/

public class ColaTurnos {
	
	private final Queue<Turno>fila = new ArrayDeque<>();
	
	public void encolar(Turno t) {
		fila.offer(t);
		
	}
	
	public Turno atenderSiguiente() {
		return fila.poll();
	}
	public Turno verSiguiente() {
		return fila.peek();
	}
	public boolean estaVacia() {
		return fila.isEmpty(); 
	}
	public boolean quitarTurno(Turno t) {
		return fila.remove(t);
	}
	public int cantidad() {
		return fila.size();
	}
	

}
	




