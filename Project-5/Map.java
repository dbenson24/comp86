
/**
 * File: Map.java
 * Date: Oct 7, 2015
 * Author: Derek
 * Email: Derek.Benson@tufts.edu
 * Description:
 * This file implements the Map object.
 * A Map extends JPanel and draws onto itself
 * a number of clouds as well as 8 randomly positioned
 * UAVs for the user to pilot.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Map extends JPanel {
	private Plane current;
	private ArrayList<Plane> planes;
	private JFrame parent;
	private boolean hasParent;
	private Timer clock;
	private double scaleFactor;
	private BufferedImage img;
	private class animate extends TimerTask {
		@Override
		public void run() {
			ArrayList<Plane> collided = new ArrayList<Plane>();
			for (Plane p : planes) {
				p.tick();
				if (p.colliding(planes)) {
					collided.add(p);
				}
			}
			
			for (Plane p : collided) {
				planes.remove(p);
			}
			refresh();
		}
	};


	public Map() {
		init();
	}

	public Map(JFrame parent) {
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
		img = null;
		try {
			File wd = new File(System.getProperty("user.dir"));
			img = ImageIO.read(new File(wd, "cloud.png"));
		} catch (IOException e) {
			System.out.println("cloud.png was not found, clouds will not be displayed");
		}
		current = null;
		scaleFactor = 1.0;
		planes = new ArrayList<Plane>();
		for (int i = 0; i < 1; i++) {
			addRandomPlane();
		}
		hasParent = false;
		setBackground(Color.CYAN);
		onClick();
		startAnimation();
	}

	public Plane getCurrent() {
		return current;
	}

	public void pauseAnimation() {
		if(clock != null) {
			clock.cancel();
			clock = null;
		}
	}

	public void startAnimation() {
		if (clock != null) {
			clock.cancel();
			clock = null;
		}
		clock = new Timer();
		clock.scheduleAtFixedRate(new animate(), 0, 1000);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, (int)(scaleFactor * 50), (int)(scaleFactor * 300), null);
		g.drawImage(img, (int)(scaleFactor * 350), (int)(scaleFactor * 375), null);
		g.drawImage(img, (int)(scaleFactor * 1000), (int)(scaleFactor * 50), null);
		g.drawImage(img, (int)(scaleFactor * 900), (int)(scaleFactor * 400), null);
	
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


	public void removeCurrent() {
		planes.remove(current);
		current = null;
	}

	public void addRandomPlane() {
		int randX = ThreadLocalRandom.current().nextInt(0, 1366);
		int randY = ThreadLocalRandom.current().nextInt(0, 768);
		Plane temp = new UAV(randX, randY);
		temp.setAltitude(ThreadLocalRandom.current().nextInt(0, 65000));
		temp.setDirection(ThreadLocalRandom.current().nextInt(0, 360));
		temp.setID(ThreadLocalRandom.current().nextInt(0, 999999));
		int maxSpeed = ThreadLocalRandom.current().nextInt(250, 650);
		temp.setMaxSpeed(maxSpeed);
		temp.setSpeed(ThreadLocalRandom.current().nextInt(0, maxSpeed));
		planes.add(temp);
	}

	public void addPlane(int x, int y, int altitude, int speed, int maxSpeed, int direction) {
		Plane temp = new UAV(x, y);
		temp.setAltitude(altitude);
		temp.setDirection(direction);
		temp.setID(ThreadLocalRandom.current().nextInt(0, 999999));
		temp.setMaxSpeed(maxSpeed);
		temp.setSpeed(speed);
		planes.add(temp);
	}

	private void onClick() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point p = e.getPoint();
				for (Plane plane : planes) {
					if (plane.contains(p)) {
						if (current != null) {
							current.setActive(false);
						}
						current = plane;
						current.setActive(true);
						refresh();
					}
				}
			}
		});
	}
	
	public double getScaleFactor() {
		return scaleFactor;
	}
	
	public void setScaleFactor(double scaleFactor) {
		this.scaleFactor = scaleFactor;
		System.out.println("Set scalefactor to: " + this.scaleFactor + " with: " + scaleFactor);
		for (Plane p : planes) {
			p.setScaleFactor(scaleFactor);
		}
	}
}
