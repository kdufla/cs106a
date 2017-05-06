/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;

import java.awt.event.*;
import java.util.Iterator;

import javax.swing.*;

public class FacePamphlet extends Program implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the interactors in
	 * the application, and taking care of any other initialization that needs
	 * to be performed.
	 */
	public void init() {

		add(new JLabel("Name"), NORTH);

		profileText = new JTextField(TEXT_FIELD_SIZE);
		add(profileText, NORTH);
		profileText.addActionListener(this);

		add = new JButton("ADD");
		add(add, NORTH);

		delete = new JButton("DELETE");
		add(delete, NORTH);

		lookup = new JButton("LOOKUP");
		add(lookup, NORTH);

		statusText = new JTextField(TEXT_FIELD_SIZE);
		add(statusText, WEST);
		statusText.addActionListener(this);

		status = new JButton("CHANGE STATUS");
		add(status, WEST);

		add(new JLabel(EMPTY_LABEL_TEXT), WEST);

		pictureText = new JTextField(TEXT_FIELD_SIZE);
		add(pictureText, WEST);
		pictureText.addActionListener(this);

		picture = new JButton("CHANGE PICTURE");
		add(picture, WEST);

		add(new JLabel(EMPTY_LABEL_TEXT), WEST);

		friendText = new JTextField(TEXT_FIELD_SIZE);
		add(friendText, WEST);
		friendText.addActionListener(this);

		friend = new JButton("ADD FRIEND");
		add(friend, WEST);

		int[] days = new int[31];
		for (int i = 1; i <= 31; i++)

			add(canvas);

		addActionListeners();
	}

	/**
	 * This class is responsible for detecting when the buttons are clicked or
	 * interactors are used, so you will have to add code to respond to these
	 * actions.
	 */
	public void actionPerformed(ActionEvent e) {

		//add pressed
		if (e.getSource() == add && !profileText.getText().toLowerCase().equals("")) {
			if (base.containsProfile(profileText.getText().toLowerCase())) {
				canvas.displayProfile(profileInUse);
				canvas.showMessage("name " + profileText.getText().toLowerCase() + " is used");
			} else {
				profileInUse = new FacePamphletProfile(profileText.getText().toLowerCase());
				base.addProfile(profileInUse);
				canvas.displayProfile(profileInUse);
			}
		}

		//delete pressed
		if (e.getSource() == delete) {
			if (base.containsProfile(profileText.getText().toLowerCase())) {
				Iterator<String> it = profileInUse.getFriends().iterator();
				while (it.hasNext()) {
					base.getProfile(it.next()).removeFriend(
							profileInUse.getName());
				}
				base.deleteProfile(profileText.getText().toLowerCase());
				profileInUse = null;
				canvas.displayProfile(profileInUse);
				canvas.showMessage(profileText.getText().toLowerCase() + " deleted");

			} else
				canvas.showMessage("invalid name");
		}

		//lookup pressed
		if (e.getSource() == lookup) {
			if (base.containsProfile(profileText.getText().toLowerCase())) {
				profileInUse = base.getProfile(profileText.getText().toLowerCase());
				canvas.displayProfile(profileInUse);
			} else {
				profileInUse = null;
				canvas.showMessage("No results found for your query.");
			}
		}

		//change status pressed
		if (e.getSource() == status) {
			profileInUse.setStatus(statusText.getText().toLowerCase());
			canvas.displayProfile(profileInUse);
			canvas.showMessage("Status changed to " + profileInUse.getStatus());
		}

		//change picture pressed
		if (e.getSource() == picture) {
			GImage image = null;
			try {
				image = new GImage(pictureText.getText());
			} catch (ErrorException ex) {
				canvas.showMessage("Image doesn't exist");
			}
			profileInUse.setImage(image);
			canvas.displayProfile(profileInUse);
			canvas.showMessage("Picture has been successfully updated");
		}

		//add friend pressed
		if (e.getSource() == friend) {
			if (base.containsProfile(friendText.getText().toLowerCase())) {
				if (profileInUse.getFriends().contains(friendText.getText().toLowerCase())) {
					canvas.showMessage("Already friends");
				} else {
					profileInUse.addFriend(friendText.getText().toLowerCase());
					base.getProfile(friendText.getText().toLowerCase()).addFriend(
							profileInUse.getName());
					canvas.displayProfile(profileInUse);
					canvas.showMessage(friendText.getText().toLowerCase()
							+ " was added to friends list");
				}
			} else
				canvas.showMessage("No account found");
		}

		// println(profileInUse.getName() + " in use");
	}

	//instance variables
	private JButton add;
	private JButton delete;
	private JButton lookup;
	private JButton status;
	private JButton picture;
	private JButton friend;
	private JTextField profileText;
	private JTextField statusText;
	private JTextField pictureText;
	private JTextField friendText;
	private FacePamphletDatabase base = new FacePamphletDatabase();
	private FacePamphletProfile profileInUse;
	private FacePamphletCanvas canvas = new FacePamphletCanvas();
}
