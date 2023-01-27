package controller;

import model.GameData;
import model.PlayerData;
import view.MainWin;
import view.Music;

public class Start {
	
	public static void startGame() {
		//实例化操作
		Opration operation = new Opration();
		//加载数据
		GameData gameData = new GameData();
		//将数据和窗口关联
		MainWin mnw = new MainWin(operation,gameData);
		//将窗口和操作区关联
		operation.setWin(mnw);
		//将数据和操作区关联
		operation.setData(gameData);
		//启动自动向下线程
		new GoDown(gameData, mnw).start();
		mnw.setVisible(true);	
		PlayerData pd = new PlayerData();
	}

	public static void startMusic() {
		
		Music audioPlayWave = new Music("seventh.wav");//开音乐 
		audioPlayWave.start();
		//@SuppressWarnings("unused")
		int musicOpenLab = 1;
	}



}
