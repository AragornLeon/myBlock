package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerWorker implements Runnable{
    Socket socket;
    BufferedReader input;
    PrintWriter output;

    public ServerWorker(Socket socket) throws IOException {
        this.socket = socket;
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        String num = null;
        try {
            while (true) {
                if((num = input.readLine()) == null) break;
                for (Socket client : Server.clients) {
                    if (client == socket) continue;
                    new PrintWriter(client.getOutputStream(), true).println(num);
                }
            }
        } catch (IOException e){
           
        }
    }
}
