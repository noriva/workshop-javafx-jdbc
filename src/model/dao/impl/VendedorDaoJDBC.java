package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class VendedorDaoJDBC implements VendedorDao {

	private Connection conn;

	public VendedorDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Vendedor obj) {
		// TODO Auto-generated method stub

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement(

					"INSERT INTO vendedor "
							+ "(nomeVendedor, emailVendedor, dataDeNascimento, salarioBase, id_Departamento) "
							+ "VALUES " + "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNomeVendedor());
			st.setString(2, obj.getEmailVendedor());
			st.setDate(3, new java.sql.Date(obj.getDataDeNascimento().getTime()));
			st.setDouble(4, obj.getSalarioBase());
			st.setInt(5, obj.getDepartamento().getIdDepartamento());

			int rowsAfeccted = st.executeUpdate();

			if (rowsAfeccted > 0) {

				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setIdVendedor(id);
				}

				DB.closeResultSet(rs);

			} else {
				throw new DbException("Erro inesperado!!!!");
			}

		} catch (SQLException e) {
			// TODO: handle exception
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void update(Vendedor obj) {
		// TODO Auto-generated method stub
		PreparedStatement st = null;

		try {

			st = conn.prepareStatement(

					"UPDATE vendedor "
							+ "SET nomeVendedor = ?, emailVendedor = ?, dataDeNascimento = ?, salarioBase = ?, id_Departamento = ? "
							+ "WHERE idVendedor = ?");

			st.setString(1, obj.getNomeVendedor());
			st.setString(2, obj.getEmailVendedor());
			st.setDate(3, new java.sql.Date(obj.getDataDeNascimento().getTime()));
			st.setDouble(4, obj.getSalarioBase());
			st.setInt(5, obj.getDepartamento().getIdDepartamento());
			st.setInt(6, obj.getIdVendedor());

			st.executeUpdate();

		} catch (SQLException e) {
			// TODO: handle exception
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
		PreparedStatement st = null;
		try {
			
			st = conn.prepareStatement("DELETE FROM vendedor WHERE idVendedor = ? ");
			
			st.setInt(1, id);
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			
			// TODO: handle exception
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		

	}

	@Override
	public Vendedor findById(Integer id) {
		// TODO Auto-generated method stub
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("SELECT vendedor.*,departamento.nomeDepartamento as DepName "
					+ "FROM vendedor INNER JOIN departamento "
					+ "ON vendedor.id_Departamento = departamento.idDepartamento " + "WHERE vendedor.idVendedor = ?");

			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {

				Departamento dep = instanciarDepartamento(rs);

				Vendedor obj = instanciarVendedor(rs, dep);

				return obj;

			}

			return null;

		} catch (SQLException e) {
			// TODO: handle exception

			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	private Vendedor instanciarVendedor(ResultSet rs, Departamento dep) throws SQLException {
		// TODO Auto-generated method stub

		Vendedor obj = new Vendedor();

		obj.setIdVendedor(rs.getInt("idVendedor"));
		obj.setNomeVendedor(rs.getString("nomeVendedor"));
		obj.setEmailVendedor(rs.getString("emailVendedor"));
		obj.setDataDeNascimento(rs.getDate("dataDeNascimento"));
		obj.setSalarioBase(rs.getDouble("salarioBase"));
		obj.setDepartamento(dep);

		return obj;
	}

	private Departamento instanciarDepartamento(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub

		Departamento dep = new Departamento();
		dep.setIdDepartamento(rs.getInt("id_departamento"));
		dep.setNomeDepartamento(rs.getString("DepName"));

		return dep;
	}

	@Override
	public List<Vendedor> findAll() {
		// TODO Auto-generated method stub
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("SELECT vendedor.*,departamento.nomeDepartamento as DepName "
					+ "FROM vendedor INNER JOIN departamento "
					+ "ON vendedor.id_Departamento = departamento.idDepartamento "

					+ "ORDER BY nomeVendedor");

			rs = st.executeQuery();

			List<Vendedor> lista = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();

			while (rs.next()) {

				Departamento dep = map.get(rs.getInt("id_Departamento"));
				if (dep == null) {
					dep = instanciarDepartamento(rs);
					map.put(rs.getInt("id_Departamento"), dep);
				}

				Vendedor obj = instanciarVendedor(rs, dep);

				lista.add(obj);

			}

			return lista;

		} catch (SQLException e) {
			// TODO: handle exception

			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	@Override
	public List<Vendedor> findByDepartamento(Departamento departamento) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("SELECT vendedor.*,departamento.nomeDepartamento as DepName "
					+ "FROM vendedor INNER JOIN departamento "
					+ "ON vendedor.id_Departamento = departamento.idDepartamento " + "WHERE id_Departamento = ? "
					+ "ORDER BY nomeVendedor");

			st.setInt(1, departamento.getIdDepartamento());
			rs = st.executeQuery();

			List<Vendedor> lista = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();

			while (rs.next()) {

				Departamento dep = map.get(rs.getInt("id_Departamento"));
				if (dep == null) {
					dep = instanciarDepartamento(rs);
					map.put(rs.getInt("id_Departamento"), dep);
				}

				Vendedor obj = instanciarVendedor(rs, dep);

				lista.add(obj);

			}

			return lista;

		} catch (SQLException e) {
			// TODO: handle exception

			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

}
