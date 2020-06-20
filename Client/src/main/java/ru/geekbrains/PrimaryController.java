package ru.geekbrains;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.Closeable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {

    public @FXML   TextArea chatTextArea;
    public @FXML   TextField messageText;
    public @FXML   Button sendMessageButton;
    public @FXML   VBox chatPanel;
    public @FXML   TextField loginField;
    public @FXML   PasswordField passField;
    public @FXML   HBox authPanel;
    private MessageService messageService;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.messageService = new MessageService(this);
        try {
            Network network = new Network("localhost", 8189, messageService);
            messageService.setNetwork(network);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Ошибка соединения с сервером");
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
        chatTextArea.appendText("Я: " + message + System.lineSeparator());
        messageService.sendMessage(message);
        messageText.clear();
    }


    public void shutdown() {
        try {
            messageService.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void sendAuth(ActionEvent actionEvent) {
        String login = loginField.getText();
        String password = passField.getText();
        messageService.sendMessage(String.format("/auth %s %s", login, password));
    }


}