package cs310.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
	String getParkPage(String park_id) throws IllegalArgumentException;
	String getParksTable();
	String login(String email, String password) throws IllegalArgumentException;
	String register(String email, String password, String fname, String lname);
	String favorites(String user_id);
	String addFavorite(String user_id, String park_id);
	String removeFavorite(String user_id, String park_id);

}
