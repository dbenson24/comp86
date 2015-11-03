
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

public abstract class Plane {

	protected int x, y, direction, speed, altitude, id, maxSpeed;

	protected boolean active;

	Plane(int x, int y) {
		active = false;
		this.x = x;
		this.y = y;
		direction = 0;
		speed = 0;
		altitude = 0;
		id = 0;
		maxSpeed = 0;
	}

	Plane(boolean active) {
		this.active = active;
		x = 0;
		y = 0;
		direction = 0;
		speed = 0;
		altitude = 0;
		id = 0;
		maxSpeed = 0;
	}

	Plane(boolean active, int x, int y) {
		this.active = active;
		this.x = x;
		this.y = y;
		direction = 0;
		speed = 0;
		altitude = 0;
		id = 0;
		maxSpeed = 0;
	}

	public void translate(int dx, int dy) {
		x = x + dx;
		y = y + dy;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getSpeed() {
		return speed;
	}

	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}

	public int getAltitude() {
		return altitude;
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
		this.maxSpeed = maxSpeed;
	}
	
	public int getMaxSpeed() {
		return maxSpeed;
	}
	
	public void tick() {
		double radians = Math.toRadians((double)direction / 100.0 * 359.0);
		int dy = (int)((double)speed / 100 * maxSpeed * Math.cos(radians) / 20.0);
		int dx = (int)((double)speed / 100 * maxSpeed * Math.sin(radians) / 20.0);
		System.out.println("dx: " + dx + " dy: " + dy + " speed: " + speed + " direction: " + direction);
		this.translate(dx, -dy);
	}
	
	abstract void draw(Graphics g);

	abstract boolean contains(Point p);
}
