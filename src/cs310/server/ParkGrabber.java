package cs310.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class ParkGrabber {

	int parkid;

	public ParkGrabber(int parkid) {
		this.parkid = parkid;
	}

	public void grab() {
		String string = "";
		try {
			URL url = new URL("http://localhost/test.php");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String inputLine;
			string = in.readLine();
			while ((inputLine = in.readLine()) != null) {
				string = string + inputLine;
			}
			System.out.println(string);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ParkGrabber grabber = new ParkGrabber(1);
		grabber.grab();
	}
}
