/*
 * File: ProgramHierarchy.java
 * Name: giorgi gvelesiani	
 * Section Leader: davit akopovi
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;

import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {
	// height of box in pixels
	private static final int BOX_HEIGHT = 50;
	// width of box in pixels
	private static final int BOX_WIDTH = 120;
	// distance between levels of hierarchy (not between centers of hierarchy
	// levels)
	private static final int MID_LINE =50;
	// distance between neighbor objects on same level of hierarchy
	private static final int SPACE_BETWEEN_BOXES = 30;

	public void run() {
		// draw rectangles
		rect(BOX_HEIGHT, BOX_WIDTH, MID_LINE, SPACE_BETWEEN_BOXES);
		// draw lines
		line(BOX_HEIGHT, BOX_WIDTH, MID_LINE, SPACE_BETWEEN_BOXES);
		// draw labels
		label(BOX_HEIGHT, BOX_WIDTH, MID_LINE, SPACE_BETWEEN_BOXES);
	}

	// this method can draw rectangles
	private void rect(int h, int w, int l, int s) {
		GRect program = new GRect(getWidth() / 2 - w / 2, getHeight() / 2 - l
				/ 2 - h, w, h);
		add(program);
		GRect cProgram = new GRect(getWidth() / 2 - w / 2, getHeight() / 2 + l
				/ 2, w, h);
		add(cProgram);
		GRect gProgram = new GRect(getWidth() / 2 - w * 3 / 2 - s, getHeight()
				/ 2 + l / 2, w, h);
		add(gProgram);
		GRect dProgram = new GRect(getWidth() / 2 + w / 2 + s, getHeight() / 2
				+ l / 2, w, h);
		add(dProgram);
	}

	// this method can draw lines
	private void line(int h, int w, int l, int s) {
		GLine rightLine = new GLine(getWidth() / 2, getHeight() / 2 - l / 2,
				getWidth() / 2 + w + s, getHeight() / 2 + l / 2);
		add(rightLine);
		GLine midLine = new GLine(getWidth() / 2, getHeight() / 2 - l / 2,
				getWidth() / 2, getHeight() / 2 + l / 2);
		add(midLine);
		GLine leftLine = new GLine(getWidth() / 2, getHeight() / 2 - l / 2,
				getWidth() / 2 - w - s, getHeight() / 2 + l / 2);
		add(leftLine);
	}

	// this method can draw labels
	private void label(int h, int w, int l, int s) {
		GLabel programLabel = new GLabel("Program");
		programLabel.setLocation(getWidth() / 2 - programLabel.getWidth() / 2,
				getHeight() / 2 - l / 2 - h / 2 + programLabel.getAscent() / 2);
		add(programLabel);
		GLabel cProgramLabel = new GLabel("Console Program");
		cProgramLabel.setLocation(
				getWidth() / 2 - cProgramLabel.getWidth() / 2, getHeight() / 2
						+ l / 2 + h / 2 + cProgramLabel.getAscent() / 2);
		add(cProgramLabel);
		GLabel gProgramLabel = new GLabel("Graphics Program");
		gProgramLabel
				.setLocation(
						getWidth() / 2 - gProgramLabel.getWidth() / 2 - s - w,
						getHeight() / 2 + l / 2 + h / 2
								+ gProgramLabel.getAscent() / 2);
		add(gProgramLabel);
		GLabel dProgramLabel = new GLabel("Dialog Program");
		dProgramLabel
				.setLocation(
						getWidth() / 2 - dProgramLabel.getWidth() / 2 + s + w,
						getHeight() / 2 + l / 2 + h / 2
								+ dProgramLabel.getAscent() / 2);
		add(dProgramLabel);
	}
}
