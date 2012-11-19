package cs310.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ParkTableGrabber extends RemoteServiceServlet  {

	public String html = "";

	public String grab() {
		String string = "";
		try {
			URL url = new URL("http://evanlouie.com/cs310/index.php/park");
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

}
