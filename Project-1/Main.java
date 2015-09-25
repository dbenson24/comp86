/**
 * Name: Derek Benson
 * Email: Derek.Benson@tufts.edu
 * This is the main class, it creates a window and 
 * initializes the JFrame.
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

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
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		CustomJButton btnHello = new CustomJButton("Hello");
		frame.getContentPane().add(btnHello, BorderLayout.WEST);
		
		CustomJButton btnWorld = new CustomJButton("World");
		frame.getContentPane().add(btnWorld, BorderLayout.EAST);

		LoggingJEditorPane editorPane = new LoggingJEditorPane();
		frame.getContentPane().add(editorPane, BorderLayout.NORTH);

        JEditorPane coursePage = new JEditorPane();
        coursePage.setEditable(false);
        try {
			coursePage.setPage("http://www.cs.tufts.edu/~jacob/86/syllabus.html");
		} catch (IOException e) {
			coursePage.setContentType("text/html");
			coursePage.setText("<html> <body>Unable to load Course Home Page</body> </html>");
		}
        
        JScrollPane scrollPane = new JScrollPane(coursePage);
        frame.getContentPane().add(scrollPane);
	}

}
