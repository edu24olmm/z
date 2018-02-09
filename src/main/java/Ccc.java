//STEP 1. Import required packages
import java.sql.*;

public class Ccc {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://rdsrryl44r61ny3t853x.mysql.rds.aliyuncs.com:3306/xx?user=wangbo&password=123456&useUnicode=true&characterEncoding=UTF-8";
	// Database credentials
	static final String USER = "wangbo";
	static final String PASS = "123456";

	//咪咕音乐，咪咕爱唱，和新闻，百度贴吧 
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "select k.PackageName as pName ,count(taskId) as countTask from keep k GROUP BY k.PackageName order by count(taskId) desc";
			ResultSet rs = stmt.executeQuery(sql);
			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				String pName = rs.getString("pName");
				String countTask = rs.getString("countTask");
				// Display values
				System.out.print(", pName: " + pName);
				System.out.println(", countTask: " + countTask);
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
		System.out.println("Goodbye!");
	}// end main
}// end FirstExample