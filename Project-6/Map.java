
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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Map extends JPanel {
	private Plane current;
	private ArrayList<Plane> planes;
	private ArrayList<Obstacle> obstacles;
	private JFrame parent;
	private boolean hasParent;
	private Timer clock;
	private double scaleFactor;
	private Point center;
	private Clip explosion;
	private int score;
	private File explosionInputFile;
	private class animate extends TimerTask {
		@Override
		public void run() {
			ArrayList<Plane> collided = new ArrayList<Plane>();
			for (Plane p : planes) {
				p.tick();
				if (p.colliding(planes)) {
					collided.add(p);
				}
				
				for (Obstacle o : obstacles) {
					if (o.colliding(p.getPosition().x, p.getPosition().y, p.getAltitude())) {
						collided.add(p);
					}
				}
			}
			boolean playSound = false;
			for (Plane p : collided) {
				if(planes.remove(p)){
					playSound = true;
					score += 1500;
				}
			}
			
			if (playSound) {
			    try {
				    explosion = AudioSystem.getClip();
					explosion.open(
						    AudioSystem.getAudioInputStream(
						    		explosionInputFile));
				    explosion.start();
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
					e.printStackTrace();
				}
			}
			score -= 20;
			if(planes.isEmpty()) {
				planes = new ArrayList<Plane>();
				for (int i = 0; i < 10; i++) {
					addRandomPlane();
				}
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
		score = 1000;
		center = new Point(0, 0);
		current = null;
		scaleFactor = 1.0;
		planes = new ArrayList<Plane>();
		for (int i = 0; i < 10; i++) {
			addRandomPlane();
		}
		obstacles = new ArrayList<Obstacle>();
		for (int i = 0; i < 15; i++) {
			addRandomObstacle();
		}
		hasParent = false;
		setBackground(Color.CYAN);
		explosion = null;
		File wd = new File(System.getProperty("user.dir"));
		explosionInputFile = new File(wd, "bomb.wav");
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
			refresh();
		}
	}

	public void startAnimation() {
		if (clock != null) {
			clock.cancel();
			clock = null;
		}
		clock = new Timer();
		clock.scheduleAtFixedRate(new animate(), 0, 1000);
		refresh();
	}
	
	public boolean isAnimating() {
		return clock != null;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(current == null) {
			center = new Point(0, 0);
		} else {
			center = current.getPosition();
		}
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
		g2d.drawString("Score: " + score, 10, 20);
		g2d.drawString("Planes alive: " + planes.size(), 10, 40);
		
		g.translate((int)(scaleFactor*(-center.x)) + this.getWidth()/2, (int)(scaleFactor*(-center.y)) + this.getHeight()/2);
		
		
		for (Obstacle o : obstacles) {
			o.draw(g);
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


	public void removeCurrent() {
		planes.remove(current);
		current = null;
	}

	public void addRandomPlane() {
		int randX = ThreadLocalRandom.current().nextInt(-1000, 1000);
		int randY = ThreadLocalRandom.current().nextInt(-750, 750);
		Plane temp = new UAV(randX, randY);
		temp.setAltitude(ThreadLocalRandom.current().nextInt(0, 65000));
		temp.setDirection(ThreadLocalRandom.current().nextInt(0, 360));
		temp.setID(ThreadLocalRandom.current().nextInt(0, 999999));
		int maxSpeed = ThreadLocalRandom.current().nextInt(250, 650);
		temp.setMaxSpeed(maxSpeed);
		temp.setSpeed(ThreadLocalRandom.current().nextInt(100, maxSpeed));
		planes.add(temp);
	}
	
	public void addRandomObstacle() {
		Obstacle temp = new Mountain(ThreadLocalRandom.current().nextInt(-4000, 4000), ThreadLocalRandom.current().nextInt(-2000, 2000), ThreadLocalRandom.current().nextInt(0, 27500));
		temp.setScaleFactor(scaleFactor);
		obstacles.add(temp);
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
				p.translate((int)(scaleFactor*(center.x)) - getWidth()/2, (int)(scaleFactor*(center.y)) - getHeight()/2);
				System.out.println("Clicked on: " + p.toString());
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
		for (Plane p : planes) {
			p.setScaleFactor(scaleFactor);
		}
		for (Obstacle o : obstacles) {
			o.setScaleFactor(scaleFactor);
		}
	}
}
