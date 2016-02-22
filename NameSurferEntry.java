/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;

import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	public NameSurferEntry(String line) {
		StringTokenizer st = new StringTokenizer(line);
		name = st.nextToken();
		for(int i = 0; i < NDECADES; i++) {
			int number = Integer.parseInt(st.nextToken());
			yearData.add(number);
		}
	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		return name;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		switch (decade) {
			case 1900: return yearData.get(0); 
			case 1910: return yearData.get(1);
			case 1920: return yearData.get(2);
			case 1930: return yearData.get(3);
			case 1940: return yearData.get(4);
			case 1950: return yearData.get(5);
			case 1960: return yearData.get(6);
			case 1970: return yearData.get(7);
			case 1980: return yearData.get(8);
			case 1990: return yearData.get(9);
			case 2000: return yearData.get(10);
			default: throw new ErrorException("");
		}
	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		String nameInfo = name + ": [ ";
		for (int i = 0; i < NDECADES; i++) { 
			nameInfo += yearData.get(i) + " "; 
		}
		nameInfo += "]";
		return nameInfo;
	}
	
	private String name;
	private ArrayList<Integer> yearData = new ArrayList<Integer>();
	
}

