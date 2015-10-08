import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;

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
		

		Canvas canvas = new Canvas();
		canvas.setParent(frame);
		frame.getContentPane().add(canvas, BorderLayout.CENTER);
		
		JPanel directionalButtons = new JPanel();
		frame.getContentPane().add(directionalButtons, BorderLayout.SOUTH);
		
		JButton leftButton = new DirectionalButton("Left", canvas);
		directionalButtons.add(leftButton);
		
		JButton upButton = new DirectionalButton("Up", canvas);
		directionalButtons.add(upButton);
		
		JButton downButton = new DirectionalButton("Down", canvas);
		directionalButtons.add(downButton);
		
		JButton rightButton = new DirectionalButton("Right", canvas);
		directionalButtons.add(rightButton);
		
	}

}
