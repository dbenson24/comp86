/**
 * File: DirectionButton.java
 * Date: Oct 7, 2015
 * Author: Derek
 * Email: Derek.Benson@tufts.edu
 * Description:
 * DirectionalButton extends JButton and enables
 * a button to control a UAV on the target Map.
 * It defaults to making the UAV move 25 pixels at
 * a time but an optional parameter magnitude allows
 * that value to be customized.
 */

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class DirectionalButton extends JButton {
	private Point change;
	private Map target;

	public DirectionalButton(String text, Map target) {
		super(text);
		this.target = target;
		switch (text) {
		case "Up":
			change = new Point(0, -25);
			break;
		case "Down":
			change = new Point(0, 25);
			break;
		case "Left":
			change = new Point(-25, 0);
			break;
		case "Right":
			change = new Point(25, 0);
		}
		onClick();
	}

	// WARNING using a negative magnitude will invert controls
	public DirectionalButton(String text, Map target, int magnitude) {
		super(text);
		this.target = target;
		switch (text) {
		case "Up":
			change = new Point(0, -magnitude);
			break;
		case "Down":
			change = new Point(0, magnitude);
			break;
		case "Left":
			change = new Point(-magnitude, 0);
			break;
		case "Right":
			change = new Point(magnitude, 0);
		}
		onClick();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (target != null && target.isAnimating()) {
			setEnabled(true);
		} else {
			setEnabled(false);
		}
	}
	
	
	private void onClick() {
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* Log and then translate the UAV */
				if (target.getCurrent() != null) {
					System.out.println("Button: " + getText() + " -> " + change);
					target.getCurrent().translate(change.x, change.y);
					target.refresh();
				} else {
					System.out.println("There is no current target");
				}
			}
		});
	}
}
