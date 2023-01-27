 package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.GameData;
import view.AlertDialog;
import view.DiyButton;
import view.MainWin;

public class Opration {
	private MainWin mnw;
	private GameData  gameData;
	public JButton stst;
	public OpButton left;
	public OpButton righ;
	public OpButton rota;
	public OpButton down;
	public DiyButton logi;
	public DiyButton set ;
	//对按钮进行美化和事件响应
	abstract class OpButton extends DiyButton{

		public OpButton(ImageIcon btic) {
			super(btic);
		}

		@Override
		public void clicked() {
			if(gameData.state == 1) {
				listenClick();
			}
			
		}

		protected abstract void listenClick();
		
		
	}
	
	
	public Opration(){
		left = new OpButton(new ImageIcon("img/left.png")) {
			
			@Override
			public void listenClick() {
				gameData.move(true, -1);
				mnw.getGamePanel().repaint();
			}
			
		};
		
		righ = new OpButton(new ImageIcon("img/righ.png")) {

			@Override
			public void listenClick() {
				gameData.move(true, 1);
				mnw.getGamePanel().repaint();
			}
		
		};
		
		rota = new OpButton(new ImageIcon("img/rota.png")) {

			@Override
			public void listenClick() {
				gameData.rota();
				mnw.getGamePanel().repaint();
			}
			
		};
		
		down = new OpButton(new ImageIcon("img/down.png")) {

			@Override
			public void listenClick() {
				if(gameData.move(false, 1)) {
					mnw.getScornxt().repaint();
				};
				mnw.getGamePanel().repaint();
			}
			
		};
		
		stst = new JButton("Start");
		stst.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(gameData.state == 1) {
					gameData.state = 2;
				}else {
					gameData.state = 1;
				}
				stst.setText(gameData.sttxt[gameData.state]);	
			}
		});
		
		logi = new DiyButton(new ImageIcon("img/login.png")) {

			@Override
			public void clicked() {
				AlertDialog.getInstance(mnw, gameData, AlertDialog.LOGIN).openDialog();
			}
			
		};
		
		set = new DiyButton(new ImageIcon("img/set.png")) {

			@Override
			public void clicked() {
				AlertDialog.getInstance(mnw, gameData, AlertDialog.SET).openDialog();
			}
			
		};
	}
	
	public void setWin(MainWin mnw) {
		this.mnw = mnw;
		this.mnw.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {		
			}
			
			@Override
			public void keyReleased(KeyEvent e) {	
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					System.out.println("down");
					down.clicked();
				}else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					System.out.println("left");
					left.clicked();
				}else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					System.out.println("right");
					righ.clicked();
				}else if (e.getKeyCode() == KeyEvent.VK_UP) {
					System.out.println("rota");
					rota.clicked();
				}
				
			}
		});
	}

	public void setData(GameData gameData) {
		
		this.gameData = gameData;
	}	
}







