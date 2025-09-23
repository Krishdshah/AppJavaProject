package com.collabconnect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField userIdField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    @FXML
    protected void handleLoginButtonAction(ActionEvent event) {
        String userId = userIdField.getText();
        String password = passwordField.getText();

        if (userId.equals("test") && password.equals("collabconnect")) {
            statusLabel.setText("Login Successful!");
            statusLabel.setTextFill(Color.GREEN);
            // In a real app, you would navigate to the main dashboard here.
        } else {
            statusLabel.setText("Invalid User ID or Password.");
            statusLabel.setTextFill(Color.RED);
        }
    }

    @FXML
    protected void switchToRegistration(ActionEvent event) throws IOException {
        App.setRoot("frontend/registration-view");
    }
}