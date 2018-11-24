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

	public boolean removerEquipamentoDoPosto(int idPosto,int idEquipamento) {
		String comandoSQL = "delete from EquipamentoDoPosto where idPosto=? and idEquipamento = ? ";
		conecte();
		try {

			PreparedStatement preparedStatement = conexao.prepareStatement(comandoSQL);
			preparedStatement.setInt(1, idPosto);
			preparedStatement.setInt(2, idEquipamento);
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
		String comandoSQL = "select * from EquipamentoDoPosto where idPosto = ? and idEquipamento = ?";
		conecte();

		try {

			PreparedStatement preparedStatement = conexao.prepareStatement(comandoSQL);
			preparedStatement.setInt(1, idPosto);
			preparedStatement.setInt(2, idEquipamento);
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
		String comandoSQL = "select * from EquipamentoDoPosto where idPosto = ? ";
		ArrayList<EquipamentoDoPosto> listaDeEquipamentosDoPosto = new ArrayList<>();
		conecte();
		try {
			PreparedStatement preparedStatement = conexao.prepareStatement(comandoSQL);
			preparedStatement.setInt(1, idPosto);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				if(rs.getInt("idPosto")==idPosto) {
					EquipamentoDoPosto equiDoPosto = new EquipamentoDoPosto(
						rs.getInt("idPosto"),rs.getInt("idEquipamento"), rs.getInt("qtdEquipamento"), rs.getString("dataDeEntrega"));
					listaDeEquipamentosDoPosto.add(equiDoPosto);
				}
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
	
	public boolean alterarQtdEquipamentoDoPosto(int idPosto,int idEquipamento,int qtd) {
		String comandoSQL = "update EquipamentoDoPosto set qtdEquipamento = ? where idEquipamento = ?  and idPosto = ?";
		conecte();

		try {

			PreparedStatement preparedStatement = conexao.prepareStatement(comandoSQL);

			preparedStatement.setInt(1, qtd);
			preparedStatement.setInt(2, idEquipamento);
			preparedStatement.setInt(3, idPosto);

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

	
	
//	public int getIdMax() {
//		String comandoSQL = "select max(idEquipamento) from EquipamentoDoPosto";
//		conecte();
//
//		try {
//			PreparedStatement preparedStatement = conexao.prepareStatement(comandoSQL);
//
//			ResultSet rs = preparedStatement.executeQuery();
//			rs.next();
//			int idMaximo = rs.getInt("max");
//
//			preparedStatement.close();
//
//			return idMaximo;
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
//		return -1;
//	}
//
}
