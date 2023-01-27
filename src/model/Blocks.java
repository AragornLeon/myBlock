package model;

import java.awt.Point;

public class Blocks {
	//用数组存储方块格子的坐标
	public Point[] points;
	
	public Blocks(int[] x, int[] y ) {
		points = new Point[4];
		for(int i=0; i<4; i++) {
			points[i] = new Point(x[i], y[i]);
		}
	}
	//对方块进行拷贝，不直接改变静态的方块，以免旋转时其相对位置变化
	public Blocks(Blocks blks){
		points = new Point[4];
		for(int i=0; i<4; i++) {
			points[i] = new Point(blks.points[i].x, blks.points[i].y);
		}
	}
}
