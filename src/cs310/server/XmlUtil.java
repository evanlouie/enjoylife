package cs310.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class XmlUtil {

	private Document doc;
	private String xmlString;
	public void loadXML(String urlString) {
		try {
			URL url = new URL(urlString);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String inputLine;
			xmlString = in.readLine();
			while ((inputLine = in.readLine()) != null) {
				xmlString = xmlString + inputLine;
			}
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();

			DocumentBuilder builder;
			
			System.out.println(xmlString);
			
			try {
				builder = factory.newDocumentBuilder();
				doc = builder
						.parse(new InputSource(new StringReader(xmlString)));

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args) {
//		XmlUtil util = new XmlUtil();
//		util.loadXML("http://localhost/evanlouie/cs310/index.php/api/park/");
//	}

	public void saveLocally(Document doc, String dir) {
		try {
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(dir));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public Document getDoc() {
		return doc;
	}
	public String getxmlSring() {
		return xmlString;
	}

}
