package hansung.ac.kr.j2ee;

import hansung.ac.kr.j2ee.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
	DBConnection dbcon;
	Connection con;
	
	public boolean login(String id, String pw){
		boolean flag = false;
		
		try {
			
			dbcon = DBConnection.getInstance();
			con = dbcon.getConnection();
			
			String sql = "select pass from pang where id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				if(pw.equals(rs.getString("pass"))){
					flag = true;
				}
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		return flag;
	}//login() end
	
	public int joinMember(String id, String pw){
		int flag = 0;
		
		try {
			dbcon = DBConnection.getInstance();
			con = dbcon.getConnection();
			
			String sql = "insert into pang values(?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			flag = pstmt.executeUpdate();
			
			
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		return flag;
	}
}
