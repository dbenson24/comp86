
/**
 * File: Plane.java
 * Date: Oct 12, 2015
 * Author: Derek
 * Email: Derek.Benson@tufts.edu
 * Description:
 * TODO
 *
 */

import java.awt.Graphics;
import java.awt.Point;

public abstract class Plane {

	protected int x, y, direction, speed, altitude, id;

	protected boolean active;

	Plane(int x, int y) {
		active = false;
		this.x = x;
		this.y = y;
		direction = 0;
		speed = 0;
		altitude = 0;
		id = 0;
	}

	Plane(boolean active) {
		this.active = active;
		x = 0;
		y = 0;
		direction = 0;
		speed = 0;
		altitude = 0;
		id = 0;
	}

	Plane(boolean active, int x, int y) {
		this.active = active;
		this.x = x;
		this.y = y;
		direction = 0;
		speed = 0;
		altitude = 0;
		id = 0;
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
	
	abstract void draw(Graphics g);

	abstract boolean contains(Point p);
}