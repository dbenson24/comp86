import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.color.*;

/**
 * File: Mountain.java
 * Date: Nov 16, 2015
 * Author: Derek
 * Email: Derek.Benson@tufts.edu
 * Description:
 * TODO
 *
 */

public class Mountain extends Obstacle {

	private Polygon shape;
	
	private int height;
	
	/**
	 * 
	 */
	public Mountain() {
		init();
	}

	/**
	 * @param x
	 * @param y
	 */
	public Mountain(int x, int y, int height) {
		super(x, y);
		init();
		this.height = height;
	}
	
	private void init() {
		height = 0;
		shape = new Polygon();
		int[][] points = { { 0, 50 }, { 20, 20 }, { 33, 33 }, { 45, 0 }, { 50, 15 }, { 65, 10 }, { 75, 30}, { 85, 20 }, { 100, 50 }};
		for (int[] point : points) {
			shape.addPoint(point[0], point[1]);
		}
	}
	
	
	@Override
	boolean colliding(int x, int y, int altitude) {
		Point p = new Point(x, y);
		p.translate((int)(-this.x * scaleFactor),(int)(-this.y * scaleFactor));
		return shape.getBounds().contains(p) && altitude <= height;
	}

	@Override
	void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.translate(scaleFactor * x, scaleFactor * y);
		g2d.setColor(Color.BLACK);
		g2d.scale(scaleFactor, scaleFactor);
		g2d.draw(shape);
		g2d.drawString("Elevation: " + height + " ft", 0, -13);
	}

}
