package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import model.GameData;
import view.MainWin;

public class Client {
    static Socket socket;
    static BufferedReader input;
    static PrintWriter output;
    String score = null;
    public Client() throws IOException {
        socket = new Socket("localhost", 1418);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);

        new Thread(new Runnable() {
            @Override
            public void run() {
            	
                try {
                    while (true) {
                        if ((score = input.readLine()) == null) break;
                        System.out.println("对手分数为："+score);
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void sendScore(int score){
        String string = "" + score;
        output.println(string);
    }
//    public String setScore() {
//		return "" + score;
//	}
}
