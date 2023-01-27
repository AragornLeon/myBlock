package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import model.GameData;

public class PlayerPanel extends JPanel{
	GameData gamedata;
	PlayerPanel(GameData gamedata){
		this.gamedata = gamedata;
		setOpaque(false);
		setBounds(15, 436, 200, 130);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("Consolas",Font.BOLD,18));
		g.setColor(new Color(15,100,100));
		
		int y = 20;
		for (String name : gamedata.playerData.getName()) {
			g.drawString(name, 30, y);
			y += 20;
		}
		
		y = 20;
		for (int score : gamedata.playerData.getScore()) {
			g.drawString("" + score, 120, y);
			y += 20;
		}
	}
}
