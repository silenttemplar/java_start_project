<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Test Result</title>
</head>
<body>
<%		
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");		
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Connection con =DriverManager.getConnection(url,"templar","1234");	
		String sqlStr = "SELECT * FROM login";
		PreparedStatement pstmt = con.prepareStatement(sqlStr);
		ResultSet rs = pstmt.executeQuery();
		
		String temp="<table border='1'>";
		while(rs.next()){				
			temp+="<tr>";
			temp+="<td>"+rs.getString("id")+"</td>";
			temp+="<td>"+rs.getString("pw")+"</td>";
			temp+="</tr>";							
		}
		temp+="</table>";
		
		//reposne의 스트림을 얻어서 request객체로 전달
		PrintWriter pw=response.getWriter();	
		pw.println(temp);
		
		pw.close();				
		rs.close();
		pstmt.close();
		con.close();
	}catch(Exception e){
		e.printStackTrace();
	}			
%>
</body>
</html>