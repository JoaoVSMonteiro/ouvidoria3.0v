package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Pessoa {
	private String nome;
	private String cpf;
	public Pessoa(String nome, String cpf) {
		super();
		this.nome = nome;
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", cpf=" + cpf + "]";
	}
	// inserir pessoa na tabela pessoa
	public void inserir(Connection con) {
		String sql = "insert into pessoa (nome,cpf) values ('"
	+ this.nome + "','" + this.cpf + "')" ;
		try {
			Statement s = con.createStatement();
			s.executeUpdate(sql);
			System.out.println("Dados salvos no banco");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// pegar id na tabela pessoa e jogar no inserirManifest
	public int getPessoaId(Connection con) {
		String sql = "select id from pessoa where CPF = (?)";
		try {
			PreparedStatement s = con.prepareStatement(sql);
			s.setString(1, this.cpf);
			ResultSet resultado = s.executeQuery();
			resultado.next();
			return resultado.getInt("id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	// listar pessoa da tabela pessoa
	public static void listar (Connection con) {
		String sql = "select * from pessoa";
		try {
			Statement s = con.createStatement();
			ResultSet resultado = s.executeQuery(sql);
			while (resultado.next()) {
				System.out.printf("ID: %d | Nome: %s | CPF: %s%n", resultado.getInt("id"), resultado.getString("nome"),
						resultado.getString("cpf"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	// apagar pessoa da tabela pessoa
	public static void apagarPessoa(Connection con,int id) {
		String sql;
		if (id ==0) {//apaga tudo
			 sql = "truncate pessoa";
		}else {//apaga por id
			 sql = "delete from pessoa where id = " +id;
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
