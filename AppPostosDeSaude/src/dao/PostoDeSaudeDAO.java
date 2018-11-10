package dao;

import java.sql.*;
import java.util.ArrayList;

import jdbc.*;
import model.Endereco;
import model.PostoDeSaude;

public class PostoDeSaudeDAO {
	private Connection conexao;

	public PostoDeSaudeDAO() {

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

	public boolean adicionarPostoDeSaude(PostoDeSaude posto) {
		String comandoSQL = "insert into PostoDeSaude (pNome, idEndereco) values (?, ?)";
		conecte();

		try {

			PreparedStatement preparedStatement = conexao.prepareStatement(comandoSQL);
			
			preparedStatement.setString(1, posto.getpNome());
			preparedStatement.setInt(2, posto.getIdEndereco());
			

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

	public boolean removerPostoDeSaude(int idPosto) {

		String comadoSQL = "delete from PostoDeSaude where idPosto = ?";
		conecte();

		try {

			PreparedStatement preparedStatement = conexao.prepareStatement(comadoSQL);

			preparedStatement.setInt(1, idPosto);

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

	public PostoDeSaude getPostoById(int idPosto) {

		String comandoSQL = "select * from PostoDeSaude where idPosto = ?";
		conecte();

		try {

			PreparedStatement preparedStatement = conexao.prepareStatement(comandoSQL);

			preparedStatement.setInt(1, idPosto);

			ResultSet rs = preparedStatement.executeQuery();
			rs.next();

			PostoDeSaude posto = new PostoDeSaude(idPosto, rs.getString("pNome"), rs.getInt("idEndereco"));
			
			preparedStatement.close();

			return posto;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.conexao.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return null;
	}

	public ArrayList<PostoDeSaude> getListPostoDeSaude() {
		String comandoSQL = "select * from PostoDeSaude order by idPosto";
		ArrayList<PostoDeSaude> listaDePostosDeSaude = new ArrayList<>();
		conecte();

		try {

			PreparedStatement preparedStatement = conexao.prepareStatement(comandoSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				PostoDeSaude posto = new PostoDeSaude(rs.getInt("idPosto"),
						rs.getString("pNome"), rs.getInt("idEndereco"));
				listaDePostosDeSaude.add(posto);
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
		return listaDePostosDeSaude;
	}

	public boolean alterarPostoDeSaude(PostoDeSaude posto, Endereco end) {

		EnderecoDAO endereco = new EnderecoDAO();

		String comandoSQL = "update PostoDeSaude set idEndereco = ?, pNome = ? where idPosto = ?";
		conecte();

		try {

			PreparedStatement preparedStatement = conexao.prepareStatement(comandoSQL);

			preparedStatement.setInt(1, posto.getIdEndereco());
			preparedStatement.setString(2, posto.getpNome());
			preparedStatement.setInt(3, posto.getIdPosto());

			int qtdRowsAffected = preparedStatement.executeUpdate();
			preparedStatement.close();

			if (qtdRowsAffected > 0) {
				if (endereco.alterarEndereco(end)) {
					return true;
				}
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

	public int getIdByObjeto(PostoDeSaude posto) {
		String comandoSQL = "select idPosto from PostoDeSaude where idEndereco = ?, pNome = ?";
		conecte();

		try {

			PreparedStatement preparedStatement = conexao.prepareStatement(comandoSQL);

			preparedStatement.setInt(1, posto.getIdEndereco());
			preparedStatement.setString(2, posto.getpNome());

			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			preparedStatement.close();

			return rs.getInt("idPosto");

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.conexao.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}

		}

		return -1;
	}

}
