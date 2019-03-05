import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONObject;
import org.json.simple.JSONArray;

public class Path10 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://targetschema-new.cqzcaawwqsmw.us-east-1.rds.amazonaws.com/ccp_lgh_db", "secured",
				"pwd4secured");
		String query = "SELECT * FROM order_activity LIMIT 5";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int rscount = rsmd.getColumnCount();
		for (int j = 1; j <= rscount; j++) {
			sb.append("order_activity@"+rsmd.getColumnName(j));	
		}
		
		while (rs.next()) {
			String str ="";
			for (int i = 1; i <= rscount; i++) {
				
				str+=rs.getObject(i).toString()+",";

			}
			str = str.substring(0, str.length()-1);
			
			sb.append("\n");
			sb.append(str);
			
		}
		System.out.println(sb);
		
	}

}
