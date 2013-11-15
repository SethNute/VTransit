package com.cs2114.vttransit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.cs2114.vttransit.TBXML.TBXMLElement;
import com.cs2114.vttransit.TBXML.TBXMLException;

/**
 * A class for storing information about a specific route, identified by its
 * fullName and shortName
 * 
 * @author Seth Nute (seth12)
 * @author Ben Kodres (bkobrien)
 * @author Joe Fletcher (joevt)
 * @version 11/8/13
 */
public class Parser {

	/**
	 * Creates a parser object.
	 */
	public Parser() {
		super();
	}

	/**
	 * Gets the times for a stop on a route
	 * 
	 * @param route
	 *            the route to get times for
	 * @param stop
	 *            the stop to get times for
	 * @return a list of times from the xml file
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public List<String> getTimesForRouteAndStop(Route route, Stop stop)
			throws TBXMLException, IOException {
		List<String> arrivalTimes = new ArrayList<String>();
		String xmlURL = "http://www.bt4u.org/webservices/BT4U_WebService.asmx/GetNextDepartures?routeShortName="
				+ route.getShortName() + "&stopCode=" + stop.getStopCode();
		String xmlString = fileToString(xmlURL);
		TBXML tbxml = new TBXML(xmlString);
		TBXMLElement root = tbxml.rootXMLElement();
		if (root != null) {
			TBXMLElement nextDepartures = tbxml.childElement("NextDepartures",
					root);
			while (nextDepartures != null) {
				TBXMLElement time = tbxml.childElement("AdjustedDepartureTime",
						nextDepartures);
				arrivalTimes.add(tbxml.textForElement(time));
				nextDepartures = tbxml.nextSibling("NextDepartures",
						nextDepartures);
			}
		}
		return arrivalTimes;
	}

	/**
	 * Gets a list of stops on a route
	 * 
	 * @param route
	 *            a route to get stops for
	 * @return a list of Stops from the xml file
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public List<Stop> getStopsOnRoute(Route route) throws TBXMLException,
			IOException {
		List<Stop> stopsOnRoute = new ArrayList<Stop>();
		String xmlURL = "http://www.bt4u.org/webservices/BT4U_WebService.asmx/GetScheduledStopNames?routeShortName="
				+ route.getShortName();
		String xmlString = fileToString(xmlURL);
		TBXML tbxml = new TBXML(xmlString);
		TBXMLElement root = tbxml.rootXMLElement();
		if (root != null) {
			TBXMLElement scheduledStop = tbxml.childElement("ScheduledStops",
					root);
			while (scheduledStop != null) {
				TBXMLElement stopName = tbxml.childElement("StopName",
						scheduledStop);
				TBXMLElement stopCode = tbxml.childElement("StopCode",
						scheduledStop);
				stopsOnRoute.add(new Stop(tbxml.textForElement(stopName), tbxml
						.textForElement(stopCode)));
				scheduledStop = tbxml.nextSibling("ScheduledStops",
						scheduledStop);
			}
		}
		return stopsOnRoute;
	}

	/**
	 * Private helper method that takes in a url string and converts the file
	 * downloaded by it into a string.
	 * 
	 * @param url
	 * @return a string of the xml file
	 */
	private String fileToString(String url) {
		URL oURL;
		URLConnection oConnection;
		BufferedReader oReader;
		String sLine;
		StringBuilder sbResponse;
		String sResponse = null;
		try {
			oURL = new URL(url);
			oConnection = oURL.openConnection();
			oReader = new BufferedReader(new InputStreamReader(
					oConnection.getInputStream()));
			sbResponse = new StringBuilder();

			while ((sLine = oReader.readLine()) != null) {
				sbResponse.append(sLine);
			}
			sResponse = sbResponse.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sResponse;
	}

	/**
	 * Parses the time string to make it more readable.
	 * 
	 * @param times
	 *            The list of times
	 * @return a list of the times (more readable)
	 */
	public List<String> makeTimesMoreReadable(List<String> times) {
		List<String> result = new ArrayList<String>();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < times.size(); i++) {
			String time = times.get(i);
			builder = new StringBuilder();
			for (int j = 0; j < time.length(); j++) {
				if (time.charAt(j) == ' ') {
					builder.append(time.substring(j + 1, time.length()));
					String toAdd = builder.toString();
					result.add(toAdd);
					break;
				}
			}
		}
		result = deleteSeconds(result);
		return result;
	}

	
	/**
	 * Helper method to get rid of the seconds in the times.
	 * @param times
	 * @return times without seconds
	 */
	private List<String> deleteSeconds(List<String> times) {
		List<String> result = new ArrayList<String>();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < times.size(); i++) {
			String time = times.get(i);
			builder = new StringBuilder();
			builder.append(time);
			for (int j = 0; j < time.length(); j++) {
				if (time.charAt(j) == ':') {
					builder.delete(j + 3, j + 6);
					String toAdd = builder.toString();
					result.add(toAdd);
					break;
				}
			}
		}
		return result;
	}

}
