package ru.geekbrains;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class ClientHandler {

    private Socket socket;
    private MyServer myServer;
    public String clientName;
    private DataInputStream in;
    private DataOutputStream out;

    public ClientHandler(Socket socket, MyServer myServer) {
        try {
            this.socket = socket;
            this.myServer = myServer;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(() ->{
                try {
                    authentication();
                    readMessages();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void authentication() throws IOException{
        while(true){
        String clientMessage = in.readUTF();
        if(clientMessage.startsWith("/auth")) {
            String[] loginAndPass = clientMessage.split("\\s+");
            String login = loginAndPass[1];
            String pass = loginAndPass[2];
            String nick = myServer.getAuthService().getNickByLoginPass(login, pass);
            if (nick == null) {
                sendMessage("Неверный логин/пароль");
                continue;
            }
            if (myServer.isNickBusy(nick)) {
                sendMessage("Учетная запись уже используется");
                continue;
            }

            sendMessage("/authok " + nick);
            clientName = nick;
            myServer.subscribe(this);
            myServer.broadcastMessage(clientName + " зашел в чат");
            break;
        }
        }
    }

    private void readMessages() throws IOException{
        while (true){
            String clientMessage = in.readUTF();
                if(clientMessage.startsWith("/w")){
                String[] parts = clientMessage.split("\\s+");
                if(parts.length < 3){
                    sendMessage("Неизвестная команда" + clientMessage);
                    continue;
                }
                String receivedLogin = parts[1];
                String message = parts[2];

                myServer.sendPrivateMessage(receivedLogin, clientName + " : " + message);
            }
                else{
                    myServer.broadcastMessage(clientName + ": " + clientMessage);}
            }

    }
    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            System.err.println("Ошибка отправки сообщения пользователю " + clientName + " : " + message);
            e.printStackTrace();
        }
    }

    public String getClientName(){
        return clientName;
    }
}
