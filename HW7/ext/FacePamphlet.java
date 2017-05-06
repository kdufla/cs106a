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

		add(new JLabel(" M:"), NORTH);

		String[] days = new String[31];
		for (int i = 1; i <= 31; i++) {
			days[i - 1] = "" + i;
		}

		String[] months = new String[12];
		for (int i = 1; i <= 12; i++) {
			months[i - 1] = "" + i;
		}
		monthsList = new JComboBox(months);
		add(monthsList, NORTH);
		monthsList.addActionListener(this);

		add(new JLabel(" Y:"), NORTH);

		String[] years = new String[110];
		for (int i = 1901; i <= 2010; i++) {
			years[i - 1901] = "" + i;
		}
		yearsList = new JComboBox(years);
		add(yearsList, NORTH);
		yearsList.addActionListener(this);

		add(canvas);

		addActionListeners();
	}

	/**
	 * This class is responsible for detecting when the buttons are clicked or
	 * interactors are used, so you will have to add code to respond to these
	 * actions.
	 */
	public void actionPerformed(ActionEvent e) {
		
		profileInUse=base.getProfile(profileText.getText().toLowerCase());

		//add pressed
		if (e.getSource() == add
				&& !profileText.getText().toLowerCase().equals("")) {
			if (base.containsProfile(profileText.getText().toLowerCase())) {
				canvas.displayProfile(profileInUse);
				canvas.showMessage("name "
						+ profileText.getText().toLowerCase() + " is used");
			} else {
				profileInUse = new FacePamphletProfile(profileText.getText()
						.toLowerCase(), (String) monthsList.getSelectedItem(),
						(String) yearsList.getSelectedItem());
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
				canvas.showMessage(profileText.getText().toLowerCase()
						+ " deleted");
				

			} else
				canvas.showMessage("invalid name");
		}

		//lookup pressed
		if (e.getSource() == lookup) {
			if (base.containsProfile(profileText.getText().toLowerCase())) {
				profileInUse = base.getProfile(profileText.getText()
						.toLowerCase());
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
			base.rewriteData();
		}

		//change picture pressed
		if (e.getSource() == picture) {
			try {
				GImage image = new GImage(pictureText.getText());
				profileInUse.setImage(pictureText.getText());
				canvas.displayProfile(profileInUse);
				canvas.showMessage("Picture has been successfully updated");
				base.rewriteData();
			} catch (ErrorException ex) {
				canvas.showMessage("Image doesn't exist");
			}
		}

		//add friend pressed
		if (e.getSource() == friend) {
			if (base.containsProfile(friendText.getText().toLowerCase())) {
				if (profileInUse.addFriend(friendText.getText().toLowerCase())) {
					canvas.showMessage("Already friends");
				} else {
					profileInUse.addFriend(friendText.getText().toLowerCase());
					base.getProfile(friendText.getText().toLowerCase())
							.addFriend(profileInUse.getName());
					canvas.displayProfile(profileInUse);
					canvas.showMessage(friendText.getText().toLowerCase()
							+ " was added to friends list");
					base.rewriteData();
				}
			} else
				canvas.showMessage("No account found");
		}
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
	private JComboBox monthsList;
	private JComboBox yearsList;
}
