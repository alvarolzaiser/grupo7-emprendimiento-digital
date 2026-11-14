package clinica.modelo;

public class Turno {
	private final int numero;
	private final Paciente paciente;
	
	public Turno(int numero,Paciente paciente){
		this.numero=numero;
		this.paciente=paciente;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public Paciente getpaciente() {
		return paciente;
	}
	
	@Override
	public String toString() {
		return "Turno N° " + numero + "-" + paciente;
	}

}
