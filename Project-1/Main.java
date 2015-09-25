/**
 * Name: Derek Benson
 * Email: Derek.Benson@tufts.edu
 * This is the main class, it creates a window and 
 * initializes the JFrame.
 */
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;

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
		frame.setBounds(100, 100, 500, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		CustomJButton btnHello = new CustomJButton("Hello");
		frame.getContentPane().add(btnHello);

		CustomJButton btnWorld = new CustomJButton("World");
		frame.getContentPane().add(btnWorld);

		LoggingJEditorPane editorPane = new LoggingJEditorPane();
		frame.getContentPane().add(editorPane);

        JEditorPane coursePage = new JEditorPane();
        coursePage.setEditable(false);
        coursePage.setPage("http://www.cs.tufts.edu/~jacob/86/syllabus.html");
        frame.getContentPane().add(coursePage);
	}

}
