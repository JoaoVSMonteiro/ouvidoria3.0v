package application;

import java.sql.Connection;
import java.util.Scanner;

import db.DB;
import entities.Elogio;
import entities.Manifest;
import entities.Pessoa;
import entities.Reclamacao;
import entities.Sugestao;

public class Program {
	public static void main(String[] args) {

		Connection con = DB.getConnection();
		Scanner sc = new Scanner(System.in);
		int opcao = 0;

		while (opcao != 4) {
			System.out.println("------------------------------------ \n" + "          OUVIDORIA NIGGAZ \n"
					+ "------------------------------------ \n" + "1) Inserir manifestações \n"
					+ "2) Listar manifestações  \n" + "3) Apagar manifestação por ID \n" + "4) Sair \n"
					+ "------------------------------------ \n" + "Selecione uma opção: ");

			opcao = sc.nextInt();
			sc.nextLine();
			switch (opcao) {
			case 1:
				System.out.println("Digite seu nome: ");
				String nome = sc.nextLine();
				System.out.println("Digite seu CPF: ");
				String cpf = sc.nextLine();
				Pessoa p = new Pessoa(nome, cpf);
				p.inserir(con);
				Pessoa.listar(con);
				System.out.println("Digite qual o tipo: \n"
						+ "1 - Elogio | 2 - Reclamação | 3 - Sugestão");
				int tipo = sc.nextInt();
				sc.nextLine();
				System.out.println("Digite o texto a ser inserido: ");
				String texto = sc.nextLine();
				if (tipo == 1) {
					Manifest e = new Elogio(texto,p);
					e.inserirManifest(con);
				}
				else if (tipo == 2) {
					Manifest r = new Reclamacao(texto, p);
					r.inserirManifest(con);
				}
				else if (tipo == 3) {
					Manifest s = new Sugestao(texto,p);
					s.inserirManifest(con);
				}

				break;

			case 2:
				System.out.println("        LISTA DE MANIFESTAÇÃO");
				Manifest.listarManifest(con);
				break;

			case 3:
				System.out
						.println("Digite o ID da reclamação a ser excluída: \n" + " *0 apaga todas as manifestações* ");
				Manifest.listarManifest(con);
				int id = sc.nextInt();
				sc.nextLine();
				Manifest.apagarManifest(con, id);
				break;

			case 4:
				System.out.println();
				System.out.println("------------------------------------ \n" + "OBRIGADO POR USAR A OUVIDORIA NIGGAZ \n"
						+ "------------------------------------ ");
				break;
			default:
				System.out.println();
				System.out.println("      *Digite uma opção válida*");
				System.out.println();
				break;
			}

		}
		sc.close();
	}
}
