import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Impala {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("com.firstworks.sql.SQLRelayDriver");
		Connection con = DriverManager.getConnection("jdbc:sqlrelay://18.210.41.129:9000/resultdb","cloudera","cloudera");
		System.out.println("Connected");
		

	}

}
