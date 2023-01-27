package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static ArrayList<Socket> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(1418);
        while(true){
            System.out.println("Waiting for new client");
            Socket clientSocket = ss.accept();
            System.out.println("new client: "+clientSocket);
            clients.add(clientSocket);
            ServerWorker serverWorker = new ServerWorker(clientSocket);
            new Thread(serverWorker).start();
        }
    }
}





