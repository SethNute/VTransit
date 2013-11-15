package com.cs2114.vttransit;

import java.util.List;

/**
 * A class that handles information about a particular bus stop, as identified
 * by name and code
 * 
 * @author Seth Nute (seth12)
 * @author Ben Kodres (bkobrien)
 * @author Joe Fletcher (joevt)
 * @version 11/8/2013
 */
public class Stop {
	private String stopName;
	private String stopCode;
	private List<String> times;

	/**
	 * A constructor that takes two params, the stopName and the stopCode
	 * 
	 * @param stopN
	 *            -> a string for the stopName
	 * @param stopC
	 *            -> an int for the stopCode
	 */
	public Stop(String stopN, String stopC) {
		stopName = stopN;
		stopCode = stopC;
		times = null; // TODO, initialize this
	}

	/**
	 * A getter for the stopName
	 * 
	 * @return String -> the stopName
	 */ 
	public String getStopName() {
		return stopName;
	}

	/**
	 * A getter for the stopCode
	 * 
	 * @return int -> the stopCode
	 */
	public String getStopCode() {
		return stopCode;
	}

	/**
	 * A getter for the specificRouteTimes
	 * 
	 * @return List<String> -> a list of all the times for the specific stop
	 *         called in the parser code
	 */
	public List<String> getSpecificRouteTimes() {
		return times;
	}

	/**
	 * A setter for the specificRouteTimes
	 * 
	 * @param timeList
	 *            -> a List<String> of all the times on a route
	 */
	public void setTimes(List<String> timeList) {
		times = timeList;
	}
	
}
