/*
addCategory()

getAllCategories()

deleteCategory()

updateCategory()

searchCategory()
*/

package supermarket_db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO{
	
	// add category function
	public boolean addCategory(Category c) {
	    try {
	        Connection conn = DBConnection.getConnection();

	        String name = c.getName().trim();

	        String checkSql = "SELECT COUNT(*) FROM Categories WHERE LOWER(category_name) = LOWER(?)";
	        PreparedStatement checkPs = conn.prepareStatement(checkSql);
	        checkPs.setString(1, name);

	        ResultSet rs = checkPs.executeQuery();
	        rs.next();

	        if (rs.getInt(1) > 0) {
	            conn.close();
	            return false;
	        }

	        String sql = "INSERT INTO Categories (category_name) VALUES (?)";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, name);

	        ps.executeUpdate();

	        conn.close();
	        return true;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
public List<Category> getAllCategories(){
	
	List<Category> categories = new ArrayList<>();
	
	try {
	Connection conn = DBConnection.getConnection();
	
	String sql = "SELECT * FROM Categories";
	
	Statement stmt = conn.createStatement();
	
	// retrieve data from database
	ResultSet rs = stmt.executeQuery(sql);
	
	while (rs.next()) {//move row by row till no rows left
		
		Category c = new Category(
				//retrieve the value of the designated column in the current row
				rs.getInt("category_id"),
				rs.getString("category_name")
				);
		
		categories.add(c);
	}
	
	conn.close();
	}
	
	catch (Exception e){ 
		e.printStackTrace();
	}
	return categories;
}

// delete category function
public boolean deleteCategory(int id) {
	
	try {
		// connect to database 
		Connection conn = DBConnection.getConnection();
	
		String sql = 
				"DELETE FROM Categories " +
				"WHERE category_id = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setInt(1, id);
		
		int rows = ps.executeUpdate();
		
		// close connection
		conn.close();
	return rows > 0;
	
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
	
}
	


// search categories function
public List<Category> searchCategories(String keyword){
	List<Category>  categories = new ArrayList<>();
	
	try {
		// connect to database
		Connection conn = DBConnection.getConnection();
	
		String sql = 
				"SELECT * " +
				"FROM Categories " +
				"WHERE category_name LIKE ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		// insert user input into first place holder of database command template
		ps.setString(1, "%" + keyword + "%");
		
		// execute select query and return rows
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) { // move row by row
			categories.add( // insert newly built Category object into categories array list
					new Category( // create java object Category
							rs.getInt("category_id"), // grab the integer id from database
							rs.getString("category_name")) // grab the text name from database
					);
		}
	conn.close();
	
	} 
	
	
	catch (Exception e) {
		e.printStackTrace();
	}
	
	return categories;
}
	

public void updateCategory(Category c) {
	
	try {
		// connect to database
		Connection conn = DBConnection.getConnection();
	
		// sql template
		String sql = 
				"UPDATE Categories " +
				"SET " +
				"category_name = ? " +
				"WHERE category_id = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
	
		// match data to slots in sql template
		ps.setString(1, c.getName());
		ps.setInt(2, c.getId());
		
		// execute update sql statement
		ps.executeUpdate();
		
		conn.close();
	
	} 
	
	
	catch (Exception e) {
		e.printStackTrace();
	}
	
	
	
}
	
	
	
}