package ru.geekbrains;

import javafx.scene.control.TextArea;

import java.io.IOException;

public class MessageService {
    private ChatController chatController;
    private TextArea chatTextArea;
    private Network network;
    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;


    public MessageService(ChatController chatController) {
        this.chatTextArea = chatController.chatTextArea;
        this.chatController = chatController;
        startConnectionToServer();
    }

    private void startConnectionToServer() {
        try {
            this.network = new Network(SERVER_ADDR, SERVER_PORT, this);
        } catch (IOException e) {
        }
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public void sendMessage(String message) {

        network.send(message);
    }
}
