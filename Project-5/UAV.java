
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
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

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
			shape.addPoint(point[0], point[1]);
		}
	}

	public boolean contains(Point p) {
		p.translate((int)(-x * scaleFactor),(int)(-y * scaleFactor));
		System.out.println("translated to: " + p.toString());
		boolean result = shape.getBounds().contains(p);
		p.translate((int)(x * scaleFactor),(int)(y * scaleFactor));
		return result;
	}

	public void translate(int dx, int dy) {
		super.translate(dx, dy);
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.translate(scaleFactor * x, scaleFactor * y);
		int lineHeight = 13;
		String ID = Integer.toString(id);
		int offset = (int) ((((double) ID.length()) / 2.0) * 7);
		String dir = "Dir: " + direction;
		String alt = "Alt: " + getAltitude() + " ft";
		String spd = "Speed: " + getSpeed() + " mph";
		g2d.drawString(ID, -offset, -lineHeight);
		g2d.drawString(dir, 35, 0);
		g2d.drawString(alt, 35, lineHeight);
		g2d.drawString(spd, 35, 2 * lineHeight);

		g2d.rotate(Math.toRadians(direction), 0, 25);
		if (active) {
			Color temp = g.getColor();
			g2d.setColor(Color.RED);
			g2d.fillPolygon(shape);
			g2d.setColor(temp);
		} else {
			g2d.fillPolygon(shape);
		}
	}

	public boolean colliding(ArrayList<Plane> planes) {
		for (Plane p : planes) {
			if (p != this && p.getPosition().distance(x, y) < 25 && Math.abs(altitude - p.getAltitude()) < 150) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
}
