import java.awt.Polygon;

public class UAV extends Polygon {

	public UAV() {
		int[][] points = {{160,0},{140,25},{140,85},{40,85},
				{0,115},{0,140},{20,160},{140,160},{140,240},
				{200,270},{215,250},{205,230},{180,210},{180,160},
				{300,160},{320,140},{320,115},{280,85},{180,85},{180,25}};
		for (int[] point : points){
			this.addPoint(point[0], point[1]);
		}
	}
}
