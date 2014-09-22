package hansung.ac.kr.j2ee.bbs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownLoadImpl implements BBS_Service {
	
	@Override
	public String BBS_hansung(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		String fileName= null;
		String realFolder="D:/upload/";		
		File file = new File(realFolder+req.getParameter("fname"));
		
		resp.setContentType("application/download");
		resp.setContentLength((int) file.length());

		String userAgent = req.getHeader("User-Agent");
		System.out.println(userAgent);
		
		boolean ie = userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1;
		
		if (ie) {
			fileName = URLEncoder.encode(file.getName(),"utf-8").replace("+","%20");
		} else {
			fileName = new String(file.getName().getBytes("utf-8"),"iso-8859-1").replace("+","%20");
		}

		resp.setHeader("Content-Disposition", "attachment; filename=\""+fileName+ "\";");
		OutputStream out = resp.getOutputStream();
		FileInputStream fis = null;
		
		try{
			int temp;	
			fis = new FileInputStream(realFolder+file.getName());
			while((temp=fis.read()) != -1){
				out.write(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(fis!=null){
				try{
					fis.close();					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}

		return null;
	}

}