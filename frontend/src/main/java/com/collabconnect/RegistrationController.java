package com.collabconnect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

public class RegistrationController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    @FXML
    protected void handleRegisterButtonAction(ActionEvent event) {
        String name = nameField.getText();
        String email = emailField.getText();

        if (name.isEmpty() || email.isEmpty() || passwordField.getText().isEmpty()) {
            statusLabel.setText("Please fill in all fields.");
            statusLabel.setTextFill(Color.RED);
        } else {
            statusLabel.setText("Registration Successful!");
            statusLabel.setTextFill(Color.GREEN);
        }
    }

    @FXML
    protected void switchToLogin(ActionEvent event) throws IOException {
        App.setRoot("frontend/login-view");
    }
}