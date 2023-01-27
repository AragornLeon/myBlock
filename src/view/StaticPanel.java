package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.Opration;

public class StaticPanel extends JPanel{
	//设置按钮
	JButton left;
	JButton righ;
	JButton rota;
	JButton down;
	JButton stst;
	JButton logi;
	JButton set;
	//静态界面
	StaticPanel(Opration opration) {
		left = opration.left;
		righ = opration.righ;
		rota = opration.rota;
		down = opration.down;
		stst = opration.stst;//start&stop
		logi = opration.logi;
		set = opration.set;
		setBounds(0, 0, 360, 600);
		setLayout(null);
		setOpaque(false);
		setstst();
		left.setBounds(233, 255, 45, 45);
		righ.setBounds(278, 255, 45, 45);
		rota.setBounds(278, 300, 45, 45);
		down.setBounds(233, 300, 45, 45);
		stst.setBounds(233, 350, 90, 45);
		logi.setBounds(240, 514, 48, 48);
		set.setBounds(290, 514, 48, 48);
		add(left); 
		add(righ); 
		add(rota);
		add(down);
		add(stst);
		add(logi);
		add(set);
	}
	
	//设置开始停止按钮透明
	private void setstst() {
		
		stst.setContentAreaFilled(false);
		stst.setFocusPainted(false);
		stst.setFocusable(false);
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.setColor(new Color(0,0,0,30));
		//游戏主屏
		g.fillRect(15, 25, 200, 360);
		//排名区域
		g.fillRect(15, 405, 200, 130);
		//右侧排版
		g.fillRect(233, 20, 105, 400);
		
		g.setColor(new Color(2,2,2,30));
		//得分区
		g.fillRect(233, 30, 90, 70);
		//下一个提示
		g.fillRect(233, 105, 90, 140);
		//操作区
		g.fillRect(233, 255, 90, 90);
		
		//边框绘制
		g.setColor(Color.pink);
		((Graphics2D)g).setStroke(new BasicStroke(3L));
		g.drawRect(13, 23, 204, 364);
		g.drawRect(13, 403, 204, 134);
		
		g.setColor(Color.DARK_GRAY);
		g.setFont(new Font("黑体", Font.PLAIN, 23));
		g.drawString("分数", 240, 52);
		g.drawString("下一个", 235, 127);
		g.drawString("得分榜", 22, 427);
		
		g.setColor(Color.RED);
		g.setFont(new Font("黑体", Font.BOLD, 14));
		g.drawString("对手分数：", 232, 444);
		
	}
	
	
}
