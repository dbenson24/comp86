/**
 * File: Obstacle.java
 * Date: Nov 16, 2015
 * Author: Derek
 * Email: Derek.Benson@tufts.edu
 * Description:
 * This is the abstract class for an obstacle.
 * Provides the methods for interacting with a generic obstacle.
 */

import java.awt.Graphics;
import java.awt.Point;

public abstract class Obstacle {
	
	protected int x, y;
	
	protected double scaleFactor;
	
	/**
	 * 
	 */
	public Obstacle() {
		init();
	}
	
	public Obstacle(int x, int y) {
		init();
		this.x = x;
		this.y = y;
	}
	
	private void init() {
		x = 0;
		y = 0;
		scaleFactor = 1.0;
	}
	
	public void setScaleFactor(double scaleFactor) {
		this.scaleFactor = scaleFactor;
	}
	
	public Point getPosition() {
		return new Point(x, y);
	}
	
	public void translate(int dx, int dy) {
		x = x + dx;
		y = y + dy;
	}
	
	/*
	 * Returns a boolean stating whether the object at the given x, y, and
	 * altitude is about to collide with the obstacle.
	 */
	abstract boolean colliding(int x, int y, int altitude);
	
	abstract void draw(Graphics g);
}
