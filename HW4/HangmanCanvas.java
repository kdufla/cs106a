/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Color;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

	/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		GLine a = new GLine(getWidth() / 2 - BEAM_LENGTH, getHeight(),
				getWidth() / 2 - BEAM_LENGTH, getHeight() - SCAFFOLD_HEIGHT);
		GLine b = new GLine(getWidth() / 2 - BEAM_LENGTH, getHeight()
				- SCAFFOLD_HEIGHT, getWidth() / 2, getHeight()
				- SCAFFOLD_HEIGHT);
		GLine c = new GLine(getWidth() / 2, getHeight() - SCAFFOLD_HEIGHT,
				getWidth() / 2, getHeight() - SCAFFOLD_HEIGHT + ROPE_LENGTH);
		add(a);
		add(b);
		add(c);
	}

	/**
	 * Updates the word on the screen to correspond to the current state of the
	 * game. The argument string shows what letters have been guessed so far;
	 * unguessed letters are indicated by hyphens.
	 */
	public void displayWord(String word) {
		int y = 40;
		GLabel lab = new GLabel(word);
		lab.setFont("Sylfaen-25");
		GRect background = new GRect(lab.getWidth(), lab.getHeight());
		background.setFilled(true);
		background.setColor(Color.WHITE);
		add(background, getWidth() / 2 - lab.getWidth() / 2,
				y - lab.getHeight());
		add(lab, getWidth() / 2 - lab.getWidth() / 2, y);
	}

	/**
	 * Updates the display to correspond to an incorrect guess by the user.
	 * Calling this method causes the next body part to appear on the scaffold
	 * and adds the letter to the list of incorrect guesses that appears at the
	 * bottom of the window.
	 */
	public void noteIncorrectGuess(int i, String wrong) {
		wrongGuess(wrong);
		head(i);
		body(i);
		leftArm(i);
		rightArm(i);
		leftLeg(i);
		rightLeg(i);
		leftFoot(i);
		rightFoot(i);
	}

	private void head(int i) {
		if (i == 7) {
			GOval head = new GOval(2 * HEAD_RADIUS, 2 * HEAD_RADIUS);
			add(head, getWidth() / 2 - HEAD_RADIUS, getHeight()
					- SCAFFOLD_HEIGHT + ROPE_LENGTH);
		}
	}

	private void body(int i) {
		if (i == 6) {
			GLine body = new GLine(getWidth() / 2, getHeight()
					- SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS,
					getWidth() / 2, getHeight() - SCAFFOLD_HEIGHT + ROPE_LENGTH
							+ 2 * HEAD_RADIUS + BODY_LENGTH);
			add(body);
		}
	}

	private void leftArm(int i) {
		if (i == 5) {
			GLine leftArm = new GLine(getWidth() / 2, getHeight()
					- SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS
					+ ARM_OFFSET_FROM_HEAD, getWidth() / 2 - UPPER_ARM_LENGTH,
					getHeight() - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2
							* HEAD_RADIUS + ARM_OFFSET_FROM_HEAD
							+ LOWER_ARM_LENGTH);
			add(leftArm);
		}
	}

	private void rightArm(int i) {
		if (i == 4) {
			GLine rightArm = new GLine(getWidth() / 2, getHeight()
					- SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS
					+ ARM_OFFSET_FROM_HEAD, getWidth() / 2 + UPPER_ARM_LENGTH,
					getHeight() - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2
							* HEAD_RADIUS + ARM_OFFSET_FROM_HEAD
							+ LOWER_ARM_LENGTH);
			add(rightArm);
		}
	}

	private void leftLeg(int i) {
		if (i == 3) {
			GLine leftLeg = new GLine(getWidth() / 2, getHeight()
					- SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS
					+ BODY_LENGTH, getWidth() / 2 - HIP_WIDTH, getHeight()
					- SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS
					+ BODY_LENGTH + LEG_LENGTH);
			add(leftLeg);
		}
	}

	private void rightLeg(int i) {
		if (i == 2) {
			GLine rightLeg = new GLine(getWidth() / 2, getHeight()
					- SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS
					+ BODY_LENGTH, getWidth() / 2 + HIP_WIDTH, getHeight()
					- SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS
					+ BODY_LENGTH + LEG_LENGTH);
			add(rightLeg);
		}
	}

	private void leftFoot(int i) {
		if (i == 1) {
			GLine leftFoot = new GLine(getWidth() / 2 - HIP_WIDTH, getHeight()
					- SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS
					+ BODY_LENGTH + LEG_LENGTH, getWidth() / 2 - HIP_WIDTH
					- FOOT_LENGTH, getHeight() - SCAFFOLD_HEIGHT + ROPE_LENGTH
					+ 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
			add(leftFoot);
		}
	}

	private void rightFoot(int i) {
		if (i == 0) {
			GLine rightFoot = new GLine(getWidth() / 2 + HIP_WIDTH, getHeight()
					- SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS
					+ BODY_LENGTH + LEG_LENGTH, getWidth() / 2 + HIP_WIDTH
					+ FOOT_LENGTH, getHeight() - SCAFFOLD_HEIGHT + ROPE_LENGTH
					+ 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
			add(rightFoot);
		}
	}

	private void wrongGuess(String word){
		GLabel lab = new GLabel(word);
		lab.setFont("Sylfaen-20");
		GRect background = new GRect(lab.getWidth(), lab.getHeight());
		background.setFilled(true);
		background.setColor(Color.WHITE);
		add(background, getWidth() / 2 - lab.getWidth() / 2,
				70 - lab.getHeight());
		add(lab, getWidth() / 2 - lab.getWidth() / 2, 70);
	}
	
	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}
