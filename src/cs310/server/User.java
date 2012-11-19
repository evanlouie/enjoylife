package cs310.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class User {
	public String users = "";
	private String server = "http://evanlouie.com/cs310/index.php/api/user/";
	
	
	public String addUser(String email, String password, String fname, String lname) {
		String url = server + "add?email=" + email +"&password=" + password + "&fname=" + fname + "&lname=" + lname;
		System.out.println(url);
		XmlUtil util = new XmlUtil();
		util.loadXML(url);
		return util.getxmlSring();
		
	}
	public void getUsers() {
		XmlUtil util = new XmlUtil();
		util.loadXML(server);
		users = util.getxmlSring();
		
	}
	
	public String getId(String email) {
		String string = "";
		String html = "";
		try {
			URL url = new URL(server + "/getid?email="+email);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String inputLine;
			string = in.readLine();
			while ((inputLine = in.readLine()) != null) {
				string = string + inputLine;
			}
			System.out.println(string);
			html = string;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return html;
	}
	public void addFavorite(String user_id, String park_id) {
		String url = server + "add_favorite?user_id=" + user_id + "&park_id=" + park_id;
		XmlUtil util = new XmlUtil();
		util.loadXML(url);
	}
	
	public void removeFavorite(String user_id, String park_id) {
		String url = server + "remove_favorite?user_id=" + user_id + "&park_id=" + park_id;
		XmlUtil util = new XmlUtil();
		util.loadXML(url);
	}	
	
	public String getUser(String user_id) {
		String url = server +"id/" + user_id;
		XmlUtil util = new XmlUtil();
		util.loadXML(url);
		return util.getxmlSring();
	}
	
	public String login(String email, String password) {
		String url = server+"login?email="+email+"&password="+password;
		XmlUtil util = new XmlUtil();
		util.loadXML(url);
		return util.getxmlSring();
	}
	
	public String favorites(String user_id) {
		String string = "";
		String html = "";
		try {
			URL url = new URL(server + "/favorites/"+user_id);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String inputLine;
			string = in.readLine();
			while ((inputLine = in.readLine()) != null) {
				string = string + inputLine;
			}
			System.out.println(string);
			html = string;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return html;
	}
	
	
	public static void main(String[] argsv) {
		User user = new User();
//		user.getUsers();
//		System.out.println(user.users);
		user.addUser("32923023", "woejfwofj", "wjfie", "weifjwe");
	}
}
