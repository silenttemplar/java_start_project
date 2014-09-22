package hansung.ac.kr.j2ee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBConnection {
	
	private final String serverIP="127.0.0.1";
	private final String portNum="1521";
	private final String id="templar";
	private final String pw="1234";
	
	private ArrayList<LoginVO> loginList;
	private PreparedStatement pstmt;
	private ResultSet rs;

	Connection con;
	static DBConnection dbcon = new DBConnection();
	
	private DBConnection(){
		
	}
	
	public static DBConnection getInstance(){
		return dbcon;
	}
	
	public Connection getConnection(){

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url="jdbc:oracle:thin:@"+serverIP+":"+portNum+":XE";
			con = DriverManager.getConnection(url,id,pw);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public ArrayList<LoginVO>getAllId(){
		loginList = new ArrayList<LoginVO>();
		con = this.getConnection();
		
		try {
			
			String sql = "select * from login";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				LoginVO obj = new LoginVO();
				obj.setId(rs.getString("id"));
				obj.setPass(rs.getString("pw"));
				loginList.add(obj);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				rs.close();
				pstmt.close();
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return loginList;
	}
	
	public void join_member(String id, String pw){
		con = this.getConnection();
		
		try {
			String sql = "insert into login values(?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				pstmt.close();
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void check_member(){
		
	}
}
