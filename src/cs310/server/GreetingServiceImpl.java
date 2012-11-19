package cs310.server;

import cs310.client.GreetingService;
import cs310.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String greetServer(String input) throws IllegalArgumentException {
//		// Verify that the input is valid. 
//		if(input.length() < 3 ) {
//			throw new IllegalArgumentException("Name must be longer that 3 letters");
//		}

//		String serverInfo = getServletContext().getServerInfo();
//		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
//		input = escapeHtml(input);
//		userAgent = escapeHtml(userAgent);

//		return "Hello, " + input + "!<br><br>I am running " + serverInfo
//				+ ".<br><br>It looks like you are using:<br>" + userAgent;
//		
		XmlUtil util = new XmlUtil();
		util.loadXML("http://www.evanlouie.com/cs310/index.php/api/park/name/" + input);

		return util.getxmlSring();
	}
	
	public String getParkPage(String park_id) throws IllegalArgumentException {
		try {
			Integer.parseInt(park_id);
		} catch(Exception e) {
			throw new IllegalArgumentException("Invalid Input");
		}
		ParkGrabber grabber = new ParkGrabber();
		return grabber.grab(park_id);
	}
	
	public String getParksTable() {
		ParkTableGrabber ptg = new ParkTableGrabber();
		return ptg.grab();
	}
	
	public String login(String email, String password) throws IllegalArgumentException {
		User user = new User();
		String users = user.login(email, password);
		boolean contains = users.contains("<email>"+email+"</email>");
		if (contains) {
			return user.getId(email);
		} else {
			throw new IllegalArgumentException("Not such registered user");
		}
		
	}
	
	public String register(String email, String password, String fname, String lname) {
		User user = new User();
		return user.addUser(email, password, fname, lname);
	}
	
	public String favorites(String user_id) {
		User user = new User();
		return user.favorites(user_id);
	}
	
	public String addFavorite(String user_id, String park_id) {
		User user = new User();
		user.addFavorite(user_id, park_id);
		return this.favorites(user_id);
	}
	
	public String removeFavorite(String user_id, String park_id) {
		User user = new User();
		user.removeFavorite(user_id, park_id);
		return this.favorites(user_id);
	}
	

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
}
