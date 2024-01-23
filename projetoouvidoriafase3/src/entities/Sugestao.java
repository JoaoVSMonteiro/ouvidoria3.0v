package entities;

public class Sugestao extends Manifest{

	public Sugestao(String texto, Pessoa pessoa) {
		super(texto, pessoa);
		this.setTipo("Sugestão");
	}

}
