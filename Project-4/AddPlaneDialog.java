import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * File: AddPlaneDialog.java
 * Date: Nov 3, 2015
 * Author: Derek
 * Email: Derek.Benson@tufts.edu
 * Description:
 * TODO
 *
 */

public class AddPlaneDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField AltitudeField;
	private JTextField SpeedField;
	private JTextField MaxSpeedField;
	private JTextField DirectionField;
	private JTextField XField;
	private JTextField YField;
	private Map target;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddPlaneDialog dialog = new AddPlaneDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddPlaneDialog(Map target) {
		this.target = target;
		setBounds(100, 100, 349, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		{
			JLabel lblAltitude = new JLabel("Altitude:");
			contentPanel.add(lblAltitude);
		}
		{
			AltitudeField = new JTextField();
			contentPanel.add(AltitudeField);
			AltitudeField.setColumns(10);
		}
		{
			JLabel lblSpeed = new JLabel("Speed:");
			contentPanel.add(lblSpeed);
		}
		{
			SpeedField = new JTextField();
			contentPanel.add(SpeedField);
			SpeedField.setColumns(10);
		}
		{
			JLabel lblMaxSpeed = new JLabel("Max Speed:");
			contentPanel.add(lblMaxSpeed);
		}
		{
			MaxSpeedField = new JTextField();
			contentPanel.add(MaxSpeedField);
			MaxSpeedField.setColumns(10);
		}
		{
			JLabel lblDirection = new JLabel("Direction:");
			contentPanel.add(lblDirection);
		}
		{
			DirectionField = new JTextField();
			contentPanel.add(DirectionField);
			DirectionField.setColumns(10);
		}
		{
			JLabel lblXPosition = new JLabel("X Position:");
			contentPanel.add(lblXPosition);
		}
		{
			XField = new JTextField();
			contentPanel.add(XField);
			XField.setColumns(10);
		}
		{
			JLabel lblYPosition = new JLabel("Y Position:");
			contentPanel.add(lblYPosition);
		}
		{
			YField = new JTextField();
			contentPanel.add(YField);
			YField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int x = Integer.parseInt(XField.getText());
						int y = Integer.parseInt(YField.getText());
						int altitude = Integer.parseInt(AltitudeField.getText());
						if( altitude > 65000) {
							altitude = 65000;
						}
						altitude = altitude * 100 / 65000;
						int maxSpeed = Integer.parseInt(MaxSpeedField.getText());
						int speed = Integer.parseInt(SpeedField.getText());
						speed = speed * 100 / maxSpeed;
						int direction = Integer.parseInt(DirectionField.getText());
						direction = (direction % 360) * 100 / 359;
						if(target != null) {
							target.addPlane(x, y, altitude, speed, maxSpeed, direction);
						}
						dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}

}
