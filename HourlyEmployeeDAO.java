package supermarket_db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HourlyEmployeeDAO {

	public boolean addHourlyEmployee(HourlyEmployee h) {

		try {

			// connect to database
			Connection conn = DBConnection.getConnection();

			String sql = "INSERT INTO Hourly_Employees " + "(employee_id, hourly_wages, hours_worked) "
					+ "VALUES (?, ?, ?)";

			
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, h.getEmployeeId());
			ps.setDouble(2, h.getHourlyWage());
			ps.setInt(3, h.getHoursWorked());

			// execute sql statement
			ps.executeUpdate();

			conn.close();

			return true;

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	public List<HourlyEmployee> getAllHourlyEmployees() {

		List<HourlyEmployee> list = new ArrayList<>();

		try {

			//connect to database
			Connection conn = DBConnection.getConnection();

			String sql = "SELECT * FROM Hourly_Employees";

			ResultSet rs = conn.createStatement().executeQuery(sql);

			while (rs.next()) { // move row by row

				list.add(new HourlyEmployee(rs.getInt("employee_id"), rs.getDouble("hourly_wages"),
						rs.getInt("hours_worked")));
			}

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public boolean deleteHourlyEmployee(int employeeId) {

		try {

			Connection conn = DBConnection.getConnection();

			String sql = "DELETE FROM Hourly_Employees " + "WHERE employee_id=?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, employeeId);

			int rows = ps.executeUpdate();

			conn.close();

			return rows > 0;

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	public void updateHourlyEmployee(HourlyEmployee h) {

		try {

			Connection conn = DBConnection.getConnection();

			String sql = "UPDATE Hourly_Employees " + "SET hourly_wages=?, " + "hours_worked=? "
					+ "WHERE employee_id=?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setDouble(1, h.getHourlyWage());

			ps.setInt(2, h.getHoursWorked());

			ps.setInt(3, h.getEmployeeId());

			ps.executeUpdate();

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	public List<HourlyEmployee> searchHourlyEmployees(String keyword) {

	    List<HourlyEmployee> list = new ArrayList<>();

	    try {

	        Connection conn = DBConnection.getConnection();

	        String sql =
	            "SELECT h.* " +
	            "FROM Hourly_Employees h " +
	            "JOIN Employees e " +
	            "ON h.employee_id = e.employee_id " +
	            "WHERE e.employee_name LIKE ?";

	        PreparedStatement ps =
	                conn.prepareStatement(sql);

	        ps.setString(1, "%" + keyword + "%");

	        ResultSet rs = ps.executeQuery();

	        while(rs.next()) {

	            list.add(
	                new HourlyEmployee(
	                    rs.getInt("employee_id"),
	                    rs.getDouble("hourly_wages"),
	                    rs.getInt("hours_worked")
	                )
	            );
	        }

	        conn.close();

	    } catch(Exception e) {

	        e.printStackTrace();
	    }

	    return list;
	}
}