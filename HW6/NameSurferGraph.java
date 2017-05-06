/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import acm.program.GraphicsProgram;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas implements NameSurferConstants,
		ComponentListener {

	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	public NameSurferGraph() {
		addComponentListener(this);
		// You fill in the rest //
	}

	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {
		names = new ArrayList<NameSurferEntry>();
		update();
	}

	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display. Note
	 * that this method does not actually draw the graph, but simply stores the
	 * entry; the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry) {
		if (!names.contains(entry))
			names.add(entry);
		update();
	}

	/**
	 * Updates the display image by deleting all the graphical objects from the
	 * canvas and then reassembling the display according to the list of
	 * entries. Your application must call update after calling either clear or
	 * addEntry; update is also called whenever the size of the canvas changes.
	 */
	public void update() {
		drawBackground();
		drawDiagram();
	}

	private void drawBackground() {
		removeAll();
		drawVerticals();
		drawHorizontals();
		drawLabelDecs();
	}

	private void drawVerticals() {
		for (int i = 0; i <= NDECADES - 2; i++) {
			GLine line = new GLine(getWidth() / NDECADES * (i + 1), 0,
					getWidth() / NDECADES * (i + 1), getHeight());
			add(line);
		}
	}

	private void drawHorizontals() {
		GLine line = new GLine(0, 20, getWidth(), 20);
		GLine line2 = new GLine(0, getHeight() - 20, getWidth(),
				getHeight() - 20);
		add(line);
		add(line2);

	}

	private void drawLabelDecs() {
		int dec = 1900;
		for (int i = 0; i < NDECADES; i++) {
			GLabel lab = new GLabel("" + dec);
			add(lab, 2 + getWidth() / NDECADES * i, getHeight() - 2);
			dec += 10;
		}
	}

	private void drawDiagram() {

		for (int i = 0; i < names.size(); i++)
			drawGraph(names.get(i), colors[i % colors.length]);

	}

	private void drawGraph(NameSurferEntry ent, Color c) {
		double graphHeight = getHeight() - GRAPH_MARGIN_SIZE * 2;
		for (int i = 0; i < NDECADES; i++) {
			double x0 = 0;
			double x1 = 0;
			double y0 = 0;
			double y1 = 0;
			String name = ent.getName() + " " + ent.getRank(i);
			if (i != NDECADES - 1) {
				x0 = (double) getWidth() / (double) NDECADES * (double) i;
				x1 = (double) getWidth() / (double) NDECADES * (double) (i + 1);
				y0 = GRAPH_MARGIN_SIZE + graphHeight * (double) ent.getRank(i)
						* 0.001;
				y1 = GRAPH_MARGIN_SIZE + graphHeight
						* (double) ent.getRank(i + 1) * 0.001;
				if (ent.getRank(i + 1) == 0)
					y1 = GRAPH_MARGIN_SIZE + graphHeight;
			} else {
				x0 = (double) getWidth() / (double) NDECADES * (double) i;
				x1 = x0;
				if (ent.getRank(i) == 0)
					y0 = GRAPH_MARGIN_SIZE + graphHeight;
				else
					y0 = GRAPH_MARGIN_SIZE + graphHeight
							* (double) ent.getRank(i) * 0.001;
				y1 = y0;
			}
			if (ent.getRank(i) == 0) {
				name = ent.getName() + " *";
				y0 = GRAPH_MARGIN_SIZE + graphHeight;
			}
			GLine graphLine = new GLine(x0, y0, x1, y1);
			add(graphLine);
			GLabel nameLab = new GLabel((name), graphLine.getX(),
					graphLine.getY());
			graphLine.setColor(c);
			nameLab.setColor(c);
			add(nameLab);
		}
	}

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) {
	}

	public void componentMoved(ComponentEvent e) {
	}

	public void componentResized(ComponentEvent e) {
		update();
	}

	public void componentShown(ComponentEvent e) {
	}

	private Color[] colors = { Color.BLACK, Color.red, Color.blue, Color.yellow };
	private ArrayList<NameSurferEntry> names = new ArrayList<NameSurferEntry>();
}
