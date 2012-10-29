package cs310.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;

public class ListItem {

	private Element li;

	public ListItem(String ParkID, String Name, String Offical,
			String StreetNumber, String StreetName, String EWStreet,
			String NSStreet, String GoogleMapDest, String Hectare,
			String NeighbourhoodName, String NeighbourhoodURL,
			String Advisories, String Facilities, String SpecialFeatures) {
		li = DOM.createElement("li");
		li.setAttribute("data-corners", "false");
		li.setAttribute("data-shadow", "false");
		li.setAttribute("data-iconshadow", "true");
		li.setAttribute("data-wrapperels", "div");
		li.setAttribute("data-icon", "arrow-r");
		li.setAttribute("data-iconpos", "right");
		li.setAttribute("data-theme", "c");
		li.setAttribute("class",
				"ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-btn-up-c");
		Element div = DOM.createElement("div");
		div.setAttribute("class", "ui-btn-inner ui-li");
		Element div1 = DOM.createElement("div");
		div1.setAttribute("class", "ui-btn-text");
		Element a = DOM.createElement("a");
		a.setAttribute("ParkID", ParkID);
		a.setAttribute("Name", Name);
		a.setAttribute("StreetNumber", StreetNumber);
		a.setAttribute("StreetName", StreetName);
		a.setAttribute("EWStreet", EWStreet);
		a.setAttribute("NSStreet", NSStreet);
		a.setAttribute("GoogleMapDest", GoogleMapDest);
		a.setAttribute("Hectare", Hectare);
		a.setAttribute("NeighbourhoodName", NeighbourhoodName);
		a.setAttribute("NeighbourhoodURL", NeighbourhoodURL);
		a.setAttribute("Advisories", Advisories);
		a.setAttribute("Facilities", Facilities);
		a.setAttribute("SpecialFeatures", SpecialFeatures);
//		a.setAttribute("href", "LINK");
		a.setAttribute("class", "ui-link-inherit");
		a.setInnerText(Name);
		Element span = DOM.createElement("span");
		span.setAttribute("class", "ui-icon ui-icon-arrow-r ui-icon-shadow");

		div1.appendChild(a);
		div.appendChild(div1);
		div.appendChild(span);
		li.appendChild(div);
	}

	public Element getLi() {
		return li;
	}
}
