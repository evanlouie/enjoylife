var map;
var kmlLayerOptions;
var parkKml;
var communitycenterKml;
var bikewayKml;
function initialize() {
	var mapOptions = {
		center : new google.maps.LatLng(49.2505, -123.1119),
		zoom : 13,
		mapTypeId : google.maps.MapTypeId.ROADMAP,
	};

	map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
	kmlLayerOptions = {
		map : map,
		preserveViewport : true,
		suppressInfoWindows : true
	}
	bikewayKml = new google.maps.KmlLayer("http://data.vancouver.ca/download/kml/bikeways.kmz", kmlLayerOptions);
	communitycenterKml = new google.maps.KmlLayer("http://data.vancouver.ca/download/kml/community_centres.kmz", kmlLayerOptions);
	parkKml = new google.maps.KmlLayer("http://data.vancouver.ca/download/kml/park_polygons.kmz", kmlLayerOptions);

}


function showParks() {
	parkKml.setMap(map);
}
function hideParks() {
	parkKml.setMap(null);
}
function showCommunityCenters() {
	communitycenterKml.setMap(map);
}
function hideCommunityCenters() {
	communitycenterKml.setMap(null);
}
function showBikeWays() {
	bikewayKml.setMap(map);	
}
function hideBikeWays() {
	bikewayKml.setMap(null);
}
function addMarker(LatLng) {
	var marker = new google.maps.Marker({
		position: new google.maps.LatLng(49.249783,-123.155250),
		map: map,
		title: "test"
	});
}







var infoWindow = new google.maps.InfoWindow();
var markerBounds = new google.maps.LatLngBounds();
var markerArray = [];

function makeMarker(options){
	var pushPin = new google.maps.Marker({map:map});
	pushPin.setOptions(options);
	google.maps.event.addListener(pushPin, 'click', function(){
		infoWindow.setOptions(options);
		infoWindow.open(map, pushPin);
	});
	markerArray.push(pushPin);
	return pushPin;
}
function openMarker(i){
   google.maps.event.trigger(markerArray[i],'click');
 };
$(document).on('click', 'a.ui-link-inherit', function() {
	var ll = $(this).attr('googlemapdest').split(',');
	makeMarker({
		position: new google.maps.LatLng(ll[0], ll[1]),
		title: $(this).attr('name'),
		content: 	
			'<iframe marginheight="0" marginwidth="0" name="wxButtonFrame" id="wxButtonFrame" height="90" src="http://btn.weather.ca/weatherbuttons/template5.php?placeCode=CABC0308&category0=Cities&containerWidth=150&btnNo=&backgroundColor=blue&multipleCity=0&citySearch=0&celsiusF=C" align="top" frameborder="0" width="150" scrolling="no" allowTransparency="true"></iframe>'+
			'<div><h1>'+$(this).attr('name')+
					'</h1><h2>Information</h2><table>'+
					'<tr><td>Address:</td><td>'+$(this).attr('streetnumber')+' '+$(this).attr('streetname')+'</td></tr>'+
					'<tr><td>Intersection:</td><td>'+$(this).attr('ewstreet')+' and '+$(this).attr('nsstreet')+'</td></tr>'+
					'<tr><td>XY-Coord:</td><td>'+$(this).attr('googlemapdest')+'</td></tr>'+
					'<tr><td>Size:</td><td>'+$(this).attr('hectare')+' Hectares</td></tr>'+
					'<tr><td>Neighborhood:</td><td><a href='+$(this).attr('neighbourhoodurl')+'>'+$(this).attr('neighbourhoodname')+'</a></td></tr>'+
					'<tr><td>Advisories:</td><td>'+$(this).attr('advisories')+'</td></tr>'+
					'<tr><td>Facilities:</td><td>'+$(this).attr('facilities')+'</td></tr>'+
					'<tr><td>Special Features:</td><td>'+$(this).attr('specialfeatures')+'</td></tr>'+
					'<tr><td>Learn More:</td><td><a href="'+window.location.pathname+'/park/'+
					'</table></div>',
		animation: google.maps.Animation.DROP
	})					
	openMarker(markerArray.length-1);

})

$(document).ready(function() {
	$("#map_options").on("change", function() {
		id = ($("#map_options option:selected").attr('value'));
		if (id == "all") {
			initialize();
		}
		if (id == "pks") {
			showParks();
		}
		if (id == "ccs") {
			showCommunityCenters();
		}
		if (id == "bws") {
			showBikeWays();
		}
	})

	$('input[type=checkbox]').on("change", function() {
		var id = $(this).attr('id');
		var checked = $(this).attr('checked');
		if (id == "bikeways-checkbox") {
			if (checked == "checked") {
				showBikeWays();
			} else {
				hideBikeWays();
			}
		} else if (id == "parks-checkbox") {
			if (checked == "checked") {
				showParks();
			} else {
				hideParks();
			}
		} else if (id == "cc-checkbox") {
			if (checked == "checked") {
				showCommunityCenters();
			} else {
				hideCommunityCenters();
			}
		}

		

	})

})


