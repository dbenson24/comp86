
/**
 * File: Plane.java
 * Date: Oct 12, 2015
 * Author: Derek
 * Email: Derek.Benson@tufts.edu
 * Description:
 * Plane is an abstract class that represents a drawable plane.
 * Protected variables include it's x and y location in pixels on the canvas,
 * it's current direction, speed, and altitude as an integer out of 100, 
 * and an integer id which is supposed to be able to uniquely identify the plane.
 */

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public abstract class Plane {

	protected int x, y, direction, id, speed, altitude, maxSpeed, maxAltitude;
	
	protected double scaleFactor;
	
	protected boolean active;

	Plane(int x, int y) {
		init();
		this.x = x;
		this.y = y;
	}

	Plane(boolean active) {
		init();
		this.active = active;
	}
	
	Plane(boolean active, int x, int y) {
		init();
		this.active = active;
		this.x = x;
		this.y = y;
	}
	
	private void init() {
		x = 0;
		y = 0;
		active = false;
		direction = 0;
		speed = 0;
		altitude = 0;
		id = 0;
		maxSpeed = 0;
		maxAltitude = 65000;
		scaleFactor = 1;
	}

	public Point getPosition() {
		return new Point(x, y);
	}
	
	public void translate(int dx, int dy) {
		x = x + dx;
		y = y + dy;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setSpeed(int speed) {
		int s = (int)Math.round(speed / 20.0);
		if (s < maxSpeed) {
			this.speed = s;
		} else {
			this.speed = maxSpeed;
		}
	}

	public int getSpeed() {
		return 20 * speed;
	}
	
	
	public void setAltitude(int altitude) {
		if (altitude < maxAltitude) {
			this.altitude = altitude;
		} else {
			this.altitude = maxAltitude;
		}
	}

	public int getAltitude() {
		return altitude;
	}
	
	public int getMaxAltitude() {
		return maxAltitude;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getDirection() {
		return direction;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
	
	public void setMaxSpeed(int maxSpeed) {
		int s = (int)Math.round(maxSpeed / 20.0);
		this.maxSpeed = s;
		if (speed > s) {
			speed = s;
		}
	}
	
	public int getMaxSpeed() {
		return 20 * maxSpeed;
	}
	
	public void setScaleFactor(double scaleFactor) {
		this.scaleFactor = scaleFactor;
	}
	
	public void tick() {
		double radians = Math.toRadians(direction);
		int dy = (int) Math.round((double)speed * Math.cos(radians));
		int dx = (int) Math.round((double)speed * Math.sin(radians));
		this.translate(dx, -dy);
	}
	
	abstract boolean colliding(ArrayList<Plane> planes);
	
	abstract void draw(Graphics g);

	abstract boolean contains(Point p);
}
