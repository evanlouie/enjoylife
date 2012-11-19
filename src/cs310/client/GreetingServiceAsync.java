package cs310.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;

	void getParkPage(String park_id, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	void getParksTable(AsyncCallback<String> callback);
	void login(String email, String password, AsyncCallback<String> callback) throws IllegalArgumentException;
	void register(String email, String password, String fname, String lname, AsyncCallback<String> callback) ;
	void favorites(String user_id, AsyncCallback<String> callback);
	void addFavorite(String user_id, String park_id, AsyncCallback<String> callback);
	void removeFavorite(String user_id, String park_id, AsyncCallback<String> callback);
}
