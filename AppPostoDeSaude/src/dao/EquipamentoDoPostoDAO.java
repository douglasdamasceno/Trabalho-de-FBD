package dao;

import java.sql.*;
import java.util.ArrayList;

import jdbc.*;
import model.*;

public class EquipamentoDoPostoDAO {
	private Connection conexao;
	private int vetorIds[];

	public EquipamentoDoPostoDAO() {

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

	public boolean adicionarEquipamentoNoPosto(EquipamentoDoPosto equipDoPosto) {

		String comandoSQL = "insert into EquipamentoDoPosto (idPosto, idEquipamento, qtdEquipamento, dataDeEntrega) values (?, ?, ?, ?)";
		conecte();

		try {

			PreparedStatement preparedStatement = conexao.prepareStatement(comandoSQL);

			preparedStatement.setInt(1, equipDoPosto.getIdPosto());
			preparedStatement.setInt(2, equipDoPosto.getIdEquipamento());
			preparedStatement.setInt(3, equipDoPosto.getQtdEquipamento());
			preparedStatement.setString(4, equipDoPosto.getDataDeEntrega());

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

	public boolean removerEquipamentoDoPosto(int idEquiDoPosto) {

		String comandoSQL = "delete from EquipamentoDoPosto where idEquiDoPosto = ?";
		conecte();

		try {

			PreparedStatement preparedStatement = conexao.prepareStatement(comandoSQL);

			preparedStatement.setInt(1, idEquiDoPosto);

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

	public EquipamentoDoPosto getEquipamentoDoPostoById(int idPosto, int idEquipamento) {

		String comandoSQL = "select * from EquipamentoDoPosto where idEquipamento = ? and idPosto = ?";
		conecte();

		try {

			PreparedStatement preparedStatement = conexao.prepareStatement(comandoSQL);

			preparedStatement.setInt(1, idEquipamento);
			preparedStatement.setInt(2, idPosto);

			ResultSet rs = preparedStatement.executeQuery();
			rs.next();

			EquipamentoDoPosto equiDoPosto = new EquipamentoDoPosto(idPosto, idEquipamento, rs.getInt("qtdEquipamento"),
					rs.getString("dataDeEntrega"));

			preparedStatement.close();

			return equiDoPosto;
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

	public ArrayList<EquipamentoDoPosto> getListEquipamentosDoPosto(int idPosto) {
		String comandoSQL = "select * from EquipamentoDoPosto where idPosto = ?";
		ArrayList<EquipamentoDoPosto> listaDeEquipamentosDoPosto = new ArrayList<>();
		conecte();

		try {

			PreparedStatement preparedStatement = conexao.prepareStatement(comandoSQL);
			preparedStatement.setInt(1, idPosto);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				EquipamentoDoPosto equiDoPosto = new EquipamentoDoPosto(rs.getInt("idEquiDoPosto"),rs.getInt("idPosto"),
						rs.getInt("idEquipamento"), rs.getInt("qtdEquipamento"), rs.getString("dataDeEntrega"));
				listaDeEquipamentosDoPosto.add(equiDoPosto);
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.conexao.close();
			} catch (SQLException e2) {
				e2.printStackTrace();// TODO: handle exception
			}
		}

		return listaDeEquipamentosDoPosto;
	}

//	public ArrayList<EquipamentoDoPosto> getListEquipamentosDoPosto() {
//		String comandoSQL = "select * from EquipamentoDoPosto";
//		ArrayList<EquipamentoDoPosto> listaDeEquipamentosDoPosto = new ArrayList<>();
//		conecte();
//
//		try {
//
//			PreparedStatement preparedStatement = conexao.prepareStatement(comandoSQL);
//
//			ResultSet rs = preparedStatement.executeQuery();
//
//			while (rs.next()) {
//				EquipamentoDoPosto equiDoPosto = new EquipamentoDoPosto(rs.getInt("idPosto"),
//						rs.getInt("idEquipamento"), rs.getInt("qtdEquipamento"), rs.getString("dataDeEntrega"));
//				listaDeEquipamentosDoPosto.add(equiDoPosto);
//			}
//
//		} catch (SQLException e) {
//			System.err.println(e.getMessage());
//		} finally {
//			try {
//				this.conexao.close();
//			} catch (SQLException e2) {
//				e2.printStackTrace();// TODO: handle exception
//			}
//		}
//
//		return listaDeEquipamentosDoPosto;
//	}

	public boolean alterarEquipamentoDoPosto(EquipamentoDoPosto equiDoPosto) {
		String comandoSQL = "update EquipamentoDoPosto set qtdEquimapamento = ?, dataDeEntrega = ? where idEquipamento = ?  and idPosto = ?";
		conecte();

		try {

			PreparedStatement preparedStatement = conexao.prepareStatement(comandoSQL);

			preparedStatement.setInt(1, equiDoPosto.getQtdEquipamento());
			preparedStatement.setString(2, equiDoPosto.getDataDeEntrega());
			preparedStatement.setInt(3, equiDoPosto.getIdEquipamento());
			preparedStatement.setInt(4, equiDoPosto.getIdPosto());

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

	public int[] getIdByObjeto(EquipamentoDoPosto equiDoPosto) {
		vetorIds = null;
		String comandoSQL = "select idPosto, idEquipamento from EquipamentoDoPosto where qtdEquipamento = ?, dataDeEntrega = ?";
		conecte();

		try {

			PreparedStatement preparedStatement = conexao.prepareStatement(comandoSQL);

			preparedStatement.setInt(1, equiDoPosto.getQtdEquipamento());
			preparedStatement.setString(2, equiDoPosto.getDataDeEntrega());

			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			vetorIds[0] = rs.getInt("idPosto");
			vetorIds[1] = rs.getInt("idEquipamento");

			preparedStatement.close();

			return vetorIds;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.conexao.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return vetorIds;
	}

	public int getIdMax() {
		String comandoSQL = "select max(idEquiDoPosto) from EquipamentoDoPosto";
		conecte();

		try {
			PreparedStatement preparedStatement = conexao.prepareStatement(comandoSQL);

			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			int idMaximo = rs.getInt("max");

			preparedStatement.close();

			return idMaximo;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.conexao.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return -1;
	}

}
