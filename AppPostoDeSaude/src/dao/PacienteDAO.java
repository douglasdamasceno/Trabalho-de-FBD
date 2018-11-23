package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.Conexao;
import model.Endereco;
import model.Paciente;

public class PacienteDAO {
	private Connection connection;

	public PacienteDAO() {

	}

	public void conecte() {
		this.connection = new Conexao().getConnection();
	}

	public boolean addPaciente(Paciente paciente) {
		String comandoSQL = "INSERT INTO paciente(pacNome,idPosto ,idendereco) VALUES (?, ?, ?);";
		this.connection = new Conexao().getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);

			preparedStatement.setString(1, paciente.getPacNome());
			preparedStatement.setInt(2, paciente.getIdPosto());
			preparedStatement.setInt(3, paciente.getIdEndereco());

			int qtdRowsAffected = preparedStatement.executeUpdate();
			preparedStatement.close();
			if (qtdRowsAffected > 0)
				return true;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public ArrayList<Paciente> getListPaciente() {
		String comandoSQL = "SELECT * FROM Paciente ORDER BY idPaciente;";
		ArrayList<Paciente> listaPacientes = new ArrayList<Paciente>();
		this.connection = new Conexao().getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("idPaciente");
				String nome = rs.getString("pacNome");
				int idPosto = rs.getInt("idPosto");
				int idEndereco = rs.getInt("idEndereco");

				Paciente user = new Paciente(id, nome, idPosto, idEndereco);

				listaPacientes.add(user);
			}
			preparedStatement.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaPacientes;
	}

	public boolean deletePaciente(int idPaciente) {
		String comandoSQL = "DELETE FROM paciente WHERE idPaciente = ?;";

		this.connection = new Conexao().getConnection();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);
			preparedStatement.setInt(1, idPaciente);

			int qtdRowsAffected = preparedStatement.executeUpdate();
			preparedStatement.close();
			if (qtdRowsAffected > 0)
				return true;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public Paciente getPacienteById(int idPaciente) {
		String comandoSQL = "SELECT * FROM paciente WHERE idPaciente = ?;";

		this.connection = new Conexao().getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);

			preparedStatement.setInt(1, idPaciente);

			ResultSet rs = preparedStatement.executeQuery();
			rs.next();

			Paciente paciente = new Paciente(idPaciente, rs.getString("pacNome"), rs.getInt("idPosto"),
					rs.getInt("idEndereco"));

			preparedStatement.close();
			return paciente;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;

	}

	public boolean alterarPaciente(Paciente paciente, Endereco endereco) {
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		String comandoSQL = "UPDATE paciente set pacNome= ?,idPosto=? ,idEndereco=? where idPaciente=?";
		this.connection = new Conexao().getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);
			preparedStatement.setString(1, paciente.getPacNome());
			preparedStatement.setInt(2, paciente.getIdPosto());
			preparedStatement.setInt(3, endereco.getIdEndereco());
			preparedStatement.setInt(4, paciente.getIdPaciente());
			int qtdRowAffected = preparedStatement.executeUpdate();
			preparedStatement.close();

			if (qtdRowAffected > 0) {
				if (enderecoDAO.alterarEndereco(endereco))
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}
	
	public boolean alterarPacNome(int idPaciente,String pacNome) {
		String comandoSQL = "UPDATE paciente SET pacNome= ? WHERE idPaciente = ?";
		this.connection = new Conexao().getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);
			preparedStatement.setString(1, pacNome);
			preparedStatement.setInt(2, idPaciente);
			int qtdRowAffected = preparedStatement.executeUpdate();
			preparedStatement.close();
			if(qtdRowAffected>0) 
				return true;
	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public boolean alterarIdPosto(int idPaciente,int idPosto) {
		String comandoSQL = "UPDATE paciente SET idPosto= ? WHERE idPaciente = ?";
		this.connection = new Conexao().getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);
			preparedStatement.setInt(1, idPosto);
			preparedStatement.setInt(2, idPaciente);
			int qtdRowAffected = preparedStatement.executeUpdate();
			preparedStatement.close();
			if(qtdRowAffected>0) 
				return true;
	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public boolean alterarEndereco(int idPaciente,Endereco endereco) {
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		String comandoSQL = "update Endereco set rua = ?, numero = ?, bairro = ?, cep = ?, estado = ? where idEndereco = ?";		
		this.connection = new Conexao().getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);
			preparedStatement.setString(1, endereco.getRua());
			preparedStatement.setInt(2, endereco.getNumero());
			preparedStatement.setString(3, endereco.getBairro());
			preparedStatement.setString(4, endereco.getCep());
			preparedStatement.setString(5, endereco.getEstado());
			preparedStatement.setInt(6, endereco.getIdEndereco());

			int qtdRowsAffected = preparedStatement.executeUpdate();
			preparedStatement.close();

			if (qtdRowsAffected > 0) {
				if (enderecoDAO.alterarEndereco(endereco)) {
					return true;
				}
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();;
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

	
	
	public int getIdMax() {
		String comandoSQL = "select max(idPaciente) from Paciente";
		conecte();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			int idMaximo = rs.getInt("max");
			preparedStatement.close();
			return idMaximo;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return -1;
	}

//	public boolean verificIfIdExist(int idPaciente) {
//		String comandoSQL = "select * from Paciente where idPaciente = ?";
//		conecte();
//		
//		try {
//			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);
//			
//			preparedStatement.setInt(1, idPaciente);
//			
//			int qtdRowsAffected = preparedStatement.executeUpdate();
//			preparedStatement.close();
//			if(qtdRowsAffected > 0) {
//				return true;
//			}
//			
//		} catch (SQLException e) {
//			System.err.println(e.getMessage());
//		} finally {
//			try {
//				this.connection.close();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//		
//		return false;
//	}
}
