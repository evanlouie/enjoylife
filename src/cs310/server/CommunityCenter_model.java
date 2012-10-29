package cs310.server;

import cs310.server.XmlUtil;

import java.util.*;

import org.w3c.dom.*;

public class CommunityCenter_model {

	private String centerName;
	private double latitude;
	private double longitude;
	private String address;
	private String urlLink;

	public CommunityCenter_model() {

	}

	public CommunityCenter_model creatModelFromXMLElement(Node node) {
		Element element = (Element) node;
		CommunityCenter_model model = new CommunityCenter_model();
		model.setCenterName(element.getElementsByTagName("CENTRE_NAME").item(0)
				.getTextContent());
		model.setLatitude(Double.parseDouble(element
				.getElementsByTagName("LATITUDE").item(0).getTextContent()));
		model.setLongitude(Double.parseDouble(element
				.getElementsByTagName("LONGITUDE").item(0).getTextContent()));
		model.setAddress(element.getElementsByTagName("ADDRESS").item(0)
				.getTextContent());
		model.setUrlLink(element.getElementsByTagName("URLLINK").item(0)
				.getTextContent());
		return model;
	}
	
	public ArrayList<CommunityCenter_model> getAllCommunityCenters(String urlString) {
		ArrayList<CommunityCenter_model> ccs = new ArrayList<CommunityCenter_model>();

		XmlUtil util = new XmlUtil();
		util.loadXML(urlString);
		Document doc = util.getDoc();
		NodeList nl = doc.getElementsByTagName("item");
		for (int i = 0; i < nl.getLength(); i++) {
			Node node = nl.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				CommunityCenter_model model = this.creatModelFromXMLElement(node);
				ccs.add(model);
			}
		}
		return ccs;
	}
	public static void main(String[] argsv) {
		CommunityCenter_model park = new CommunityCenter_model();
		try {
			ArrayList<CommunityCenter_model> parks = new ArrayList<CommunityCenter_model>();
			parks = park.getAllCommunityCenters("http://www.evanlouie.com/cs310/index.php/api/community_center/community_centers/fomat/xml");
			Iterator<CommunityCenter_model> it = parks.iterator();
			while(it.hasNext()) {
				System.out.println(it.next().getCenterName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUrlLink() {
		return urlLink;
	}

	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}

}
