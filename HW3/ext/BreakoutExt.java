/*
 * File: Breakout.java
 * -------------------
 * Name: Giorgi Gvelesiani
 * Section Leader: Davit Akopovi
 * 
 * This file will eventually implement the game of Breakout.
 * few things about sounds:
 * sounds in this application are famous. mostly because they 
 * are funny. "china" by donald trumph is famous because 
 * donald says china every time he's on TV.
 * max - http://coub.com/view/8r8jd
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class BreakoutExt extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 40;

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
	private static final int DELAY = 6;

	// heart image width
	private static final int IMG_W = 50;

	// heart image height
	private static final int IMG_H = 25;

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

	//y of second paddle
	private static final int DIST = HEIGHT/2-100;
	
	//width of exit button
	private static final double EXIT_X= 50;
	
	//height of exit button
	private static final double EXIT_Y= 25;

	/* Method: run() */
	/** Runs the Breakout program. */

	public void run() {
		while(true){
		initialization();
		gameProcess();
		}
	}

	private void initialization() { // getting ready for game
		addMouseListeners();
		drawBricks();
		drawPaddle();
		drawBall();
		drawSecondPassle();
		drawExit();
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

	private void drawSecondPassle() {
		sPaddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		sPaddle.setFilled(true);
		add(sPaddle, (WIDTH - PADDLE_WIDTH) / 2, DIST);
	}

	// second paddle
	private GRect sPaddle;

	// major part
	private void gameProcess() {
		int v = NBRICKS_PER_ROW * NBRICK_ROWS; // number of bricks
		points(v);
		for (int i = 0; i < NTURNS; i++) { // lives in game (turns)
			hearts(i); // draw hearts
			if (v == 0)
				break;
			ballStartPos();
			waitForClick();
			v = motionOfBall(v);
		}
		// finish game and write
		if (v == 0)
			win();
		else
			gameOver(v);
		pause(5000);
	}

	private void hearts(int i) {
		/*
		 * first it will write image with 3 hearts and than just overwrite new
		 * images after player's death.
		 */
		if (i == 0)
			newHeart("heart3.jpg"); // with 3 hearts
		if (i == 1)
			newHeart("heart2.jpg"); // with 2 hearts
		if (i == 2)
			newHeart("heart1.jpg"); // with 1 heart
	}

	private void newHeart(String image) {
		// add image on right top of application
		heart = new GImage(image);
		heart.setSize(IMG_W, IMG_H);
		add(heart, WIDTH - IMG_W, 0);
	}

	// hearts which is same as lives in games
	private GImage heart;

	private void ballStartPos() {
		// put ball in the middle of application
		ball.setLocation(WIDTH / 2 - BALL_RADIUS, HEIGHT / 2 - BALL_RADIUS);
	}

	private int motionOfBall(int v) {
		// everything about motion of ball is here
		double x = xRandom(); // returns random (-3<x<=-1)or(1<=x3)
		double y = yRandom(); // returns random 2<=y3
		double sx = xRandom();
		v = moveBall(x, y, v, sx);
		return v;
	}

	private int moveBall(double x, double y, int v, double sx) {
		// this is the most complicated method. I'm going to explain it below.
		while (true) {
			ball.move(x, y);
			sx = moveSecondPaddle(sx);
			y = sPaddleAndBall(y);
			pause(DELAY);
			x = xChangeDirection(x, ball, D); // x=-x if ball collides with vertical wall
			y = yChangeDirection(y); // y=-y if ball collides with top wall
			if (paddleAndBall(x, y) == 1&& y>0) {
				y = -y;
				x = -x;
			}
			if (paddleAndBall(x, y) == 2 &&y>0)
				y = -y;
			if (identifyBrick(y)) {
				/*
				 * if ball collides with brick: if ball is left or right of
				 * brick x=-x, if not it means that it collides with top or
				 * bottom so y=-y. than remove brick, number of bricks left
				 * minus 1 and write points. paddle is getting bigger with
				 * points and ball is getting faster with points
				 */
				GObject obj = objectRect();
				if (changeVector(obj, x, y))
					x = -x;
				else
					y = -y;
				remove(obj);
				v--;
				max.play();
				paddle.setSize(PADDLE_WIDTH + points(v) / 25, PADDLE_HEIGHT);
				x = speedUp(points(v), x);
				y = speedUp(points(v), y);
			}
			if (ball.getY() >= HEIGHT || v == 0) {
				if (ball.getY() >= HEIGHT)
					over.play();
				pause(2000);
				break;
			}
		}
		return v;
	}

	private double xRandom() {
		// generates random number for ball direction
		double x = rgen.nextDouble(0.5, 2.0);
		if (rgen.nextBoolean(0.5))
			x = -x;
		return x;
	}

	private double yRandom() {
		// generates random number for ball direction
		double x = rgen.nextDouble(1.0, 2.0);
		return x;
	}

	private double speedUp(int v, double x) {
		// |x| gets bigger
		if (x > 0)
			return x + 0.0001 * v;
		else
			return x - 0.0001 * v;
	}

	private double moveSecondPaddle(double sx) {
		sPaddle.move(sx, 0);
		sx = xChangeDirection(sx, sPaddle, PADDLE_WIDTH);
		return sx;
	}
	
	private double sPaddleAndBall(double y) {
		GObject obj = objectRect();
		if (obj == sPaddle)
			return -y;
		else
			return y;
	}

	private double xChangeDirection(double x, GObject obj, int l) {
		// return minus variable if ball collides with vertical wall. else
		// variable
		if (obj.getX() <= 0 || obj.getX() + l >= WIDTH) {
			return -x;
		} else {
			return x;
		}
	}

	private double yChangeDirection(double y) {
		// return minus variable if ball collides with top wall. else - variable
		if (ball.getY() <= 0) {
			return -y;
		} else {
			return y;
		}
	}

	private int paddleAndBall(double x, double y) {
		// return true if ball collides with paddle. else return false
		GObject obj = objectRect();
		Color color = rgen.nextColor();
		if (obj == paddle) {
			china.play();
			sPaddle.setColor(color);
			if ((ball.getX() + BALL_RADIUS < paddle.getX() + paddle.getWidth()
					/ 3 && x > 0)
					|| (ball.getX() + BALL_RADIUS >= paddle.getX()
							+ paddle.getWidth() * 2 / 3 && x < 0)) {
				return 1;
			} else {
				return 2;
			}

		} else
			return 0;
	}

	private GObject objectRect() {
		/*
		 * check 4 angles of rectangle outside ball. if one of them is in some
		 * object return object. else return null. numbers are clockwise,
		 * starting with top left angle
		 */
		double x = ball.getX();
		double y = ball.getY();
		if (getElementAt(x - 1, y - 1) != null) {
			GObject obj = getElementAt(x - 1, y - 1);
			return obj;
		}// #1
		if (getElementAt(x + D + 1, y - 1) != null) {
			GObject obj = getElementAt(x + D + 1, y - 1);
			return obj;
		}// #2
		if (getElementAt(x + D + 1, y + D + 1) != null) {
			GObject obj = getElementAt(x + D + 1, y + D + 1);
			return obj;
		}// #3
		if (getElementAt(x - 1, y + D + 1) != null) {
			GObject obj = getElementAt(x - 1, y + D + 1);
			return obj;
		}// #4
		else
			return null;
	}

	/*
	 * same as objectRect() with 8 angles instead private GObject objectOct() {
	 * 
	 * check 8 angles of octagon inside ball. if one of them is in some object
	 * return object. else return null. numbers are clockwise, starting with top
	 * angle
	 * 
	 * // distance between center of ball on x (or y) and inside angle double a
	 * = BALL_RADIUS * Math.sqrt(2) / 2; double x = ball.getX(); double y =
	 * ball.getY(); if (getElementAt(x + BALL_RADIUS, y - 1) != null) { GObject
	 * obj = getElementAt(x + BALL_RADIUS, y - 1); return obj; }// #1
	 * 
	 * if (getElementAt(x + BALL_RADIUS + a + 1, y + BALL_RADIUS - a - 1) !=
	 * null) { GObject obj = getElementAt(x + BALL_RADIUS + a + 1, y +
	 * BALL_RADIUS - a - 1); return obj; }// #2 if (getElementAt(x + D + 1, y +
	 * BALL_RADIUS) != null) { GObject obj = getElementAt(x + D + 1, y +
	 * BALL_RADIUS); return obj; }// #3
	 * 
	 * if (getElementAt(x + BALL_RADIUS + a + 1, y + BALL_RADIUS + a + 1) !=
	 * null) { GObject obj = getElementAt(x + BALL_RADIUS + a + 1, y +
	 * BALL_RADIUS + a + 1); return obj; }// #4 if (getElementAt(x +
	 * BALL_RADIUS, y + D + 1) != null) { GObject obj = getElementAt(x +
	 * BALL_RADIUS, y + D + 1); return obj; }// #5
	 * 
	 * if (getElementAt(x + BALL_RADIUS - a - 1, y + BALL_RADIUS + a + 1) !=
	 * null) { GObject obj = getElementAt(x + BALL_RADIUS - a - 1, y +
	 * BALL_RADIUS + a + 1); return obj; }// #6 if (getElementAt(x - 1, y +
	 * BALL_RADIUS) != null) { GObject obj = getElementAt(x - 1, y +
	 * BALL_RADIUS); return obj; }// #7
	 * 
	 * if (getElementAt(x + BALL_RADIUS - a - 1, y + BALL_RADIUS - a - 1) !=
	 * null) { GObject obj = getElementAt(x + BALL_RADIUS - a - 1, y +
	 * BALL_RADIUS - a - 1); return obj; }// #8 else return null; }
	 */

	// bounce sound
	AudioClip china = MediaTools.loadAudioClip("china.au");

	private boolean identifyBrick(double y) {
		// check if object object() method found is brick
		GObject obj = objectRect();
		if (obj != paddle && obj != null && obj != pointBackground
				&& obj != points && obj != heart && obj != sPaddle&& obj!=exit&&obj!=exitLabel)
			return true;
		else
			return false;
	}

	private boolean changeVector(GObject obj, double x, double y) {
		// check where is brick. show if it's left or right of ball.
		if ((ball.getX() + D < obj.getX() + x || ball.getX() > obj.getX()
				+ obj.getWidth() - x)
				&& (ball.getY() + D > obj.getY() + y || ball.getY() < obj
						.getY() + obj.getHeight() - y))
			return true;
		else
			return false;
	}

	private int points(int v) {
		// add white rectangle and print points on it
		int p = (NBRICKS_PER_ROW * NBRICK_ROWS - v) * 10;
		points = new GLabel("points: " + p);
		pointBackground = new GRect(0, 0, 100, 30);
		pointBackground.setFilled(true);
		pointBackground.setColor(Color.WHITE);
		add(pointBackground);
		add(points, 10, 20);
		ball.sendToFront(); // ball is always front
		return p;
	}

	private GLabel points; // points earned by player

	// bounce sound
	AudioClip max = MediaTools.loadAudioClip("max.au");

	/*
	 * I need this rectangle as white background. in my code new point is
	 * written on old one so it's almost impossible to see points. i'm adding
	 * white filled rectangle on old points. after, it looks like new point on
	 * white background. not on a lots of old points and old rectangles
	 */
	private GRect pointBackground;

	// over the line sound
	AudioClip over = MediaTools.loadAudioClip("over.au");

	private void win() {
		// message if player won the game
		removeAll();
		GLabel lab = new GLabel("Good Job! You Won!");
		add(lab, WIDTH / 2 - lab.getWidth() / 2, HEIGHT / 2 - lab.getAscent()
				/ 2);
		win.play();
	}

	// win sound
	AudioClip win = MediaTools.loadAudioClip("yeah!.au");

	private void gameOver(int v) {
		// message if player lost the game
		removeAll();
		int p = (NBRICKS_PER_ROW * NBRICK_ROWS - v) * 10;
		GLabel lab = new GLabel("Game Over! your points: " + p);
		add(lab, WIDTH / 2 - lab.getWidth() / 2, HEIGHT / 2 - lab.getAscent()
				/ 2);
	}

	public void mouseMoved(MouseEvent e) {
		// this makes paddle to follow mouse
		double w = paddle.getWidth() / 2;// half width of paddle
		if (e.getX() >= w && e.getX() <= WIDTH - w) {
			paddle.setLocation(e.getX() - w, HEIGHT - PADDLE_Y_OFFSET);
		}
	}
	private void drawExit(){
		exit=new GRect(EXIT_X,EXIT_Y);
		exit.setFilled(false);
		exitLabel=new GLabel ("EXIT");
		exitLabel.setFont("Sylfaen-20");
		add(exitLabel,WIDTH-EXIT_X+3,HEIGHT-3 );
		add(exit,WIDTH-EXIT_X, HEIGHT-EXIT_Y);
	}
	
	public GRect exit; 
	public GLabel exitLabel;
	
	public void mouseClicked(MouseEvent e) {
		if(getElementAt(e.getX(), e.getY())==exit){
			remove(exit);
			exit();
		}
	}

	// random generator
	private RandomGenerator rgen = RandomGenerator.getInstance();
}
