package entities;

public class Reclamacao extends Manifest {

	public Reclamacao(String texto, Pessoa pessoa) {
		super(texto, pessoa);
		this.setTipo("Reclamação");
	}

}
