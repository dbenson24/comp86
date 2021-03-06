
/**
 * File: UAV.java
 * Date: Oct 7, 2015
 * Author: Derek
 * Email: Derek.Benson@tufts.edu
 * Description:
 * UAV extends Plane and provides a
 * Plane in the shape of a UAV. Drawing is implemented
 * through a single Polygon. The polygon is also used
 * for translations. 
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class UAV extends Plane {

	private Polygon shape;

	public UAV() {
		super(0, 0);
		makePolygon();
	}

	public UAV(int x, int y) {
		super(x, y);
		makePolygon();
	}

	public UAV(boolean active) {
		super(active);
		makePolygon();
	}

	public UAV(boolean active, int x, int y) {
		super(active, x, y);
		makePolygon();
	}

	private void makePolygon() {
		shape = new Polygon();
		int[][] points = { { 0, 0 }, { -4, 5 }, { -4, 17 }, { -24, 17 }, { -32, 23 }, { -32, 28 }, { -28, 32 },
				{ -4, 32 }, { -4, 48 }, { 8, 54 }, { 11, 50 }, { 9, 46 }, { 4, 42 }, { 4, 32 }, { 28, 32 }, { 32, 28 },
				{ 32, 23 }, { 24, 17 }, { 4, 17 }, { 4, 5 } };

		for (int[] point : points) {
			shape.addPoint(point[0] + x, point[1] + y);
		}
	}

	public boolean contains(Point p) {
		return shape.contains(p);
	}

	public void translate(int dx, int dy) {
		x = x + dx;
		y = y + dy;
		shape.translate(dx, dy);
	}

	public void draw(Graphics g) {
		if (active) {
			Color temp = g.getColor();
			g.setColor(Color.RED);
			g.fillPolygon(shape);
			g.setColor(temp);
		} else {
			g.fillPolygon(shape);
		}

		int lineHeight = 13;
		String ID = Integer.toString(id);
		int offset = (int)((((double)ID.length()) / 2.0)*7);
		String dir = "Dir: " + (int)(359*((double)direction)/100);
		String alt = "Alt: " + (int)(65000*((double)altitude)/100) + " ft";
		String spd = "Speed: " + (int)(650*((double)speed)/100) + " mph";
		
		g.drawString(ID, x - offset, y - lineHeight);
		g.drawString(dir, x + 35, y);
		g.drawString(alt, x + 35, y + lineHeight);
		g.drawString(spd, x + 35, y + 2 * lineHeight);

	}
}
