package Ajax;

public class LoginVO {
	private String id;
	private String pass;	
	
	public LoginVO(){};
	
	public LoginVO(String id,String pass){
		this.id = id;
		this.pass = pass;		
	};
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}	

}
