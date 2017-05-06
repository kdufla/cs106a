/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */

import acm.graphics.*;

import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas implements
		FacePamphletConstants {

	/**
	 * Constructor This method takes care of any initialization needed for the
	 * display
	 */
	public FacePamphletCanvas() {
		// You fill this in
	}

	/**
	 * This method displays a message string near the bottom of the canvas.
	 * Every time this method is called, the previously displayed message (if
	 * any) is replaced by the new message text passed in.
	 */
	public void showMessage(String msg) {
		if (getElementAt(getWidth() / 2, getHeight() - BOTTOM_MESSAGE_MARGIN) != null)
			remove(getElementAt(getWidth() / 2, getHeight()
					- BOTTOM_MESSAGE_MARGIN));
		message = new GLabel(msg);
		message.setColor(Color.BLACK);
		message.setFont(MESSAGE_FONT);
		add(message, getWidth() / 2 - message.getWidth() * 3 / 4, getHeight()
				- BOTTOM_MESSAGE_MARGIN);
	}

	/**
	 * This method displays the given profile on the canvas. The canvas is first
	 * cleared of all existing items (including messages displayed near the
	 * bottom of the screen) and then the given profile is displayed. The
	 * profile display includes the name of the user from the profile, the
	 * corresponding image (or an indication that an image does not exist), the
	 * status of the user, and a list of the user's friends in the social
	 * network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		addName(profile.getName());
		addImage(profile.getImage());
		addStatus(profile.getStatus());
		addFriends(profile.getFriends());
	}

	private void addName(String name) {
		GLabel nm = new GLabel(name);
		nm.setFont(PROFILE_NAME_FONT);
		nm.setColor(Color.GRAY);
		double x = LEFT_MARGIN;
		heightN = nm.getHeight();
		double y = TOP_MARGIN + heightN;
		add(nm, x, y);
	}

	private void addImage(GImage image) {
		double x = LEFT_MARGIN;
		double y = TOP_MARGIN + heightN + IMAGE_MARGIN;
		if (image == null) {
			GRect frame = new GRect(x, y, IMAGE_WIDTH, IMAGE_HEIGHT);
			add(frame);
			GLabel noImage = new GLabel("No Image");
			noImage.setFont(PROFILE_IMAGE_FONT);
			add(noImage, x + IMAGE_WIDTH / 2 - noImage.getWidth() / 2, y
					+ IMAGE_HEIGHT / 2 - noImage.getHeight() / 2);
		} else {
			image.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
			add(image, x, y);
		}
	}

	private void addStatus(String stat) {
		status = new GLabel(stat);
		remove(status);
		status.setFont(PROFILE_STATUS_FONT);
		add(status, LEFT_MARGIN, TOP_MARGIN + heightN + IMAGE_MARGIN
				+ IMAGE_HEIGHT + STATUS_MARGIN + status.getHeight());
	}

	private void addFriends(ArrayList<String> frn) {
		GLabel friends = new GLabel("Friends:");
		friends.setFont(PROFILE_FRIEND_LABEL_FONT);
		add(friends, getWidth() / 2, TOP_MARGIN + heightN);
		Iterator<String> it = frn.iterator();
		int i = 0;
		while (it.hasNext()) {
			GLabel friendName = new GLabel(it.next());
			friendName.setFont(PROFILE_FRIEND_FONT);
			add(friendName,
					getWidth() / 2,
					TOP_MARGIN + heightN + friends.getHeight()
							+ friendName.getHeight() * i);
			i++;
		}
	}

	private GLabel message = new GLabel("");
	private double heightN;
	GLabel status = new GLabel("");
}
