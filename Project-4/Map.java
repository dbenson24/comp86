
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
	private TimerTask animate = new TimerTask() {
		@Override
		public void run() {
			for (Plane p : planes) {
				p.tick();
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
		current = null;
		planes = new ArrayList<Plane>();
		for (int i = 0; i < 1; i++) {
			int randX = ThreadLocalRandom.current().nextInt(0, 1366);
			int randY = ThreadLocalRandom.current().nextInt(0, 768);
			Plane temp = new UAV(randX, randY);
			temp.setAltitude(ThreadLocalRandom.current().nextInt(0, 100));
			temp.setSpeed(ThreadLocalRandom.current().nextInt(0, 100));
			temp.setDirection(ThreadLocalRandom.current().nextInt(0, 100));
			temp.setID(ThreadLocalRandom.current().nextInt(0, 999999));
			temp.setMaxSpeed(ThreadLocalRandom.current().nextInt(0, 650));
			planes.add(temp);
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
		clock.scheduleAtFixedRate(animate, 0, 1000);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		BufferedImage img = null;
		try {
			System.out.println();
			File wd = new File(System.getProperty("user.dir"));
			img = ImageIO.read(new File(wd, "cloud.png"));
			g.drawImage(img, 50, 300, null);
			g.drawImage(img, 350, 375, null);
			g.drawImage(img, 1000, 50, null);
			g.drawImage(img, 900, 400, null);
		} catch (IOException e) {
			System.out.println("cloud.png was not found, clouds will not be displayed");
		}
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

}
