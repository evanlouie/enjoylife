package cs310.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import cs310.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.xml.client.XMLParser;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Helloworld implements EntryPoint {
	
	private String user_id = "0";
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);
	public static native void refreshFacebook() /*-{
		$wnd.refreshFacebook();
	}-*/;
	public static native void initSocial() /*-{
	  $wnd.initSharing();
//	  $wnd.initFacebook();
	}-*/; 
	public static native void initFacebook() /*-{
		$wnd.initFacebook();
	}-*/;
	public static native void initTable() /*-{
		$wnd.initTable();
	}-*/;
	public static native void forceRefreshFB() /*-{
		$wnd.FB.XFBML.parse();
	}-*/;
	
	public static native void refreshMaps() /*-{
		$wnd.refreshMaps();
	}-*/;
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button loginButton = Button.wrap(DOM.getElementById("loginButton"));
		final TextBox loginEmail = TextBox.wrap(DOM.getElementById("loginEmail"));
		final PasswordTextBox passwordBox = PasswordTextBox.wrap(DOM.getElementById("loginPassword"));
		
		final Button registerButton = Button.wrap(DOM.getElementById("registerButton"));
		final TextBox registerEmail = TextBox.wrap(DOM.getElementById("registerEmail"));
		final PasswordTextBox registerPassword = PasswordTextBox.wrap(DOM.getElementById("registerPassword"));
		final TextBox registerFname = TextBox.wrap(DOM.getElementById("registerFname"));
		final TextBox registerLname = TextBox.wrap(DOM.getElementById("registerLname"));

		final Button favoriteButton = Button.wrap(DOM.getElementById("favoriteButton"));
		// HelloWorldPage page = new HelloWorldPage();
		// JQMContext.changePage(page);

		final Button searchButton = Button.wrap(DOM
				.getElementById("search-button"));
		final TextBox nameField = new TextBox();
		final TextBox searchQuery = TextBox.wrap(DOM
				.getElementById("search-query"));
		nameField.setText("GWT User");
		final Label errorLabel = new Label();
		
		final ArrayList<Button> buttons = new ArrayList<Button>();

		// Focus the cursor on the name field when the app loads
		searchQuery.setFocus(true);
		searchQuery.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				searchButton.setEnabled(true);
				searchButton.setFocus(true);
			}
		});
		initFacebook();
		greetingService.getParksTable(new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.toString());
				
			}

			@Override
			public void onSuccess(String result) {
				DOM.getElementById("parksTableContainer").setInnerHTML(result);		
				initTable();
			}
			
		});
		class RegisterHandler implements ClickHandler, KeyUpHandler {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					register();
				}				
			}

			@Override
			public void onClick(ClickEvent event) {
				register();
			}
			public void register() {
				String email = registerEmail.getText();
				String password = registerPassword.getText();
				String fname = registerFname.getText();
				String lname = registerLname.getText();
//				Window.alert(email+password+fname+lname);
				greetingService.register(email, password, fname, lname, new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR:" + caught.toString());
						
					}

					@Override
					public void onSuccess(String result) {
						Window.alert("Registration Successful: Enjoy!");
						int length = Window.Location.getHref().length();
						Window.Location.replace(Window.Location.getHref().substring(0, length-9)+"#login");
						
					}
					
				});
			}
		}
		class LoginHandler implements ClickHandler, KeyUpHandler {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					login();
				}
			}

			@Override
			public void onClick(ClickEvent event) {
				login();
			}
			
			public void login() {
				String email = loginEmail.getText();
				String password = passwordBox.getText();
//				Window.alert(email+password);

					greetingService.login(email, password, new AsyncCallback<String>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert(caught.toString());
						}

						@Override
						public void onSuccess(String result) {
							user_id = result;
							int length = Window.Location.getHref().length();
//							Window.alert(Window.Location.getHref().substring(length-6, length));
							if (Window.Location.getHref().substring(length-6, length).equals("#login")) {
								Window.Location.replace(Window.Location.getHref().substring(0, length-6)+"#home");
								refreshMaps();
							} else {
								Window.Location.replace(Window.Location.getHref()+"#home");
								refreshMaps();
							}
							greetingService.favorites(user_id, new AsyncCallback<String>() {

								@Override
								public void onFailure(Throwable caught) {
									Window.alert("ERROR:"+caught.toString());
									
								}

								@Override
								public void onSuccess(String result) {
									DOM.getElementById("favouritesContent").setInnerHTML(result);
									
																
								}
								
							});
							initTable();
							
						}
					});
					
			}
			
		}
		class FavoriteButtonHandler implements ClickHandler {
			
			public String park_id;
			
			public FavoriteButtonHandler(String park_id) {
				this.park_id = park_id;
			}
			@Override
			public void onClick(ClickEvent event) {
				favorite(park_id);
				
			}
			public void favorite(String park_id) {
//				Window.alert("park:"+park_id+"user:"+user_id);
				greetingService.addFavorite(user_id, park_id, new AsyncCallback<String>(){
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Error: "+caught.toString());
						
					}

					@Override
					public void onSuccess(String result) {
//						favoriteButton.setEnabled(false);
						favoriteButton.setText("Added!");
						DOM.getElementById("favouritesContent").setInnerHTML(result);
//						Window.alert(result);
					}
					
				});
			}
		}
		class ListItemHandler implements ClickHandler {
			String park_id;
			
			ListItemHandler(String park_id) {
				this.park_id = park_id;
			}

			public void onClick(ClickEvent event) {
				loadParkPage();
			}
			
			private void loadParkPage() {
				greetingService.getParkPage(park_id, new AsyncCallback<String>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.toString());
						
					}
					@Override
					public void onSuccess(String result) {
//						Window.alert(result);
						DOM.getElementById("parkContainer").setInnerHTML(result);
						
						int length = Window.Location.getHref().length();
						if(Window.Location.getHref().substring(length-5, length).equals("#home")) {
							Window.Location.replace(Window.Location.getHref().substring(0, length-5)+"#place");
						}
						else {
							Window.Location.replace(Window.Location.getHref()+"#place");
						}
						FavoriteButtonHandler fbh = new FavoriteButtonHandler(park_id);
						favoriteButton.addClickHandler(fbh);
						initSocial();
						forceRefreshFB();
						
					}
				});
			}
		}

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}
			
			
			/**
			 * Send the name from the nameField to the server and wait for a
			 * response.
			 */
			private void sendNameToServer() {
				DOM.getElementById("lightbox").setAttribute("style", "display:block;");
				DOM.getElementById("lightbox-panel").setAttribute("style", "display:block");
				// First, we validate the input.
				errorLabel.setText("");
				String textToServer = searchQuery.getText();
				if (!FieldVerifier.isValidName(textToServer)) {
					errorLabel.setText("Please enter at least four characters");
					return;
				}

				// Then, we send the input to the server.
				searchButton.setEnabled(false);
				textToServerLabel.setText(textToServer);
				serverResponseLabel.setText("");
				greetingService.greetServer(textToServer, new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						Window.alert("Search text must be greater than 3 characters");
					}

					public void onSuccess(String xmlString) {
						searchButton.setEnabled(true);
						DOM.getElementById("lightbox").removeAttribute("style");
						DOM.getElementById("lightbox-panel").removeAttribute("style");

						while (DOM.getElementById("search_result_list")
								.hasChildNodes()) {
							DOM.getElementById("search_result_list")
							.removeChild(
									DOM.getElementById(
											"search_result_list")
											.getLastChild());
						}
						Park park = new Park();
						ArrayList<Park> parks = park
								.getParks(xmlString);
						ListItem ListItem = null;
						Element li;
						Iterator<Park> it = parks.iterator();
						ArrayList<Integer> parkids = new ArrayList<Integer>();
						while (it.hasNext()) {
							park = (Park) it.next();
							try {
								ListItem = new ListItem(Integer
										.toString(park.getParkid()),
										park.getName(),
										Integer.toString(park
												.getOfficial()),
												Integer.toString(park
														.getStreetNumber()),
														park.getStreetName(), park
														.getEwStreet(), park
														.getNsStreet(), park
														.getGoogleMapDest(),
														Double.toString(park
																.getHectare()),
																park.getNeighbourhoodName(),
																park.getNeighbourhoodURL(),
																park.getAdvisories(), park
																.getFacilities(), park
																.getSpecialFeatures());
								int parkId = park.getParkid();
								parkids.add(parkId);
								try {
								} catch(Exception e) {
									e.printStackTrace();
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							li = ListItem.getLi();
							DOM.getElementById("search_result_list")
							.appendChild(li);

						}
						Iterator<Integer> iterator = parkids.iterator();
						while(iterator.hasNext()) {
							int parkid = iterator.next();
							Button button = Button.wrap(DOM.getElementById(Integer.toString(parkid)));
							ListItemHandler lih = new ListItemHandler(Integer.toString(parkid));
							button.addClickHandler(lih);
						}
						
					}
				});
			}
		}
		
		
		
		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		searchButton.addClickHandler(handler);
		searchQuery.addKeyUpHandler(handler);
		
		LoginHandler lh = new LoginHandler();
		loginButton.addClickHandler(lh);
		passwordBox.addKeyUpHandler(lh);
		
		RegisterHandler rh = new RegisterHandler();
		registerButton.addClickHandler(rh);
		registerLname.addKeyUpHandler(rh);
		

	}
}
