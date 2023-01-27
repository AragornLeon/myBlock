 package view;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import model.GameData;

public class GamePanel extends JPanel{
	
	private GameData gameData;
	//设置游戏区边框
	public GamePanel(GameData gameData) {
		this.gameData = gameData;
		setOpaque(false);
		setBounds(15,24,200,360);
	}


	@Override
	protected void paintComponent(Graphics g) {
		
		//super.paintComponent(g);
		for (Point point:gameData.blks.points) {
			g.setColor(gameData.colors[gameData.current]);
			g.fill3DRect((point.x + gameData.x)*20, (point.y + gameData.y)*20, 20, 20,false);
		}
		
		for(int j=19; j>=2; j--) {
			for(int i=0; i<10; i++) {
				if(gameData.blockarea[i][j] != 0) {
					g.setColor(gameData.colors[gameData.blockarea[i][j] - 1]);
					g.fill3DRect((i)*20, (j-2)*20, 20, 20,false);
				}
			}
		}
	}
	
	
	
	
}
