package dao;

import jdbc.*;
import model.*;

import java.sql.*;
import java.util.ArrayList;

public class EnderecoDAO {
	private Connection conexao;

	public EnderecoDAO() {

	}

	public Connection getConexao() {
		return conexao;
	}

	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}

	public void conecte() {
		this.conexao = new Conexao().getConnection();
	}

	public boolean adicionarEndereco(Endereco endereco) {
		String comandoSQL = "insert into Endereco (rua, numero, bairro, cep, estado) values (?, ?, ?, ?, ?)";
		conecte();

		try {
			PreparedStatement preparedStatement = conexao.prepareStatement(comandoSQL);

			preparedStatement.setString(1, endereco.getRua());
			preparedStatement.setInt(2, endereco.getNumero());
			preparedStatement.setString(3, endereco.getBairro());
			preparedStatement.setString(4, endereco.getCep());
			preparedStatement.setString(5, endereco.getEstado());

			int qtdRowsAffected = preparedStatement.executeUpdate();
			preparedStatement.close();

			if (qtdRowsAffected > 0) {
				return true;
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.conexao.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

		return false;
	}

	public boolean removerEndereco(int idEndereco) {

		String comandoSQL = "delete from Endereco where idEndereco = ?";
		conecte();

		try {

			PreparedStatement preparedStatement = conexao.prepareStatement(comandoSQL);

			preparedStatement.setInt(1, idEndereco);

			int qtdRowsAffected = preparedStatement.executeUpdate();
			preparedStatement.close();

			if (qtdRowsAffected > 0) {
				return true;
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.conexao.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return false;
	}

	public Endereco getEnderecoById(int idEndereco) {

		String comandoSQL = "select * from Endereco where idEndereco = ?";
		conecte();

		try {

			PreparedStatement preparedStatement = conexao.prepareStatement(comandoSQL);

			preparedStatement.setInt(1, idEndereco);

			ResultSet rs = preparedStatement.executeQuery();
			rs.next();

			Endereco endereco = new Endereco(rs.getInt("idEndereco"), rs.getString("rua"), rs.getInt("numero"),
					rs.getString("bairro"), rs.getString("cep"), rs.getString("estado"));

			preparedStatement.close();
			return endereco;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.conexao.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

		return null;
	}

	public ArrayList<Endereco> getListEndereco() {
		String comandoSQL = "select * from Endereco";
		ArrayList<Endereco> listaDeEnderecos = new ArrayList<>();
		conecte();

		try {
			PreparedStatement preparedStatement = conexao.prepareStatement(comandoSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Endereco endereco = new Endereco(rs.getInt("idEndereco"), rs.getString("rua"), rs.getInt("numero"),
						rs.getString("bairro"), rs.getString("cep"), rs.getString("estado"));
				listaDeEnderecos.add(endereco);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.conexao.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return listaDeEnderecos;
	}

	public boolean alterarEndereco(Endereco end) {
		String comandoSQL = "update Endereco set rua = ?, numero = ?, bairro = ?, cep = ?, estado = ? where idEndereco = ?";
		conecte();
		try {
			PreparedStatement preparedStatement = conexao.prepareStatement(comandoSQL);
			preparedStatement.setString(1, end.getRua());
			preparedStatement.setInt(2, end.getNumero());
			preparedStatement.setString(3, end.getBairro());
			preparedStatement.setString(4, end.getCep());
			preparedStatement.setString(5, end.getEstado());
			preparedStatement.setInt(6, end.getIdEndereco());

			int qtdRowsAffected = preparedStatement.executeUpdate();
			preparedStatement.close();

			if (qtdRowsAffected > 0) {
				return true;
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.conexao.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return false;
	}

	public int getIdByObjeto(Endereco end) {
		String comandoSQL = "select idEndereco from Endereco where rua = ? and numero = ? and bairro = ? and cep = ? and estado = ?";
		int idEndereco = 0;
		conecte();

		try {

			PreparedStatement preparedStatement = conexao.prepareStatement(comandoSQL);

			preparedStatement.setString(1, end.getRua());
			preparedStatement.setInt(2, end.getNumero());
			preparedStatement.setString(3, end.getBairro());
			preparedStatement.setString(4, end.getCep());
			preparedStatement.setString(5, end.getEstado());

			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			
			idEndereco = rs.getInt("idEndereco");
			preparedStatement.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.conexao.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return idEndereco;

	}
	
//	public boolean verificIfIdExist(int idEnd) {
//		String comandoSQL = "select * from Endereco where idEndereco = ?";
//		conecte();
//		
//		try {
//			PreparedStatement preparedStatement = conexao.prepareStatement(comandoSQL);
//			
//			preparedStatement.setInt(1, idEnd);
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
//				this.conexao.close();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//		
//		return false;
//	}

}
