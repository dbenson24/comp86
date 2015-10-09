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
		

		Canvas canvas = new Canvas(frame);
		frame.getContentPane().add(canvas, BorderLayout.CENTER);
		
		/* Used to group the buttons together at the bottom */
		JPanel directionalButtons = new JPanel();
		frame.getContentPane().add(directionalButtons, BorderLayout.SOUTH);
		
		DirectionalButton leftButton = new DirectionalButton("Left", canvas);
		directionalButtons.add(leftButton);
		
		DirectionalButton upButton = new DirectionalButton("Up", canvas);
		directionalButtons.add(upButton);
		
		DirectionalButton downButton = new DirectionalButton("Down", canvas);
		directionalButtons.add(downButton);
		
		DirectionalButton rightButton = new DirectionalButton("Right", canvas);
		directionalButtons.add(rightButton);
		
	}

}
