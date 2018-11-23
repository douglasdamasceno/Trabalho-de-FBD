package view;

import dao.EnderecoDAO;
import dao.PacienteDAO;
import model.Endereco;
import model.Paciente;

public class teste {
	
	public static void main(String[] args) {
		PacienteDAO pacienteDAO = new PacienteDAO();

		EnderecoDAO eDAO = new EnderecoDAO();
		
		Endereco e = new Endereco(17,"rua", 12, "bairro", "cep", "estado");
		
		if(eDAO.alterarEndereco(e)) {
			System.out.println("ok");
		}else {
			System.out.println("falha");
		}
		
		if(pacienteDAO.alterarPacNome(10, "pacNome")){
			System.out.println("OK");
		}else {
			System.out.println("false");
		}
		
	}	

}
