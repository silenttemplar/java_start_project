package hansung.ac.kr.j2ee;

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

public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 2291838995335164042L;
	HashMap<String, Object> hm; 
	TestInterface tt;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		//명령어와 명령어 처리 클래스를 pair로 저장
		hm= new HashMap<String, Object>();

		/*
		 * web.xml에서의 정의된 init-param의 이름을 인자로 줄경우
		 * 해당 properties file의 절대경로(또는 상대경로) 획득
		 * properties file은 명령어와 처리클래스가 매핑되어 있다.
		 * 명령어와 처리클래스의 매핑정보를 저장할 properties객체 생성
		 */
		String path = config.getInitParameter("testservlet");
		Properties pr = new Properties();
		FileReader fr = null;
		
		/*
		 *  얻어온 경로를 이용해서, FileReader 생성
		 */
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
		
		/*
		 * 읽어온 properties file를 properties의 객체에 저장
		 * properties 객체를 순회, 클래스 객체 생성
		 * Properties 클래스에 들어가 있는 key-value는 String형
		 * String type를 Object type으로 type casting
		 */
		Iterator keylter = pr.keySet().iterator();
		while(keylter.hasNext()){
			String key = (String)keylter.next();
			String value = pr.getProperty(key);
			
			try {
				/*
				 * 해당 문자열을 클래스로 생성 -> 해당클래스의 객체를 생성(newInstance())
				 * 이후 Map객체인 hm에 객체 저장.
				 */
				Class valueClass = Class.forName(value);
				Object valueInstance = valueClass.newInstance();
				hm.put(key, valueInstance);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req,resp);		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req,resp);
	}
	
	//doGet, doPost 요청 처리를 위해 정의된 method
	private void process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 요청한 request에서 URI 획득
		 * 획득한 URI을 다시 ContextPath만 뺀 URI획득
		 * HashMap를 이용해서 key로 해당클래스 호출 -> 반환값은 TestInterface클래스에 저장
		 * 해당클래스의 메소드 hansung을 (interface로 정의된 메소드명) 호출, 처리
		 */
		
		String view = null;
		
		try {			
            String command = req.getRequestURI();           
            if (command.indexOf(req.getContextPath()) == 0) {
               command =command.substring(req.getContextPath().length());
            }
            tt = (TestInterface)hm.get(command); 
            view = tt.hansung(req, resp);
        } catch(Throwable e) {
           e.printStackTrace();
        }
		
		if(view != null){
			RequestDispatcher dispatcher = req.getRequestDispatcher(view);
			dispatcher.forward(req, resp);
		}
	}//process method  end
}
