/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		drawGraph();
	}

	/*
	 * Draws the blank graph to which lines will be added
	 */
	private void drawGraph() {
		double x = getWidth() / NDECADES;
		double yBottom = (getHeight() - GRAPH_MARGIN_SIZE);
		for(int i = 1; i <= NDECADES; i++) {
			GLine graphLine = new GLine(x * i, 0, x * i, yBottom);
			add(graphLine);
		}
		GLine topLine = new GLine(0, GRAPH_MARGIN_SIZE, x * NDECADES, GRAPH_MARGIN_SIZE);
		add(topLine);
		GLine bottomLine = new GLine(0, yBottom, x * NDECADES, yBottom);
		add(bottomLine);
		for(int i = 0; i < NDECADES - 1; i++) {
			GLabel year = new GLabel(" 19" + i + "0", x * i, getHeight()); 
			add(year);
		}
		GLabel millenium = new GLabel(" 2000", x * (NDECADES - 1), getHeight());
		add(millenium);
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		graphLines.clear();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		if(graphLines.indexOf(entry) == -1) {
			graphLines.add(entry);
		}
		GCompound graph = new GCompound();
		for(int i = 0; i < NDECADES - 1; i++) {
			int year = 1900 + (i * 10);
			int nextYear = 1900 + ((i + 1) * 10);
			double xYear = (getWidth() / NDECADES) * i;
			double xNextYear = (getWidth() / NDECADES) * (i + 1);
			double yYear = entry.getRank(year) * (getHeight() - (2 * GRAPH_MARGIN_SIZE))/MAX_RANK + GRAPH_MARGIN_SIZE;
			double yNextYear = entry.getRank(nextYear) * (getHeight() - (2 * GRAPH_MARGIN_SIZE))/MAX_RANK + GRAPH_MARGIN_SIZE;
			double yRankZero = getHeight() - GRAPH_MARGIN_SIZE;
			if(entry.getRank(year) == 0) {
				GLine lineZero = new GLine(xYear, yRankZero, xNextYear, yRankZero);
				if(entry.getRank(nextYear) != 0) {
					lineZero.setEndPoint(xNextYear, yNextYear);
				}
				graph.add(lineZero);
				GLabel nameZero = new GLabel(entry.getName() + "*", xYear + 3, yRankZero);
				graph.add(nameZero);
			} else {
				GLine rankLine = new GLine(xYear, yYear, xNextYear, yRankZero);
				if(entry.getRank(nextYear) != 0) {
					rankLine.setEndPoint(xNextYear, yNextYear);
				}
				graph.add(rankLine);                     
				GLabel nameRank = new GLabel(entry.getName() + " " + entry.getRank(year));
				if(entry.getRank(year) >= entry.getRank(nextYear)) {
					nameRank.setLocation(xYear + 3, yYear + 10);
				} else {
					nameRank.setLocation(xYear + 3, yYear - nameRank.getAscent());
				}
				graph.add(nameRank);
			}
		} 
		add(graph);
		if(graphLines.indexOf(entry)%4 == 1) {
			graph.setColor(Color.RED);
		} else if(graphLines.indexOf(entry)%4 == 2) {
			graph.setColor(Color.BLUE);
		} else if(graphLines.indexOf(entry)%4 == 3) {
			graph.setColor(Color.MAGENTA);
		}
	}
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
		drawGraph();
		for(int i = 0; i < graphLines.size(); i++) {
			addEntry(graphLines.get(i));
		}
	}
	
	private ArrayList<NameSurferEntry> graphLines = new ArrayList<NameSurferEntry>();
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
