package cs310.client;

import java.security.DomainCombiner;
import java.util.ArrayList;
import java.util.Iterator;

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
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.xml.client.XMLParser;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Helloworld implements EntryPoint {
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

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// HelloWorldPage page = new HelloWorldPage();
		// JQMContext.changePage(page);

		final Button searchButton = Button.wrap(DOM
				.getElementById("search-button"));
		final TextBox nameField = new TextBox();
		final TextBox searchQuery = TextBox.wrap(DOM
				.getElementById("search-query"));
		nameField.setText("GWT User");
		final Label errorLabel = new Label();

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
				greetingService.greetServer(textToServer,
						new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						dialogBox
						.setText("Remote Procedure Call - Failure");
						serverResponseLabel
						.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.center();
						closeButton.setFocus(true);
					}

					public void onSuccess(String xmlString) {
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
							} catch (Exception e) {
								e.printStackTrace();
							}
							li = ListItem.getLi();
							DOM.getElementById("search_result_list")
							.appendChild(li);
						}
					}
				});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		searchButton.addClickHandler(handler);
		searchQuery.addKeyUpHandler(handler);
	}
}
