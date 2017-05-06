/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.io.IOConsole;
import acm.program.*;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base and
	 * initializing the interactors at the bottom of the window.
	 */
	public void init() {

		add(new JLabel("Name"), SOUTH);

		nameText = new JTextField(18);
		add(nameText, SOUTH);

		graphBut = new JButton("Graph");
		add(graphBut, SOUTH);

		clearBut = new JButton("Clear");
		add(clearBut, SOUTH);

		graph = new NameSurferGraph();
		graph.setBackground(Color.LIGHT_GRAY);
		add(graph);

		addActionListeners();
	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are clicked, so
	 * you will have to define a method to respond to button actions.
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == graphBut) {
			if (nameText.getText().length() > 0
					&& base.findEntry(nameText.getText().toLowerCase()) != null)

				graph.addEntry(base.findEntry(nameText.getText().toLowerCase()));
			nameText.setText("");
		}
		if (e.getSource() == clearBut) {
			graph.clear();
		}
	}

	private JTextField nameText;
	private JButton graphBut;
	private JButton clearBut;
	private NameSurferDataBase base = new NameSurferDataBase(NAMES_DATA_FILE);
	private NameSurferGraph graph;

}
