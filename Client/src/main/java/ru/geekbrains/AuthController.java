package ru.geekbrains;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AuthController {
    public @FXML    TextField loginField;
    public @FXML    PasswordField passField;
    private MessageService messageService;

    public static void setVisible(boolean b) {
    }



    @FXML
    private void switchToSecondary() throws IOException {
        ClientApp.setRoot("secondary");
    }

    @FXML
    public void sendAuth(ActionEvent actionEvent) {
        String login = loginField.getText();
        String pass = passField.getText();
        messageService.sendMessage(String.format("/auth %s %s", login, pass));
        loginField.clear();
        passField.clear();
    }
}
