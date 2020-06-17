package ru.geekbrains;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

import java.io.Closeable;
import java.io.IOException;

public class MessageService implements Closeable {
    private PrimaryController primaryController;
    private TextArea chatTextArea;
    private Network network;
    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;


    public MessageService(PrimaryController primaryController) {
        this.chatTextArea = primaryController.chatTextArea;
        this.primaryController = primaryController;
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

    public void receiveService(String message) {
        if (message.startsWith("/authok")) {
            primaryController.authPanel.setVisible(false);
            primaryController.chatPanel.setVisible(true);
        }
        else if (primaryController.authPanel.isVisible()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Authentication is failed");
            alert.setContentText(message);
            alert.showAndWait();
        }
        else {
            chatTextArea.appendText("Сервер: " + message + System.lineSeparator());
        }
    }

    @Override
    public void close() throws IOException {
        network.close();
    }
}
