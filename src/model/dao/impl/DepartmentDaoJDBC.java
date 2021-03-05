package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {
	private Connection conn;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public DepartmentDaoJDBC() {
	}
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		List<Department> deps = findAll();
		
		try {
			st = conn.prepareStatement("INSERT INTO department\r\n" + 
					"(Name)\r\n" + 
					"VALUES\r\n" + 
					"(?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getName());
			
			int rowsAffected = 0;
			
			boolean insert = false;
			for(Department dps : deps) {
				if(!(obj.getName().contentEquals(dps.getName()))) {
					insert = true;
				}else {
					insert = false;
				}
				if(insert == false) {
					break;
				}
			}
			
			if(insert == true) {
				rowsAffected = st.executeUpdate();
				if(rowsAffected > 0) {
					rs = st.getGeneratedKeys();
					while (rs.next()) {
						int id = rs.getInt(1);
						System.out.println("Done! ID = " + id);
					}
				}else {
					System.out.println("No rows affected.");
				}	
			}else {
				System.out.println("Departament already exists.");
			}
		}		
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public void update(Department obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Department findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Department> deps = new ArrayList<>();
		try {
			st = conn.prepareStatement("SELECT department.* FROM department");

			rs = st.executeQuery();
			
			while (rs.next()) {
				Department dep = instanteateDepartment(rs);
				deps.add(dep);
			}
			return deps;			
		}		
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	private Department instanteateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		
		return dep;
	}

}
