import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * File: AttributeController.java 
 * Date: Oct 21, 2015 
 * Author: Derek 
 * Email: Derek.Benson@tufts.edu 
 * Description:
 * The AttributeController is a flow layout JPanel that can be used to adjust
 * the attributes of a plane. A combobox allows a user to select either speed,
 * altitude, or direction and a slider allows the user to adjust the selected value.
 */

public class AttributeController extends JPanel {

	private JComboBox<String> comboBox;
	private JSlider slider;
	
	private Map target;

	/**
	 * 
	 */
	public AttributeController() {
		init();
	}
	
	/**
	 * @param target
	 */

	public AttributeController(Map target) {
		super();
		init();
		this.target = target;
	}
	
	/**
	 * @param layout
	 */
	public AttributeController(LayoutManager layout) {
		super(layout);
		init();
	}

	/**
	 * @param isDoubleBuffered
	 */
	public AttributeController(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		init();
	}

	/**
	 * @param layout
	 * @param isDoubleBuffered
	 */
	public AttributeController(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		init();
	}

	private void init() {
		String[] labels = { "speed", "altitude", "direction" };
		comboBox = new JComboBox<String>(labels);

		slider = new JSlider();

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* Handle what happens when the comboBox is changed */
				if (target.getCurrent() != null) {
					slider.setEnabled(false);
					switch ((String)comboBox.getSelectedItem()) {
					case "altitude":
						slider.setMaximum(target.getCurrent().getMaxAltitude());
						slider.setValue(target.getCurrent().getAltitude());
						break;
					case "speed":
						slider.setMaximum(target.getCurrent().getMaxSpeed());
						slider.setValue(target.getCurrent().getSpeed());
						break;
					case "direction":
						slider.setMaximum(360);
						slider.setValue(target.getCurrent().getDirection());
						break;
					}
					slider.setEnabled(true);
					target.refresh();
				}
			}
		});
				
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				/* Handle what happens when the slider is changed */
				if (target.getCurrent() != null && slider.isEnabled()) {
					switch ((String)comboBox.getSelectedItem()) {
					case "altitude":
						target.getCurrent().setAltitude(slider.getValue());
						break;
					case "speed":
						target.getCurrent().setSpeed(slider.getValue());
						break;
					case "direction":
						target.getCurrent().setDirection(slider.getValue());
						break;
					}
					target.refresh();
				}
			}
		});
		add(comboBox);
		add(slider);
		
		target = null;
	}

	public void setTarget(Map target) {
		this.target = target;
	}

	public Map getTarget(Map target) {
		return target;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		/* Make sure that the slider represents the state of the current plane */
		if(target.getCurrent() != null) {
			slider.setEnabled(false);
			switch ((String)comboBox.getSelectedItem()) {
			case "altitude":
				slider.setMaximum(target.getCurrent().getMaxAltitude());
				slider.setValue(target.getCurrent().getAltitude());
				break;
			case "speed":
				slider.setMaximum(target.getCurrent().getMaxSpeed());
				slider.setValue(target.getCurrent().getSpeed());
				break;
			case "direction":
				slider.setMaximum(360);
				slider.setValue(target.getCurrent().getDirection());
				break;
			}
			slider.setEnabled(true);
		} 
	}
}
