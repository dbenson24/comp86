

import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class DirectionalButton extends JButton {
	private Point change;
	private Canvas target;
	
	public DirectionalButton(String text, Canvas target) {
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
		System.out.println(change);
		System.out.println(this.target);
		onClick();
	}
	
	// WARNING using a negative magnitude will invert controls
	public DirectionalButton(String text, Canvas target, int magnitude) {
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
	
	private void onClick () {
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(change);
				target.getUAV().translate(change.x, change.y);
				target.refresh();
			}
		});
	}
}
