/*
 * File: Pyramid.java
 * Name: giorgi gvelesiani	
 * Section Leader: davit akopovi 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;

import java.awt.*;

public class Pyramid extends GraphicsProgram {

	/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

	/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

	/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;

	public void run() {
		// y is number of bricks in base
		double y = BRICKS_IN_BASE;
		for (double j = 1; j <= BRICKS_IN_BASE; j++) {
			for (double i = 1; i <= y; i++) {
				/*
				 * getWidth() / 2 - BRICKS_IN_BASE * BRICK_WIDTH / 2 + (i - 1) *
				 * BRICK_WIDTH+(j-1)*BRICK_WIDTH/2 is major part in this
				 * program. middle point - half length of base is point where
				 * first brick should be if brick is not first its start point
				 * should be number of brick times brick width for example: if
				 * brick is second after first brick start point should be start
				 * point of first brick + width of first brick + width of first
				 * brick after first brick +(j-1)*BRICK_WIDTH/2 if it's not in
				 * first row. start point of second row is half brick width
				 * right from start point of first row.
				 */
				GRect rect = new GRect(getWidth() / 2 - BRICKS_IN_BASE
						* BRICK_WIDTH / 2 + (i - 1) * BRICK_WIDTH + (j - 1)
						* BRICK_WIDTH / 2, getHeight() - j * BRICK_HEIGHT,
						BRICK_WIDTH, BRICK_HEIGHT);
				add(rect);
			}
			// number of bricks in rows are different. e.g. if in 3th row number
			// of bricks is 6, in 4th it will be 5.
			y--;
		}
	}
}
