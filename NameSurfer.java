/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the top of the window.
 */
	public void init() {
	    addNameController();
	    addActionListeners();
	    graph = new NameSurferGraph();
	    add(graph);
	    dbase = new NameSurferDataBase(NAMES_DATA_FILE);
	}
	
	/*
	 * This creates the interactors at the top of the window
	 */
	private void addNameController() {
		name = new JTextField(MAX_NAME_LENGTH);
		name.addActionListener(this);
		graphButton = new JButton("Graph");
		clearButton = new JButton("Clear");
		add(new JLabel("Name"), NORTH);
		add(name, NORTH);
		add(graphButton, NORTH);
		add(clearButton, NORTH);
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == graphButton || source == name) {
			if(dbase.findEntry(name.getText()) != null) {
				graph.addEntry(dbase.findEntry(name.getText()));
			}
			graph.update();
		} else if(source == clearButton) {
			graph.clear();
			graph.update();
		}
	}
	
	private JTextField name;
	private JButton graphButton, clearButton;
	private int MAX_NAME_LENGTH = 20;
	NameSurferGraph graph;
	NameSurferDataBase dbase;
	
}
