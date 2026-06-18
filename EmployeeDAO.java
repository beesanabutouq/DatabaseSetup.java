package supermarket_db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

	public boolean addEmployee(Employee e) {
		try {

			// connect to database
			Connection conn = DBConnection.getConnection();

			String sql = "INSERT INTO Employees " + "(employee_name, position, salary, hire_date, manager_id) "
					+ "VALUES (?, ?, ?, ?, ?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, e.getName());
			ps.setString(2, e.getPosition());
			ps.setDouble(3, e.getSalary());
			ps.setString(4, e.getHireDate());
			ps.setInt(5, e.getManagerId());

			ps.executeUpdate();

			conn.close();

			return true;

		} catch (Exception ex) {

			ex.printStackTrace();
			return false;
		}

	}

	public List<Employee> getAllEmployees() {

		List<Employee> employees = new ArrayList<>();

		try {

			// connect to database
			Connection conn = DBConnection.getConnection();

			String sql = "SELECT * FROM Employees";

			ResultSet rs = conn.createStatement().executeQuery(sql);

			while (rs.next()) {

				employees.add(

						new Employee(

								rs.getInt("employee_id"), rs.getString("employee_name"), rs.getString("position"),
								rs.getDouble("salary"), rs.getString("hire_date"), rs.getInt("manager_id")));
			}

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return employees;
	}

	public boolean deleteEmployee(int id) {

		try {

			Connection conn = DBConnection.getConnection();

			String sql = "DELETE FROM Employees " + "WHERE employee_id=?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, id);

			int rows = ps.executeUpdate();

			conn.close();

			return rows > 0;

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	public List<Employee> searchEmployees(String keyword) {

		List<Employee> employees = new ArrayList<>();

		try {

			Connection conn = DBConnection.getConnection();

			String sql = "SELECT * FROM Employees " + "WHERE employee_name LIKE ?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, "%" + keyword + "%");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				employees.add(

						new Employee(

								rs.getInt("employee_id"), rs.getString("employee_name"), rs.getString("position"),
								rs.getDouble("salary"), rs.getString("hire_date"), rs.getInt("manager_id")));
			}

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return employees;
	}

	public void updateEmployee(Employee e) {

		try {

			Connection conn = DBConnection.getConnection();

			String sql = "UPDATE Employees " + "SET employee_name=?," + "position=?," + "salary=?," + "hire_date=?,"
					+ "manager_id=? " + "WHERE employee_id=?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, e.getName());
			ps.setString(2, e.getPosition());
			ps.setDouble(3, e.getSalary());
			ps.setString(4, e.getHireDate());
			ps.setInt(5, e.getManagerId());
			ps.setInt(6, e.getId());

			ps.executeUpdate();

			conn.close();

		} catch (Exception ex) {

			ex.printStackTrace();
		}
	}

}