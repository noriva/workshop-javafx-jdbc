package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartamentoDao;
import model.entities.Departamento;

public class DepartamentoDaoJDBC implements DepartamentoDao {

	private Connection conn;

	public DepartamentoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Departamento obj) {
		// TODO Auto-generated method stub

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("INSERT INTO departamento " + "(nomeDepartamento) " + "VALUES " + "(?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNomeDepartamento());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setIdDepartamento(id);
				}
			} else {

				throw new DbException("error ");

			}

		} catch (SQLException e) {
			// TODO: handle exception
			throw new DbException(e.getMessage());
		} finally {

			DB.closeStatement(st);

		}

	}

	@Override
	public void update(Departamento obj) {
		// TODO Auto-generated method stub

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("UPDATE departamento " + "SET nomeDepartamento = ? " + "WHERE = ?");

			st.setString(1, obj.getNomeDepartamento());
			st.setInt(2, obj.getIdDepartamento());

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

			st = conn.prepareStatement("DELETE FROM departamento WHERE idDepartamento = ?");

			st.setInt(1, id);

			st.executeUpdate();

		} catch (SQLException e) {
			// TODO: handle exception

			throw new DbException(e.getMessage());

		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public Departamento findById(Integer id) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("SELECT * FROM departamento WHERE idDepartamento = ?");
			st.setInt(1, id);

			rs = st.executeQuery();

			if (rs.next()) {
				Departamento obj = new Departamento();
				obj.setIdDepartamento(rs.getInt("idDepartamento"));
				obj.setNomeDepartamento(rs.getString("nomeDepartamento"));
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

	@Override
	public List<Departamento> findAll() {
		// TODO Auto-generated method stub

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("SELECT * FROM departamento ORDER BY nomeDepartamento");
			rs = st.executeQuery();

			List<Departamento> lista = new ArrayList<>();

			while (rs.next()) {

				Departamento obj = new Departamento();
				obj.setIdDepartamento(rs.getInt("idDepartamento"));
				obj.setNomeDepartamento(rs.getString("nomeDepartamento"));
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
