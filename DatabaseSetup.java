package supermarket_db;
import java.sql.*;
import java.sql.Connection;


public class DatabaseSetup {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/supermarket_db";
        String user = "root";
        String password = "fdshk12345@@";

        Connection conn = null;
        Statement stmt = null;

        try {
            // Load MySQL JDBC driver
            //Class.forName("com.mysql.cj.jdbc.Driver");
        	
            // Connect to MySQL
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database!");

            stmt = conn.createStatement();

            // -----------------------------------------------------
            // 1. CREATE TABLES
            // -----------------------------------------------------

            String createBranch =
            		"CREATE TABLE IF NOT EXISTS Branches("+
            		"branch_id INT PRIMARY KEY AUTO_INCREMENT,"+
            		"branch_name VARCHAR(100) NOT NULL,"+
            		"city VARCHAR(50),"+
            		"address VARCHAR(255),"+
            		"branch_phone_number VARCHAR(20)"+
            		");";
            
            String createCategory = 
            		"CREATE TABLE IF NOT EXISTS Categories ("+
            		"category_id INT PRIMARY KEY AUTO_INCREMENT,"+
            		"category_name VARCHAR(100) NOT NULL"+
            		");";
            
            String createProduct =
            	    "CREATE TABLE IF NOT EXISTS Products (" +
            	    "product_id INT PRIMARY KEY AUTO_INCREMENT," +
            	    "product_name VARCHAR(100) NOT NULL," +
            	    "barcode_number VARCHAR(50)," +
            	    "retail_price DOUBLE," +
            	    "cost_price DOUBLE," +
            	    // a product belongs to one category, whereas a category contains 
            	    // many types of products (Category - 1:N - Product)
            	    "category_id INT,"+
            	    
            	    "FOREIGN KEY (category_id) REFERENCES Categories(category_id)"+
            	    ");";
            
            String createSupplier =
                    "CREATE TABLE IF NOT EXISTS Suppliers(" +
                    "supplier_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "company_name VARCHAR(100) NOT NULL," +
                    "contact_person VARCHAR(100) NOT NULL," +
                    "supplier_phone_number VARCHAR(20) NOT NULL," +
                    "supplier_email VARCHAR(100) UNIQUE," +
                    "city VARCHAR(50)," +
                    "street VARCHAR(100)," +
                    "postal_code VARCHAR(20)" +
                    ");";
              
              String createCustomer =
            	    "CREATE TABLE IF NOT EXISTS Customers (" +
            	    "customer_id INT PRIMARY KEY AUTO_INCREMENT," +
            	    "customer_name VARCHAR(100) NOT NULL," +
            	    "phone_number VARCHAR(20) UNIQUE," +
            	    "customer_email VARCHAR(100) UNIQUE," +
            	    "customer_address VARCHAR(255)," +
            	    "registration_date DATE" +
            	    ");";
              
            String createEmployee = 
            		"CREATE TABLE IF NOT EXISTS Employees (" +
            		"employee_id INT PRIMARY KEY AUTO_INCREMENT," +
            		"employee_name VARCHAR(100)," +
            		"position VARCHAR(50)," +
            		"salary DOUBLE," +
            		"hire_date DATE," +
            		"manager_id INT," +
            		// Employee manages Employees
            		"FOREIGN KEY (manager_id) REFERENCES Employees(employee_id)" +
            		");";
            
            String createHourlyEmployee = 
            		"CREATE TABLE IF NOT EXISTS Hourly_Employees (" +
            		"employee_id INT PRIMARY KEY," +
            		"hourly_wages DOUBLE," +
            		"hours_worked INT," +
            		
            		"FOREIGN KEY (employee_id) REFERENCES Employees(employee_id)" +
            		");";
            
                String createContractEmployee = 
            		"CREATE TABLE IF NOT EXISTS ContractEmployees (" +
            		"employee_id INT PRIMARY KEY," +
            		"contract_salary DOUBLE," +
            		"contract_end_date DATE," +
            		
					"FOREIGN KEY (employee_id) REFERENCES Employees(employee_id)" +
					");";
                
                String createWarehouse =
                        "CREATE TABLE IF NOT EXISTS Warehouses (" +
                        "warehouse_id INT PRIMARY KEY AUTO_INCREMENT," +
                        "city VARCHAR(100)," +
                        "street VARCHAR(150)," +
                        "postal_code INT," +
                        "capacity INT," +
                        "warehouse_email VARCHAR(100)," +
                        "manager_id INT," +
                        "UNIQUE (city, street, warehouse_email)" +
                        ");";
            String createInventory = 
            		"CREATE TABLE IF NOT EXISTS Inventory("+
            		"inventory_id INT PRIMARY KEY AUTO_INCREMENT,"+
            		"quantity INT,"+
            		"last_restocked DATE,"+
            		"expiration_date DATE,"+
            		
            		"product_id INT,"+
            		"branch_id INT,"+
            		"warehouse_id INT,"+
            		
            		//Inventory belongs to one product, one branch, one warehouse
            		"FOREIGN KEY (product_id) REFERENCES Products(product_id),"+
            		"FOREIGN KEY (branch_id) REFERENCES Branches(branch_id),"+
            		"FOREIGN KEY (warehouse_id) REFERENCES Warehouses(warehouse_id)"+
            		");";
            
            String createTransaction =
            	    "CREATE TABLE IF NOT EXISTS Transactions (" +
            	    "transaction_id INT PRIMARY KEY AUTO_INCREMENT," +
            	    "customer_id INT NOT NULL," +
            	    "transaction_date DATE," +
            	    "transaction_time TIME," +
            	    "total_amount DOUBLE," +
            	    "FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)" +
            	    ");";
            
            
            String createTransactionProduct =
            	    "CREATE TABLE IF NOT EXISTS Transaction_Products (" +
            	    "transaction_id INT," +
            	    "product_id INT," +
            	    "quantity INT," +
            	    "PRIMARY KEY (transaction_id, product_id)," +
            	    "FOREIGN KEY (transaction_id) REFERENCES Transactions(transaction_id)," +
            	    "FOREIGN KEY (product_id) REFERENCES Products(product_id)" +
            	    ");";

          
            String createLoyaltyCard =
            	    "CREATE TABLE IF NOT EXISTS LoyaltyCard (" +
            	    "loyalty_card_id INT PRIMARY KEY AUTO_INCREMENT," +
            	    "loyalty_points INT DEFAULT 0," +
            	    "issue_date DATE" +
            	    ");";
            
            String createPurchase =
            	    "CREATE TABLE IF NOT EXISTS Purchase (" +
            	    "purchase_id INT PRIMARY KEY AUTO_INCREMENT," +
            	    "purchase_date DATE," +
            	    "total_cost DOUBLE," +
            	    "status VARCHAR(50)" +
            	    ");";
            
            String createDiscount =
            	    "CREATE TABLE IF NOT EXISTS Discount (" +
            	    "discount_id INT PRIMARY KEY AUTO_INCREMENT," +
            	    "discount_name VARCHAR(50) NOT NULL," +
            	    "discount_type VARCHAR(50) NOT NULL," +
            	    "value DOUBLE NOT NULL," +
            	    "start_date DATE," +
            	    "end_date DATE," +
            	    "points_required INT DEFAULT NULL" +
            	    ");";
            String createPayment =
            	    "CREATE TABLE IF NOT EXISTS Payment (" +
            	    "transaction_id INT," +
            	    "payment_no INT," +
            	    "payment_method VARCHAR(50) NOT NULL," +
            	    "amount DOUBLE NOT NULL," +
            	    "PRIMARY KEY (transaction_id, payment_no)," +
            	    "FOREIGN KEY (transaction_id) REFERENCES Transactions(transaction_id)" +
            	    ");";
            
            
            stmt.executeUpdate(createBranch);
            stmt.executeUpdate(createCategory);

            stmt.executeUpdate(createProduct);

            stmt.executeUpdate(createSupplier);

            stmt.executeUpdate(createCustomer);

            stmt.executeUpdate(createEmployee);

            stmt.executeUpdate(createHourlyEmployee);
            stmt.executeUpdate(createContractEmployee);

            stmt.executeUpdate(createWarehouse);

            stmt.executeUpdate(createInventory);

            stmt.executeUpdate(createTransaction);

            stmt.executeUpdate(createTransactionProduct);

            stmt.executeUpdate(createLoyaltyCard);

            stmt.executeUpdate(createPurchase);

            stmt.executeUpdate(createDiscount);

            stmt.executeUpdate(createPayment); 
            
            System.out.println("Tables created (if not already present).");

            // -----------------------------------------------------
            // 2. INSERT SAMPLE DATA
            // -----------------------------------------------------
            
            String insertCategory =
            	    "INSERT INTO Categories(category_name) VALUES " +
            	    "('Dairy')," +
            	    "('Bakery')," +
            	    "('Beverages');";
            String insertBranch =
            	    "INSERT INTO Branches(branch_name, city, address, branch_phone_number) VALUES " +
            	    "('Ramallah Branch','Ramallah','Al-Manara Street','022123456')," +
            	    "('Nablus Branch','Nablus','Main Street','092123456');";
            String insertSupplier =
                    "INSERT INTO Suppliers " +
                    "(company_name, contact_person, supplier_phone_number, supplier_email, city, street, postal_code) VALUES " +

                    "('Fresh Food Co', 'Ahmad Ali', '0591111111', 'fresh@food.com', 'Ramallah', 'Al-Irsal Street', '00970')," +

                    "('Dairy Factory', 'Sami Hasan', '0592222222', 'dairy@factory.com', 'Nablus', 'Rafidia Street', '40001');";
            String insertEmployee =
            	    "INSERT INTO Employees(employee_name, position, salary, hire_date, manager_id) VALUES " +
            	    "('Mohammad Khaled','Manager',5000,'2024-01-10',NULL)," +
            	    "('Lina Ahmad','Cashier',2500,'2024-03-01',1)," +
            	    "('Omar Saleh','Stock Clerk',2700,'2024-02-15',1);";
            String insertHourlyEmployee =
            	    "INSERT INTO Hourly_Employees(employee_id, hourly_wages, hours_worked) VALUES " +
            	    "(2,15,160)," +
            	    "(3,18,170);";
            
            String insertContractEmployee =
            	    "INSERT INTO ContractEmployees(employee_id, contract_salary, contract_end_date) VALUES " +
            	    "(1,5000,'2027-12-31');";
            String insertWarehouse =
            	    "INSERT INTO Warehouses(city, street, postal_code, capacity, warehouse_email, manager_id)\r\n"
            	    + "VALUES\r\n"
            	    + "('Ramallah','Industrial Zone',12345,5000,'warehouse1@market.com',1),\r\n"
            	    + "('Nablus','Main Area',54321,3000,'warehouse2@market.com',1);";
            String insertProduct =
            	    "INSERT INTO Products(product_name, barcode_number, retail_price, cost_price, category_id) VALUES " +
            	    "('Milk','10001',5.5,4.0,1)," +
            	    "('Bread','10002',2.0,1.2,2)," +
            	    "('Juice','10003',6.0,4.5,3);";
            String insertInventory =
            	    "INSERT INTO Inventory(quantity,last_restocked,expiration_date,product_id,branch_id,warehouse_id) VALUES " +
            	    "(100,'2026-07-01','2026-09-01',1,1,1)," +
            	    "(200,'2026-07-02','2026-08-15',2,1,1)," +
            	    "(150,'2026-07-03','2026-10-01',3,2,2);";
            
            String insertCustomer =
            	    "INSERT INTO Customers(customer_name,phone_number,customer_email,customer_address,registration_date) VALUES " +
            	    "('Ali','0591234567','ali@gmail.com','Ramallah','2025-01-01')," +
            	    "('Sara','0569876543','sara@gmail.com','Nablus','2025-02-10');";
            String insertLoyaltyCard =
            	    "INSERT INTO LoyaltyCard(loyalty_points,issue_date) VALUES " +
            	    "(150,'2025-01-01')," +
            	    "(300,'2025-02-10');";
            
            String insertTransaction =
            	    "INSERT INTO Transactions(customer_id,transaction_date,transaction_time,total_amount) VALUES " +
            	    "(1,'2026-07-15','10:30:00',15.5)," +
            	    "(2,'2026-07-15','11:15:00',10.0);";
           
            String insertTransactionProduct =
            	    "INSERT INTO Transaction_Products(transaction_id,product_id,quantity) VALUES " +
            	    "(1,1,2)," +
            	    "(1,2,1)," +
            	    "(2,3,1);";

            String insertDiscount =
            	    "INSERT INTO Discount(discount_name,discount_type,value,start_date,end_date,points_required) VALUES " +
            	    "('Summer Sale','percentage',10,'2026-07-01','2026-07-31',NULL)," +
            	    "('Loyalty Reward','loyalty',5,'2026-01-01','2026-12-31',100);";

            String insertPurchase =
            	    "INSERT INTO Purchase(purchase_date,total_cost,status) VALUES " +
            	    "('2026-07-10',1200,'Delivered')," +
            	    "('2026-07-12',800,'Pending');";

            String insertPayment =
            	    "INSERT INTO Payment(transaction_id,payment_no,payment_method,amount) VALUES " +
            	    "(1,1,'Cash',15.5)," +
            	    "(2,1,'Credit Card',10.0);";
            
            // Insert only if rows do not exist in category
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Categories");
            rs.next();

            if (rs.getInt(1) == 0) {
                stmt.executeUpdate(insertCategory);
            }
            stmt.executeUpdate(insertBranch);
            ResultSet rs2 = stmt.executeQuery("SELECT COUNT(*) FROM suppliers");
            rs2.next();

            if (rs2.getInt(1) == 0) {
                stmt.executeUpdate(insertSupplier);
            }
            ResultSet rs1 = stmt.executeQuery("SELECT COUNT(*) FROM Employees");
            rs1.next();

            if (rs1.getInt(1) == 0) {
                stmt.executeUpdate(insertEmployee);
            }

            stmt.executeUpdate(insertWarehouse);

            stmt.executeUpdate(insertProduct);
            stmt.executeUpdate(insertInventory);

            stmt.executeUpdate(insertCustomer);
            stmt.executeUpdate(insertLoyaltyCard);

            stmt.executeUpdate(insertTransaction);
            stmt.executeUpdate(insertTransactionProduct);

            stmt.executeUpdate(insertDiscount);
            stmt.executeUpdate(insertPurchase);
            stmt.executeUpdate(insertPayment);

            System.out.println("Sample data inserted.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}