package Human;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleDao implements Dao_Interface{
	private final String serverIP="127.0.0.1";
	private final String portNum="1521";
	private final String id="templar";
	private final String pw="1234";
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	static OracleDao obj = new OracleDao();
	
	private OracleDao(){
		
	}
	
	public static OracleDao getInstance(){
		return obj;
	}
	
	public Connection getConnection(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@"+serverIP+":"+portNum+":xe";
			conn =DriverManager.getConnection(url,id,pw);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("getConnection() 실패");
			e.printStackTrace();
		}
		
		return conn;
	}

	@Override
	public ResultSet getStudentList() throws SQLException {
		conn = this.getConnection();
		String sqlStr = "SELECT * FROM login";
		pstmt = conn.prepareStatement(sqlStr);
		rs = pstmt.executeQuery();
		
		return rs;
	}
	
	public void closeConnection() throws SQLException{
		if(rs != null){
			rs.close();
		}
		pstmt.close();
		conn.close();
	}
}
