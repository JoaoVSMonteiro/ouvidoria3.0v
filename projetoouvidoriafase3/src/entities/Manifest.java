package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Manifest {
	private String tipo;
	private String texto;
	Pessoa pessoa;

	public Manifest(String texto, Pessoa pessoa) {
		this.texto = texto;
		this.pessoa = pessoa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	// inserir manifestacao na tabela manifestacao
	public void inserirManifest(Connection con) {
		String sql = "insert into manifestacao (tipo, texto, pessoa_id) values(?,?,?) ";
		try {
			PreparedStatement s = con.prepareStatement(sql);
			s.setString(1, this.tipo);
			s.setString(2, this.texto);
			s.setInt(3, this.pessoa.getPessoaId(con));
			s.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	



	
	// listar manifestacao na tabela manifestacao
	public static void listarManifest (Connection con) {
		String sql = "select * from manifestacao";
		try {
			Statement s = con.createStatement();
			ResultSet resultado = s.executeQuery(sql);
			while (resultado.next()) {
				System.out.printf("ID: %d | Tipo: %s | Texto: %s%n", resultado.getInt("id"), resultado.getString("tipo"),
						resultado.getString("texto"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	// apagar manifestacao na tabela manifestacao
	public static void apagarManifest(Connection con,int id) {
		String sql;
		if (id ==0) {//apaga tudo
			 sql = "truncate manifestacao";
		}else {//apaga por id
			 sql = "delete from manifestacao where id = " +id;
		}
		try {
			Statement s = con.createStatement();
			s.executeUpdate(sql);
			System.out.println("Dado deletado com sucesso");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

			
	
}

