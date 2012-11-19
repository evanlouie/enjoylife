package cs310.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;
import java.util.Map.Entry;

public class Model {

	public Model() {

	}

	private String encodePost(HashMap<String, String> data) {
		String post = "";
		Iterator<Entry<String, String>> it = data.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> pairs = (Map.Entry<String, String>) it
					.next();
			try {
				post += URLEncoder.encode(pairs.getKey(), "UTF-8") + "="
						+ URLEncoder.encode(pairs.getValue(), "UTF-8");
				if (it.hasNext()) {
					post += "&";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return post;
	}

	public boolean post(HashMap<String, String> data) {
		String request = this.encodePost(data);
		try {
			// Send Data
			URL url = new URL(
					"http://evanlouie.com/cs310/index.php/api/park");
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(
					conn.getOutputStream());
			wr.write(request);
			wr.flush();

			// Get Response
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				System.out.println(line);
			}
			wr.close();
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public static void main(String[] args) {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("hello", "world");
		hm.put("Evan", "Louie");
		Model model = new Model();
		model.post(hm);
		System.out.println(model.encodePost(hm));
	}
}
