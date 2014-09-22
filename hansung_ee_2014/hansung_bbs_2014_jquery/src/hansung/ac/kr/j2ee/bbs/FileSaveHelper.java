package hansung.ac.kr.j2ee.bbs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileSaveHelper {
	static void save(String fileName, InputStream in) throws IOException{
		String saveDir = "D:/upload";
		File file = new File(saveDir + "/" +fileName);
		System.out.println(file.getAbsolutePath());
		FileOutputStream os = null;
		

		os = new FileOutputStream(file);
		int temp = -1;
		while((temp = in.read()) != -1){
			os.write(temp);
		}
		
		if(os != null){
			os.close();
		}
		in.close();
		
	}//save method end
}
