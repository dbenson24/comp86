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
import java.awt.Polygon;

public abstract class Plane {
	
	protected int x, y;
	
	Plane(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	void translate(int dx, int dy) {
		x = x + dx;
		y = y + dy;
	}
	
	abstract void draw(Graphics g);
	
}
