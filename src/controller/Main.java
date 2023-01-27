package controller;


import java.io.IOException;

import model.GameData;
import model.PlayerData;
import view.AlertDialog;
/**
 * 控制层与层之间的关系
 */
import view.MainWin;
import view.Music;

public class Main {
	
	public static void main(String[] args) throws IOException{
		Client client = new Client();
		Start.startMusic();
		Start.startGame();
	}
	
	
}


class GoDown extends Thread{
	
	private GameData gameData;
	private MainWin mwn;
	
	GoDown(GameData gameData,  MainWin mwn) {
		this.gameData = gameData;
		this.mwn = mwn;
	}

	@Override
	public void run() {
		while(true) {
			try {
				if(gameData.state == 1) {
					if(gameData.move(false, 1)) {
						mwn.getScornxt().repaint();
					};
					mwn.getGamePanel().repaint();
						sleep(666); 
				}else if(gameData.state == 3){
					gameData.init();
					System.out.println("gameover!");
					mwn.alert(AlertDialog.OVER);
					mwn.getStst().setText(gameData.sttxt[gameData.state]);
					gameData.state = 30;
				}else {
					sleep(150);
				}
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}