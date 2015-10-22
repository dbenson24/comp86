
/**
 * File: Main.java
 * Date: Oct 7, 2015
 * Author: Derek
 * Email: Derek.Benson@tufts.edu
 * Description:
 * This file sets up the main execution loop and
 * initializes all of the JPanels and buttons.
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1366, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Map map = new Map(frame);
		frame.getContentPane().add(map, BorderLayout.CENTER);

		/* Used to group the buttons together at the bottom */
		JPanel directionalButtons = new JPanel();
		frame.getContentPane().add(directionalButtons, BorderLayout.SOUTH);
		
		AttributeController ac = new AttributeController();
		ac.setTarget(map);
		directionalButtons.add(ac);

		DirectionalButton leftButton = new DirectionalButton("Left", map);
		directionalButtons.add(leftButton);

		DirectionalButton upButton = new DirectionalButton("Up", map);
		directionalButtons.add(upButton);

		DirectionalButton downButton = new DirectionalButton("Down", map);
		directionalButtons.add(downButton);

		DirectionalButton rightButton = new DirectionalButton("Right", map);
		directionalButtons.add(rightButton);

	}

}
