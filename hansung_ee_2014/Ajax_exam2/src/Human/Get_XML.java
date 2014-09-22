package Human;

import java.sql.ResultSet;

public class Get_XML {	
	ResultSet rs;
	StringBuffer sb;
	
	OracleDao oracleDao;
		
	public StringBuffer getXml(){
		try{
			oracleDao = OracleDao.getInstance();
			rs = oracleDao.getStudentList();
			
			sb=new StringBuffer();
			sb.append("<?xml version='1.0' encoding='utf-8' ?>\n");
			sb.append("<human>\n");
					
			while(rs.next()){			
				sb.append("<Member>\n");
				sb.append("<id>"+rs.getString("id")+"</id>\n");
				sb.append("<pass>"+rs.getString("pw")+"</pass>\n");
				sb.append("</Member>\n");
			}
			sb.append("</human>");
			
			oracleDao.closeConnection();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return sb;
   }
}




