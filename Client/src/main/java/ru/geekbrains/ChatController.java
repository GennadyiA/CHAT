package ru.geekbrains;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatController implements Initializable {

    public @FXML    TextArea chatTextArea;
    public @FXML    TextField messageText;
    public @FXML    Button sendMessageButton;
    public @FXML    VBox chatPanel;
    private MessageService messageService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.messageService = new MessageService(this);
        try {
            Network network = new Network("localhost", 8189, messageService);
            messageService.setNetwork(network);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Server connection error");
            alert.setContentText(e.getMessage());
            alert.show();
        }

    }

    @FXML
    public void sendText(ActionEvent actionEvent) {
        sendMessage();
    }

    @FXML
    public void sendMessage(ActionEvent actionEvent) {
        sendMessage();
    }

    private void sendMessage() {
        String message = messageText.getText();
        messageService.sendMessage(message);
        messageText.clear();
    }
}