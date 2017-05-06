/*
 * File: Target.java
 * Name: giorgi gvelesiani	
 * Section Leader: davit akopovi
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;

import java.awt.*;

public class Target extends GraphicsProgram {
	// radius of oval one in cm
	private static final double OONE_RADIUS = 2.54;
	// radius of oval two in cm
	private static final double OTWO_RADIUS = 1.65;
	// radius of oval three in cm
	private static final double OTHREE_RADIUS = 0.76;
	// pixels in one cm
	private static final double PIXEL_CM = 72 / 2.54;

	public void run() {
		circle(OONE_RADIUS, Color.RED);
		circle(OTWO_RADIUS, Color.WHITE);
		circle(OTHREE_RADIUS, Color.RED);
	}

	/*
	 * this method can draw r radius circle, which is located in center and is
	 * filled with color c coordinates of oval are (vertical middle - radius in
	 * pixels, horizontal middle - radius in pixels, diameter in pixels,
	 * diameter in pixels)
	 */
	private void circle(double r, Color c) {
		GOval oval = new GOval(getWidth() / 2 - r * PIXEL_CM, getHeight() / 2
				- r * PIXEL_CM, 2 * r * PIXEL_CM, 2 * r * PIXEL_CM);
		oval.setFilled(true);
		oval.setColor(c);
		add(oval);
	}
}
