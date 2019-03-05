import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


import org.json.JSONObject;
import org.json.simple.JSONArray;

public class NewJson {
	static JSONObject jsonObj = new JSONObject();
	static JSONArray jsonarray = new JSONArray();

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://sotero-perftest.cqzcaawwqsmw.us-east-1.rds.amazonaws.com/dbtest", "root",
				"Exafluence201");
		String query = "SELECT * FROM sbtest1 LIMIT "+ args[0].trim();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int rscount = rsmd.getColumnCount();
		JSONArray innerArray = new JSONArray();
		JSONObject recordArray = new JSONObject();

		while (rs.next()) {
			int assign = 0;
			// JSONObject temp = new JSONObject();
			JSONObject rowObj = new JSONObject();
			JSONArray rowArray = new JSONArray();
			for (int i = 1; i <= rscount; i++) {
				rowArray.add(rs.getObject(i).toString());

			}
			rowObj.put("ROWS", rowArray);

			innerArray.add(rowObj);

		}
		//recordArray.put("ROWS", innerArray);
		jsonObj.put("RECORDS", innerArray);

		JSONArray colArray = new JSONArray();
		for (int j = 1; j <= rscount; j++) {
			JSONObject temp1 = new JSONObject();
			// Here column names are splitting with table name
			String val = rsmd.getColumnName(j).contains(".") ? rsmd.getColumnName(j).split("\\.")[1]
					: rsmd.getColumnName(j);
			temp1.put("name", val);
			temp1.put("tablename", "sbtest1");
			colArray.add(temp1);
		}
		jsonObj.put("COLUMNS", colArray);
		jsonObj.put("aid", "8");
		jsonObj.put("tid", "0");
		// System.out.println(jsonObj.toString());
		FileWriter fw = new FileWriter("/home/ec2-user/newJson/" + args[0].trim() + ".txt");
		fw.write(jsonObj.toString());
		fw.flush();
	}

}
