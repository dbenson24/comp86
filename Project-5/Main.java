
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

		Map map = new Map(frame);
		frame.getContentPane().add(map, BorderLayout.CENTER);

		/* Used to group the buttons together at the bottom */
		JPanel controlPanel = new JPanel();
		frame.getContentPane().add(controlPanel, BorderLayout.SOUTH);
		
		/* Used to group the combobox and slider together */
		AttributeController ac = new AttributeController(map);
		controlPanel.add(ac);

		DirectionalButton leftButton = new DirectionalButton("Left", map);
		controlPanel.add(leftButton);

		DirectionalButton upButton = new DirectionalButton("Up", map);
		controlPanel.add(upButton);

		DirectionalButton downButton = new DirectionalButton("Down", map);
		controlPanel.add(downButton);

		DirectionalButton rightButton = new DirectionalButton("Right", map);
		controlPanel.add(rightButton);
		
		MapMenuBar menuBar = new MapMenuBar(map);
		frame.setJMenuBar(menuBar);

	}

}
