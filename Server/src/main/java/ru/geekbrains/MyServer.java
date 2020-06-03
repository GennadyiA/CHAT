package ru.geekbrains;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {
    private final int PORT = 8189;
    private List<ClientHandler> clients = new ArrayList<>();
    private AuthService authService = new AuthService();

    public MyServer() {
        System.out.println("Сервер запущен");
        try (ServerSocket server = new ServerSocket(PORT)) {
            authService.start();
            while (true) {
                System.out.println("Сервер ожидает подключения");
                Socket socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(socket, this);
            }
        } catch (IOException e) {
            System.out.println("Ошибка в работе сервера. Причина: " + e.getMessage());
            e.printStackTrace();
        } finally {
            authService.stop();
        }
    }

    public AuthService getAuthService(){
        return authService;
    }

    public synchronized boolean isNickBusy(String nick) {
        for(ClientHandler client : clients){
            if(client.getClientName().equals(nick)){
                return true;
            }
        }
        return false;
    }

    public void subscribe (ClientHandler clientHandler){
        clients.add(clientHandler);
    }

    public void broadcastMessage(String message){
        for(ClientHandler client : clients){
            client.sendMessage(message);
        }
    }

}
