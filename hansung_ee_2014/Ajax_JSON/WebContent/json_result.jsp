<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="org.json.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%		
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");		
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Connection con =DriverManager.getConnection(url,"templar","1234");		
	
		String sqlStr = "SELECT pw FROM login where id='aaa'";
		PreparedStatement pstmt = con.prepareStatement(sqlStr);
		ResultSet rs = pstmt.executeQuery();			
		HashMap<String,String> pass_Map= new HashMap<String,String>();
		if(rs.next()){
			pass_Map.put("pass",rs.getString(1));	
		}
		JSONObject jb= new JSONObject(pass_Map);
		PrintWriter pw=response.getWriter();	
		pw.println(jb.toString());
		pw.close();	//스트림안닫으면 에러가 발생		
		rs.close();
		pstmt.close();
		con.close();
	}catch(Exception e){
		e.printStackTrace();
	}			

%>
</body>
</html>