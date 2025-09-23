package com.collabconnect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class DashboardController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Button logoutButton;

    @FXML
    protected void handleLogoutButton(ActionEvent event) throws IOException {
        // Navigate back to the login screen
        App.setRoot("frontend/login-view");
    }
}