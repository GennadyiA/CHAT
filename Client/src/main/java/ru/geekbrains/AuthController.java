package ru.geekbrains;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AuthController {

    @FXML
    private void switchToSecondary() throws IOException {
        ClientApp.setRoot("secondary");
    }

    public void sendAuth(ActionEvent actionEvent) {
    }
}
