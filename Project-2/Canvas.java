import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Canvas extends JPanel {
	private UAV uav;
	private JFrame parent;
	private boolean hasParent;
	
	public Canvas() {
		uav = new UAV();
		hasParent = false;
		setBackground(Color.CYAN);
	}

	public Canvas(LayoutManager arg0) {
		super(arg0);
		uav = new UAV();
		hasParent = false;
	}

	public Canvas(boolean arg0) {
		super(arg0);
		uav = new UAV();
		hasParent = false;
	}

	public Canvas(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		uav = new UAV();
		hasParent = false;
	}
	
	public UAV getUAV() {
		return uav;
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    BufferedImage img = null;
	    try {
	    	File test = new File("E:\\Workspace\\comp86\\Project-2\\cloud.png");
	    	System.out.println(test.exists());
	    	System.out.println(test);
	        img = ImageIO.read(new File("E:\\Workspace\\comp86\\Project-2\\cloud.png"));
	    } catch (IOException e) {
	    	System.out.println("Ioexception");
	    }
	    g.drawImage(img, 250, 250, null);
		g.fillPolygon(uav);
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
}
