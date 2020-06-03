package ru.geekbrains;

import javafx.application.Platform;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Network {
    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;

    public Network(String serverAddress, int port, MessageService messageService) throws IOException {
        this.socket = new Socket(serverAddress, port);
        this.in  = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String message = in.readUTF();
                                           }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    public void send(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
