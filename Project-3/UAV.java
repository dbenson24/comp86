/**
 * File: UAV.java
 * Date: Oct 7, 2015
 * Author: Derek
 * Email: Derek.Benson@tufts.edu
 * Description:
 * UAV extends Polygon and provides a
 * polygon in the shape of a UAV.
 */

import java.awt.Graphics;
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
	
	private void makePolygon() {
		shape = new Polygon();
		int[][] points = {{0,0},{-20,25},{-20,85},{-120,85},{-160,115},{-160,140},{-140,160},{-20,160},{-20,240},{40,270},{55,250},{45,230},{20,210},{20,160},{140,160},{160,140},{160,115},{120,85},{20,85},{20,25}};
		
		for (int[] point : points){
			shape.addPoint(point[0] + x, point[1] + y);
		}
	}
	
	public void translate(int dx, int dy) {
		shape.translate(dx, dy);
	}
	
	public void draw(Graphics g){
		g.fillPolygon(shape);
	}
}
