package cs310.server;

public class User {
	public String users = "";
	private String server = "http://localhost/evanlouie/cs310/index.php/api/user/";
	
	
	public void addUser(String email, String password, String fname, String lname) {
		String url = server + "add?email=" + email +"&password=" + password + "&fname=" + fname + "&lname=" + lname;
		System.out.println(url);
		XmlUtil util = new XmlUtil();
		util.loadXML(url);
		
	}
	public void getUsers() {
		XmlUtil util = new XmlUtil();
		util.loadXML(server);
		users = util.getxmlSring();
		
	}
	
	
	public void addFavorite(String user_id, String park_id) {
		String url = server + "?add_favorite&user_id=" + user_id + "&park_id=" + park_id;
		XmlUtil util = new XmlUtil();
		util.loadXML(url);
	}
	
	public void removeFavorite(String user_id, String park_id) {
		String url = server + "?remove_favorite&user_id=" + user_id + "&park_id=" + park_id;
		XmlUtil util = new XmlUtil();
		util.loadXML(url);
	}	
	
	public static void main(String[] argsv) {
		User user = new User();
//		user.getUsers();
//		System.out.println(user.users);
		user.addUser("32923023", "woejfwofj", "wjfie", "weifjwe");
	}
}
