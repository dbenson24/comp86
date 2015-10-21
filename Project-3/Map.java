/**
 * File: Map.java
 * Date: Oct 7, 2015
 * Author: Derek
 * Email: Derek.Benson@tufts.edu
 * Description:
 * This file implements the Map object.
 * A Map extends JPanel and draws onto itself
 * a number of clouds as well as a UAV for someone
 * to pilot.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Map extends JPanel {
	private Plane current;
	private ArrayList<Plane> planes;
	private JFrame parent;
	private boolean hasParent;

	public Map() {
		init();
	}

	public Map(JFrame parent){
		init();
		this.parent = parent;
		hasParent = true;
	}

	public Map(LayoutManager arg0) {
		super(arg0);
		init();
	}

	public Map(boolean arg0) {
		super(arg0);
		init();
	}

	public Map(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		init();
	}

	private void init() {
		current = new UAV(true);
		planes = new ArrayList();
		planes.add(current);
		planes.add(new UAV(100, 100));
		planes.add(new UAV(200, 200));
		hasParent = false;
		setBackground(Color.CYAN);
		onClick();
	}
	
	public Plane getCurrent() {
		return current;
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
	    	System.out.println("cloud.png was not found, clouds will not be displayed");
	    }
	    
	    g.drawImage(img, 50, 300, null);
	    g.drawImage(img, 350, 375, null);
	    g.drawImage(img, 1000, 50, null);
	    g.drawImage(img, 900, 400, null);
	    for (Plane plane : planes) {
	    	plane.draw(g);
	    }
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
	
	private void onClick () {
		 addMouseListener(new MouseAdapter() {
		     @Override
		     public void mousePressed(MouseEvent e) {
		    	 Point p = e.getPoint();
		    	 for (Plane plane : planes) {
		    		 if (plane.contains(p)) {
		    			 current.setActive(false);
		    			 current = plane;
		    			 current.setActive(true);
		    			 refresh();
		    		 }
		    	 }
		     }
		  });
	}
	
}
