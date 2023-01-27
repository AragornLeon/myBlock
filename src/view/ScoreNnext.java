package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import controller.Client;
import model.GameData;

public class ScoreNnext extends JPanel{
	
	private GameData gameData;
	int[] offset = new int[] {0,-10,0,0,-10,0,0};
	
	public ScoreNnext(GameData gameData) {
		this.gameData = gameData;
		setOpaque(false);
		setBounds(233,30,90,573);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setFont(new Font("黑体", Font.PLAIN, 23));
		g.drawString(gameData.getScore(),10,60);
		g.setFont(new Font("黑体", Font.BOLD, 18));
		g.setColor(new Color(250,0,0));
		g.drawString("0",30,437);
		for (Point point:gameData.BLOCKS[gameData.next].points) {
			g.setColor(gameData.colors[gameData.next]);
			g.fill3DRect((point.x)*20 + 35 + offset[gameData.next], (point.y)*20 + 150, 20, 20,false);
		}

	}
	
}

