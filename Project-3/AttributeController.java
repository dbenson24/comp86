import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * File: AttributeController.java Date: Oct 21, 2015 Author: Derek Email:
 * Derek.Benson@tufts.edu Description: TODO
 *
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
					switch ((String)comboBox.getSelectedItem()) {
					case "altitude":
						slider.setValue(target.getCurrent().getAltitude());
						break;
					case "speed":
						slider.setValue(target.getCurrent().getSpeed());
						break;
					case "direction":
						slider.setValue(target.getCurrent().getDirection());
						break;
					}
					
					target.refresh();
				} else {
					System.out.println("There is no current target");
				}
				System.out.println(comboBox.getSelectedItem());
			}
		});
				
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				/* Handle what happens when the slider is changed */
				if (target.getCurrent() != null) {
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
				} else {
					System.out.println("There is no current target");
				}
				System.out.println(slider.getValue());
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
		if(target.getCurrent() != null) {
			switch ((String)comboBox.getSelectedItem()) {
			case "altitude":
				slider.setValue(target.getCurrent().getAltitude());
				break;
			case "speed":
				slider.setValue(target.getCurrent().getSpeed());
				break;
			case "direction":
				slider.setValue(target.getCurrent().getDirection());
				break;
			}
		} 
	}
}
