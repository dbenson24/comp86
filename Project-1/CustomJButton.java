
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class CustomJButton extends JButton implements ActionListener {

	public CustomJButton() {
		super();
		addNameLogging();
	}

	public CustomJButton(Icon arg0) {
		super(arg0);
		addNameLogging();
	}

	public CustomJButton(String arg0) {
		super(arg0);
		addNameLogging();
	}

	public CustomJButton(Action arg0) {
		super(arg0);
		addNameLogging();
	
	}

	public CustomJButton(String arg0, Icon arg1) {
		super(arg0, arg1);
		addNameLogging();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	private void addNameLogging() {
		timesClicked = 0;
		String content = getText();
		setName(content+" Button");
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timesClicked++;
				System.out.println(getName()+" was clicked for the "+timesClicked+" time.");
				if (getText().equals(content)){
					setText("Clicked");
				} else {
					setText(content);
				}
			}
		});
	}
	
	private int timesClicked;
}
