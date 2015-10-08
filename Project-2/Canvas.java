/**
 * File: Canvas.java
 * Date: Oct 7, 2015
 * Author: Derek
 * Email: Derek.Benson@tufts.edu
 * Description:
 * This file implements the Canvas object.
 * A Canvas extends JPanel and draws onto itself
 * a number of clouds as well as a UAV for someone
 * to pilot.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Canvas extends JPanel {
	private UAV uav;
	private JFrame parent;
	private boolean hasParent;
	
	public Canvas() {
		uav = new UAV();
		hasParent = false;
		setBackground(Color.CYAN);
	}
	
	public Canvas(JFrame parent){
		uav = new UAV();
		this.parent = parent;
		this.hasParent = true;
		setBackground(Color.CYAN);
	}
	
	public Canvas(LayoutManager arg0) {
		super(arg0);
		uav = new UAV();
		hasParent = false;
		setBackground(Color.CYAN);
	}

	public Canvas(boolean arg0) {
		super(arg0);
		uav = new UAV();
		hasParent = false;
		setBackground(Color.CYAN);
	}

	public Canvas(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		uav = new UAV();
		hasParent = false;
		setBackground(Color.CYAN);
	}
	
	public UAV getUAV() {
		return uav;
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    BufferedImage img = null;
	    try {
	    	System.out.println();
	    	File wd = new File(System.getProperty("user.dir"));
	        img = ImageIO.read(new File(wd, "cloud.png"));
	    } catch (IOException e) {
	    	System.out.println("Ioexception");
	    }
	    g.drawImage(img, 50, 300, null);
	    g.drawImage(img, 350, 375, null);
	    g.drawImage(img, 1000, 50, null);
	    g.drawImage(img, 900, 400, null);
		g.fillPolygon(uav);
	}
	
	public void setParent(JFrame parent) {
		this.parent = parent;
		hasParent = true;
	}
	
	public void refresh() {
		if (hasParent) {
			parent.revalidate();
			parent.repaint();
		} else {
			revalidate();
			repaint();
		}
	}
}
