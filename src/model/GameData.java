package model;

import java.awt.Color;
import java.awt.Point;
import java.util.Iterator;
import java.util.Random;

import controller.Client;

public class GameData {
	
	public Blocks blks;
	//绘制七种方块
	public Blocks[] BLOCKS =  new Blocks[] {
		new Blocks(new int[] {-1,0,1,1}, new int[] {0,0,0,1}),
		new Blocks(new int[] {-1,0,1,2}, new int[] {0,0,0,0}),
		new Blocks(new int[] {-1,-1,0,1}, new int[] {0,1,0,0}),
		new Blocks(new int[] {-1,0,0,1}, new int[] {0,0,1,1}),
		new Blocks(new int[] {0,0,1,1}, new int[] {0,1,0,1}),
		new Blocks(new int[] {-1,0,0,1}, new int[] {1,0,1,0}),
		new Blocks(new int[] {-1,0,0,1}, new int[] {0,0,1,0})
	};
	//给七种方块上色
	public Color[] colors = new Color[] {
		new Color(255,50,0),
		new Color(0,255,0),
		new Color(0,120,255),
		new Color(255,255,0),
		new Color(255,0,255),
		new Color(0,255,255),
		new Color(153,0,255)
	};
	
	//按钮信息
	public String[] sttxt = new String[] {"Start","Stop","Go on","ReStart"};
	//偏移量
	public int x;
	public int y;
	//存放方块的数组
	public int[][] blockarea;
	//存放消除行数的数组
	int[] deleteNum;
	//随机因子
	Random random;
	//下一个方块编号
	public int next;
	//当前的方块
	public int current;
	//得分
	public int score;
	//游戏状态
	public int state;
	//游戏记录
	public PlayerData playerData;
	public String name;
	public GameData() {
		init();
		playerData = new PlayerData();
		name = "";
	}
	
	public void init() {
		blockarea = new int[10][20];
		random = new Random();
		next = random.nextInt(7);
		initBlks();
	}
	
	//移动方法
	public boolean move(boolean isX, int step) {
		boolean isdele = false;
		
		if (isX) {
			for (Point point: blks.points) {
				if(point.x+x+step < 0 || point.x+x+step > 9
						|| blockarea[point.x+x+step][point.y+y+2] !=0) {
					return false;
				}
			}
			x += step;
			
			
		} else {
				for (Point point: blks.points) {
					if(point.y+y+step > 17 || blockarea[point.x+x][point.y+y+2+step] !=0) {
						save();
						isdele = deleTest();
						if(isdele) {
							dele();
						}
						if(isdead()) {
							
							state = 3;
						}
						initBlks();
						return true;
					}
				}
				y += step;
			
		}
		return isdele;
	}
	
	//重置下落的方块
	private void initBlks() {
		x = 4;
		y = 0;
		deleteNum = new int[20];
		blks = new Blocks(BLOCKS[next]);
		current = next;
		next = random.nextInt(7);
	}

	//旋转方法：顺时针旋转，x = -y, y = x   	
	public void rota() {
		
		for (Point point: blks.points) {
			int x1 = -point.y + x;
			int y1 =  point.x +y;
			if(x1>9 || x1<0 || y1>17 || y1<-2) {
				return;
			}
			if (blockarea[x1][y1+2]!=0) {
				return;
			}
			if (current == 4) {
				return;
			}
		}
		
		for (Point point: blks.points) {
			int temp = point.x;
			point.x = -point.y;
			point.y = temp;
		}
			
	}
	
	//保存方块组
	public void save() {
		for (Point point: blks.points) {
			blockarea[point.x + x][point.y + y + 2] = current + 1; 
		}
	}
	
	//检测是否满行
	boolean deleTest() {
		boolean isdele = false;
		boolean isVacant;
		for(int j=19; j>=2; j--) {
			isVacant = false;
			for(int i=0; i<10; i++) {
				if(blockarea[i][j]==0) {
					isVacant = true;
					break;
				}
			}
			if(!isVacant) {
				isdele = true;
				deleteNum[j-1] = deleteNum[j] + 1;
			}else {
				deleteNum[j-1] = deleteNum[j];
			}
		
		}                     
		return isdele;
	}
	
	//消除满行
	void dele() {
		for(int j=19; j>=2; j--) {
			for(int i=0; i<10; i++) {
				blockarea[i][j+deleteNum[j]] = blockarea[i][j];
				}
		}
		score += deleteNum[2]*10;
		//System.out.println(score);
		Client.sendScore(score);
		
	}
	
	//死亡判断
	boolean isdead() {
		for(int i=0; i<10; i++) {
			if(blockarea[i][2]!=0) 
				return true;
		}
		return false;
	}
	
	
	public String getScore() {
		return "" + score;
	}
	
	
	
	
}
