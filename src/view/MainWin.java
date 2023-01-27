package view;

import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.Client;
import controller.Opration;
import model.GameData;

public class MainWin extends JFrame{
	Opration operation;
	GameData gameData;
	GamePanel gamePanel;
	Container mainpane;
	StaticPanel staticPanel;
	ScoreNnext scornxt;
	PlayerPanel playerPanel;

	
	public MainWin(Opration operation, GameData gameData){
		
		this.operation = operation;
		this.gameData = gameData;
		mainpane = getLayeredPane();
		setBounds(50, 50, 360, 600);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置背景
		setback();
		//用画布绘制区域
		staticPanel = new StaticPanel(operation);
		mainpane.add(staticPanel);
		//添加游戏方块
		setGamePanel();
		//添加分数提示
		setScoreNnext();
		//添加分数记录
		playerPanel = new PlayerPanel(gameData);
		mainpane.add(playerPanel);
		mainpane.setComponentZOrder(playerPanel, 0);//0表示置顶
		//设置图层顺序
		//setOrder();
		//获得按键的权利
		setFocusable(true);
	}
	

	

	public ScoreNnext getScornxt() {
		return scornxt;
	}

	//添加分数提示
	private void setScoreNnext() {
		scornxt = new ScoreNnext(gameData);
		mainpane.add(scornxt);
	}
	
	
	
	//设置图层顺序
//	private void setOrder() {
//		mainpane.setComponentZOrder(staticPanel, 1);
//		mainpane.setComponentZOrder(gamePanel, 0);
//		mainpane.setComponentZOrder(scornxt, 0);
//	}
	
	//设置背景
	void setback(){
		ImageIcon imgic = new ImageIcon("img/bg.png");
		JLabel jl = new JLabel(imgic);
		jl.setBounds(0, 0, 360, 600);
		getContentPane().add(jl);
	}
	
	//添加游戏方块
	void setGamePanel() {
		gamePanel = new GamePanel(gameData);
		mainpane.add(gamePanel);
	}
	
	//获取游戏区
	public GamePanel getGamePanel() {
		return gamePanel;
	}
	
	//获取流程控制开关 
	public JButton getStst() {
		
		return staticPanel.stst;
	}

	public void alert(int mode) {	
		//JOptionPane.showMessageDialog(this, string);
		AlertDialog.getInstance(this, gameData, mode).openDialog();
	}
}



