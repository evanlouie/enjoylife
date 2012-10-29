package cs310.server;

import java.io.StringWriter;
import java.util.*;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class Park extends Model {

	private int parkid;
	private String name;
	private int official;
	private int streetNumber;
	private String streetName;
	private String ewStreet;
	private String nsStreet;
	private String googleMapDest;
	private double hectare;
	private String neighbourhoodName;
	private String neighbourhoodURL;
	private String advisories;
	private String facilities;
	private String specialFeatures;
	private String washrooms;

	public Park() {

	}

	public Park creatModelFromXMLElement(Node node) {
		Element element = (Element) node;
		Park model = new Park();
		model.setParkid(Integer.parseInt(element.getElementsByTagName("ParkID")
				.item(0).getTextContent()));
		model.setName(element.getElementsByTagName("Name").item(0)
				.getTextContent());
		model.setOfficial(Integer.parseInt(element
				.getElementsByTagName("Official").item(0).getTextContent()));
		if (element.getElementsByTagName("StreetNumber").item(0)
				.getTextContent() != "") {
			model.setStreetNumber(Integer.parseInt(element
					.getElementsByTagName("StreetNumber").item(0)
					.getTextContent()));
		} else {
			model.setStreetNumber(0);
		}
		model.setStreetName(element.getElementsByTagName("StreetName").item(0)
				.getTextContent());
		model.setEwStreet(element.getElementsByTagName("EWStreet").item(0)
				.getTextContent());
		model.setNsStreet(element.getElementsByTagName("NSStreet").item(0)
				.getTextContent());
		model.setGoogleMapDest(element.getElementsByTagName("GoogleMapDest")
				.item(0).getTextContent());
		model.setHectare(Double.parseDouble(element
				.getElementsByTagName("Hectare").item(0).getTextContent()));
		model.setNeighbourhoodName(element
				.getElementsByTagName("NeighbourhoodName").item(0)
				.getTextContent());
		model.setNeighbourhoodURL(element
				.getElementsByTagName("NeighbourhoodURL").item(0)
				.getTextContent());
		model.setAdvisories(element.getElementsByTagName("Advisories").item(0)
				.getTextContent());
		model.setFacilities(element.getElementsByTagName("Facilities").item(0)
				.getTextContent());
		model.setSpecialFeatures(element
				.getElementsByTagName("SpecialFeatures").item(0)
				.getTextContent());
		model.setWashrooms(element.getElementsByTagName("Washrooms").item(0)
				.getTextContent());
		return model;
	}

	public ArrayList<Park> getParks(String urlString) {

		ArrayList<Park> parks = new ArrayList<Park>();

		XmlUtil util = new XmlUtil();
		util.loadXML(urlString);
		Document doc = util.getDoc();
		NodeList nl = doc.getElementsByTagName("item");
		for (int i = 0; i < nl.getLength(); i++) {
			Node node = nl.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Park model = this.creatModelFromXMLElement(node);
				parks.add(model);
			}
		}
		return parks;
	}
	
	public String getParksAsXMLString(String urlString) {
		String XMLString = "";
		XmlUtil util = new XmlUtil();
		util.loadXML(urlString);
		Document doc = util.getDoc();
		TransformerFactory tf = TransformerFactory.newInstance();
		try{
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			XMLString = writer.getBuffer().toString().replaceAll("\n|\r", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return XMLString;
	}

	public static void main(String[] argsv) {
		Park park = new Park();
		try {
			ArrayList<Park> parks = new ArrayList<Park>();
			parks = park.getParks("http://www.evanlouie.com/cs310/index.php/api/park");
			Iterator<Park> it = parks.iterator();
			while(it.hasNext()) {
				System.out.println(it.next().getGoogleMapDest());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getParkid() {
		return parkid;
	}

	public void setParkid(int parkid) {
		this.parkid = parkid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOfficial() {
		return official;
	}

	public void setOfficial(int official) {
		this.official = official;
	}

	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getEwStreet() {
		return ewStreet;
	}

	public void setEwStreet(String ewStreet) {
		this.ewStreet = ewStreet;
	}

	public String getNsStreet() {
		return nsStreet;
	}

	public void setNsStreet(String nsStreet) {
		this.nsStreet = nsStreet;
	}

	public String getGoogleMapDest() {
		return googleMapDest;
	}

	public void setGoogleMapDest(String googleMapDest) {
		this.googleMapDest = googleMapDest;
	}

	public double getHectare() {
		return hectare;
	}

	public void setHectare(double hectare) {
		this.hectare = hectare;
	}

	public String getNeighbourhoodName() {
		return neighbourhoodName;
	}

	public void setNeighbourhoodName(String neighbourhoodName) {
		this.neighbourhoodName = neighbourhoodName;
	}

	public String getNeighbourhoodURL() {
		return neighbourhoodURL;
	}

	public void setNeighbourhoodURL(String neighbourhoodURL) {
		this.neighbourhoodURL = neighbourhoodURL;
	}

	public String getAdvisories() {
		return advisories;
	}

	public void setAdvisories(String advisories) {
		this.advisories = advisories;
	}

	public String getFacilities() {
		return facilities;
	}

	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public String getWashrooms() {
		return washrooms;
	}

	public void setWashrooms(String washrooms) {
		this.washrooms = washrooms;
	}

}
