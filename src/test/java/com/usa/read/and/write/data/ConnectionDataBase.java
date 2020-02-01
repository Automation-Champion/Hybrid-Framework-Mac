package com.usa.read.and.write.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectionDataBase {
    
	//Step 1) Make a connection to the Database using method.
	//DriverManager.getConnection(URL, "userid", "password") 
	
	//Step 2) Create Query to the Database using the Statement Object.
    //Statement stmt = con.createStatement();
	
	//Step 3) Send the query to database using execute query and store the results in the ResultSet object.
	//ResultSet rs = stmt.executeQuery(select * from employee;);
	
	public static ArrayList<String> getDataTableColumn(String SQLqyeries, String columnName) {
		ResultSet rs = null;
		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "hr", "hr");
			Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(SQLqyeries);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		ArrayList<String> columnValue = new ArrayList<String>();
		try {
			while (rs.next()) {
				columnValue.add(rs.getString(columnName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(columnValue);
		// step5 close the connection object
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return columnValue;

	}

	public static void main(String[] args) {
		String Queries ="SELECT department_name, max (salary)\r\n" +
				" FROM employees\r\n" +
				" Left join departments\r\n" +
				" ON employees.department_id = departments.department_id\r\n" +
				" GROUP BY department_name\r\n" +
				" HAVING max (salary)>5000\r\n" +
				" Order by max (salary) desc";
				getDataTableColumn(Queries, "Department_name");
				getDataTableColumn(Queries, "max(salary)");

	}
}