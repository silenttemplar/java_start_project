package hansung.ac.kr.j2ee.bbs;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BBS_Servlet extends HttpServlet {

	private static final long serialVersionUID = 6739028505615710861L;
	HashMap<String, Object> map_servlet;
	BBS_Service bbsService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		map_servlet= new HashMap<String, Object>();
		
		String path = config.getInitParameter("bbs_servlet");
		Properties pr = new Properties();
		FileReader fr = null;
		
		try {
			fr = new FileReader(path);
			pr.load(fr);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(fr != null){
				try {
					fr.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}//if문 end
		}//finally end
		
		Iterator keylter = pr.keySet().iterator();
		while(keylter.hasNext()){
			String key = (String)keylter.next();
			String value = pr.getProperty(key);
			
			try {
				Class valueClass = Class.forName(value);
				Object valueInstance = valueClass.newInstance();
				map_servlet.put(key, valueInstance);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req,resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req,resp);
	}
	
	public void process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String view = null;
		
		try {			
            String command = req.getRequestURI();           
            if (command.indexOf(req.getContextPath()) == 0) {
               command =command.substring(req.getContextPath().length());
            }
            bbsService = (BBS_Service)map_servlet.get(command); 
            view = bbsService.BBS_hansung(req, resp);
        } catch(Throwable e) {
           e.printStackTrace();
        }
		
		if(view != null){
			RequestDispatcher dispatcher = req.getRequestDispatcher(view);
			dispatcher.forward(req, resp);
		}//if문 end
	}//process method end
	
}
