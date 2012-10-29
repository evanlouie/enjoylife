package cs310.client;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.xml.client.XMLParser;

public class Park {
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

	public Park creatModelFromXMLElement(com.google.gwt.xml.client.Node node) {
		com.google.gwt.xml.client.Element element = null;
		try {
			element = (com.google.gwt.xml.client.Element) node;
		} catch (Exception e) {
			e.printStackTrace();
		}

		Park park = new Park();
		try {
			park.setParkid(Integer.parseInt(element
					.getElementsByTagName("ParkID").item(0).getFirstChild()
					.toString()));
			park.setName(element.getElementsByTagName("Name").item(0)
					.getFirstChild().toString());
			park.setOfficial(Integer.parseInt(element
					.getElementsByTagName("Official").item(0).getFirstChild()
					.toString()));

			if (element.getElementsByTagName("StreetNumber").item(0)
					.getFirstChild() != null) {
				park.setStreetNumber(Integer.parseInt(element
						.getElementsByTagName("StreetNumber").item(0)
						.getFirstChild().toString()));
			} else {
				park.setStreetNumber(0);
			}

			park.setStreetName(element.getElementsByTagName("StreetName")
					.item(0).getFirstChild().toString());
			if (element.getElementsByTagName("EWStreet").item(0)
					.getFirstChild() != null) {
				park.setEwStreet(element.getElementsByTagName("EWStreet")
						.item(0).getFirstChild().toString());
			} else {
				park.setEwStreet("");
			}
			if (element.getElementsByTagName("NSStreet").item(0)
					.getFirstChild() != null) {
				park.setNsStreet(element.getElementsByTagName("NSStreet")
						.item(0).getFirstChild().toString());
			} else {
				park.setNsStreet("");
			}

			park.setGoogleMapDest(element.getElementsByTagName("GoogleMapDest")
					.item(0).getFirstChild().toString());
			park.setHectare(Double.parseDouble(element
					.getElementsByTagName("Hectare").item(0).getFirstChild()
					.toString()));
			park.setNeighbourhoodName(element
					.getElementsByTagName("NeighbourhoodName").item(0)
					.getFirstChild().toString());
			park.setNeighbourhoodURL(element
					.getElementsByTagName("NeighbourhoodURL").item(0)
					.getFirstChild().toString());
			park.setAdvisories(element.getElementsByTagName("Advisories")
					.item(0).getFirstChild().toString());
			park.setFacilities(element.getElementsByTagName("Facilities")
					.item(0).getFirstChild().toString());
			park.setSpecialFeatures(element
					.getElementsByTagName("SpecialFeatures").item(0)
					.getFirstChild().toString());
			park.setWashrooms(element.getElementsByTagName("Washrooms").item(0)
					.getFirstChild().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return park;
	}

	public ArrayList<Park> getParks(String xmlString) {

		ArrayList<Park> parks = new ArrayList<Park>();

		com.google.gwt.xml.client.Document doc = XMLParser.parse(xmlString);
		com.google.gwt.xml.client.Element element = doc.getDocumentElement();
		com.google.gwt.xml.client.NodeList nl = element
				.getElementsByTagName("item");
		for (int i = 0; i < nl.getLength(); i++) {
			com.google.gwt.xml.client.Node node = nl.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {

				Park model = this.creatModelFromXMLElement(node);
				parks.add(model);
			}
		}
		return parks;
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
