/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 400;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1)
			* BRICK_SEP)
			/ NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;

	// diameter of ball in pixels
	private static final int D = 2 * BALL_RADIUS;

	// argument for pause() method
	private static final int DELAY = 1;
	
	// first color
	private static final Color FIRST = Color.RED;

	// second color
	private static final Color SECOND = Color.ORANGE;

	// third color
	private static final Color THIRD = Color.YELLOW;

	// fourth color
	private static final Color FOURTH = Color.GREEN;

	// fifth color
	private static final Color FIFTH = Color.CYAN;

	/* Method: run() */
	/** Runs the Breakout program. */

	public void run() {
		addMouseListeners();
		initialisation();
		gameProcess();

	}

	private void initialisation() { // getting ready for game
		drawBricks();
		drawPaddle();
		drawBall();
	}

	private void drawBricks() {
		/*
		 * draw bricks using color and number of where you want it this method
		 * is for drawing all bricks
		 */
		drawSameColorBricks(0, FIRST);
		drawSameColorBricks(1, SECOND);
		drawSameColorBricks(2, THIRD);
		drawSameColorBricks(3, FOURTH);
		drawSameColorBricks(4, FIFTH);
	}

	private void drawSameColorBricks(int n, Color c) {
		// can draw only same color bricks and only same colored bricks
		int l = BRICK_HEIGHT + BRICK_SEP; // distance between coordinates on y
		int nColors = 5; /*
						 * number of colors. warning: number of brick
						 * rows/number of colors must be integer
						 */
		int p = NBRICK_ROWS / nColors; // number of same colored rows
		// two cycles: one for rows, one for columns
		for (double y = n * p * l + BRICK_Y_OFFSET; y < n * p * l + p * l
				+ BRICK_Y_OFFSET; y += l) {
			int k = BRICK_WIDTH + BRICK_SEP; /* distance between coordinates on x */
			for (double x = 0; x <= WIDTH - BRICK_WIDTH; x += k) {
				GRect rect = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
				rect.setFilled(true);
				rect.setColor(c);
				add(rect, x, y);
			}
		}
	}

	private void drawPaddle() {
		// draw paddle in middle of x and PADDLE_Y_OFFSET up from bottom
		paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle, (WIDTH - PADDLE_WIDTH) / 2, HEIGHT - PADDLE_Y_OFFSET);
	}

	// paddle which will be used everywhere else
	private GRect paddle;

	private void drawBall() {
		// its still initialization. it will draw ball.
		ball = new GOval(D, D);
		ball.setFilled(true);
		add(ball, WIDTH / 2 - BALL_RADIUS, HEIGHT / 2 - BALL_RADIUS);
	}

	private GOval ball; // just ball


	private void gameProcess() {
		int v = NBRICKS_PER_ROW * NBRICK_ROWS; //number of bricks
		for (int i = 0; i < NTURNS; i++) {//turns
			if (v == 0)
				break;
			ballStartPos();
			v = moveBall(v);
		}
		// finish game and write
		if (v == 0)
			win();
		else
			gameOver();
	}

	

	private int moveBall(int v) {
		double x = xRandom();
		double y = 3.00;
		while (true) {
			ball.move(x, y);
			pause(DELAY);
			x = xChangeDirection(x);
			y = yChangeDirection(y);
			y = paddleAndBall(y);
			if (destroyBrick(y) == 1) {
				GObject obj = object();
				remove(obj);
				v--;
				y = -y;
			}
			if (ball.getY() >= HEIGHT || v == 0) {
				break;
			}
		}
		return v;
	}

	private double xRandom() {
		double x = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5))
			x = -x;
		return x;
	}

	private double xChangeDirection(double x) {
		if (ball.getX() <= 0 || ball.getX() + 2 * BALL_RADIUS >= WIDTH) {
			return -x;
		} else {
			return x;
		}
	}

	private double yChangeDirection(double y) {
		if (ball.getY() <= 0) {
			return -y;
		} else {
			return y;
		}
	}

	private double paddleAndBall(double y) {
		GObject obj = object();
		if (obj == paddle && y > 0)
			return -y;
		else
			return y;
	}

	private void ballStartPos() {
		ball.setLocation(WIDTH / 2 - BALL_RADIUS, HEIGHT / 2 - BALL_RADIUS);
	}

	private void gameOver() {
		removeAll();
		GLabel lab = new GLabel("Game Over");
		add(lab, WIDTH / 2 - lab.getWidth() / 2, HEIGHT / 2 - lab.getAscent()
				/ 2);
	}

	private GObject object() {
		if (getElementAt(ball.getX(), ball.getY()) != null) {
			GObject obj = getElementAt(ball.getX(), ball.getY());
			return obj;
		}
		if (getElementAt(ball.getX() + D, ball.getY()) != null) {
			GObject obj = getElementAt(ball.getX() + D, ball.getY());
			return obj;
		}
		if (getElementAt(ball.getX() + D, ball.getY() + D) != null) {
			GObject obj = getElementAt(ball.getX() + D, ball.getY() + D);
			return obj;
		}
		if (getElementAt(ball.getX(), ball.getY() + D) != null) {
			GObject obj = getElementAt(ball.getX(), ball.getY() + D);
			return obj;
		} else
			return null;
	}

	private int destroyBrick(double y) {
		GObject obj = object();
		if (obj != paddle && obj != null)
			return 1;
		else
			return 0;
	}
	
	//
	private void win() {
		removeAll();
		GLabel lab = new GLabel("Good Job! You Won!");
		add(lab, WIDTH / 2 - lab.getWidth() / 2, HEIGHT / 2 - lab.getAscent()
				/ 2);
	}
	
	// this makes paddle to follow mouse
		public void mouseMoved(MouseEvent e) {
			int w = PADDLE_WIDTH / 2;// half width of paddle
			if (e.getX() >= w && e.getX() <= WIDTH - w) {
				paddle.setLocation(e.getX() - w, HEIGHT - PADDLE_Y_OFFSET);
			}
		}

	private RandomGenerator rgen = RandomGenerator.getInstance();
}
