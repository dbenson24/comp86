
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;

public class LoggingJEditorPane extends JEditorPane implements ActionListener {

	public LoggingJEditorPane() {
		trackKeyStrokes();
	}

	public LoggingJEditorPane(URL arg0) throws IOException {
		super(arg0);
		trackKeyStrokes();
	}

	public LoggingJEditorPane(String arg0) throws IOException {
		super(arg0);
		trackKeyStrokes();
	}

	public LoggingJEditorPane(String arg0, String arg1) {
		super(arg0, arg1);
		trackKeyStrokes();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	private void trackKeyStrokes() {
		addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				System.out.println(getText()+e.getKeyChar());
			}
		});
	}
}
