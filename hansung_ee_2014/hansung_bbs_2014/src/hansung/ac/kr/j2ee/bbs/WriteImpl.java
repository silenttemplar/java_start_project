package hansung.ac.kr.j2ee.bbs;

import hansung.ac.kr.j2ee.bbs.db.BBS_Dto;
import hansung.ac.kr.j2ee.bbs.db.BBS_OracleDao;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class WriteImpl implements BBS_Service {

	@Override
	public String BBS_hansung(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		//한글처리
		req.setCharacterEncoding("utf-8");
		BBS_Dto article = new BBS_Dto();
		BBS_OracleDao dao = BBS_OracleDao.getInstance();
		
		article.setId(req.getSession().getAttribute("id").toString());
		
		//servlet 3.0 규약에 따르지 않고 파라미터 읽음
		article.setTitle(req.getParameter("title"));
		article.setContent(req.getParameter("content"));
		
		//servlet 3.0 규약에 따르고 파라미터 읽음
		//article.setTitle(readParameterValue(req.getParameter("title")))
		//article.setContent(readParameterValue(req.getParameter("content")));
		
		check_part(req.getParts());
		
		Part filePart = req.getPart("fname");
		String fileName = getFileName(filePart);
		article.setFname(fileName);
		
		if(fileName != null || !fileName.equals("")){
			FileSaveHelper.save(fileName, filePart.getInputStream());
		}

		dao.insertArticle(article);
		
		return "/list.bbs";
	}
	
	public String getFileName(Part filePart) throws UnsupportedEncodingException{
		for(String cd : filePart.getHeader("Content-Disposition").split(";")){
			if(cd.trim().startsWith("filename")){
				return cd.substring(cd.indexOf('=')+1).trim().replace("\"", "");
			}
		}
		return null;
	}
	
	public void check_part(Collection<Part> cp ){
		Iterator<Part> it = cp.iterator();
		
		while(it.hasNext()){
			Part pa = it.next();
			System.out.println(pa.getName()+" 입니다.");
			
			Collection<String> co = pa.getHeaderNames();
			Iterator<String> it2 = co.iterator();
			while(it2.hasNext()){
				String name = it2.next();
				System.out.println("해더이름: "+name+", 해더 값: "+pa.getHeader(name));				
			}
			
		}
	}
	
	/*
	 *  multipart 읽어온 뒤 각각의 part를 한글 처리를 위해서 정의된 메소드
	 *  charset은 "utf-8"
	 *  part에서 inputstream을 얻은 뒤
	 *  -> 읽어온 char형을 stringbuilder에 append하고
	 *  -> 다읽으면 String으로 형변환
	 */
	public String readParameterValue(Part part){
		InputStreamReader reader;
		StringBuilder 	builder = new StringBuilder();
		int temp = -1;
		
		try{
			reader = new InputStreamReader(part.getInputStream(), "utf-8");
			while((temp = reader.read()) != -1){
				builder.append((char)temp);
			}
			
			reader.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return builder.toString();
	}

}
