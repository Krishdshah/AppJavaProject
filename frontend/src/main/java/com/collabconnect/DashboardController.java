package com.collabconnect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class DashboardController {

    @FXML
    private BorderPane mainPane;

    @FXML
    private Label welcomeLabel;

    public void initialize() {
        try {
            handleShowDashboard(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleShowDashboard(ActionEvent event) throws IOException {
        loadCenterPane("frontend/dashboard-home-view");
    }

    @FXML
    protected void handleShowProjects(ActionEvent event) throws IOException {
        loadCenterPane("frontend/my-projects-view");
    }

    @FXML
    protected void handleShowFindMates(ActionEvent event) throws IOException {
        loadCenterPane("frontend/find-mates-view");
    }

    @FXML
    protected void handleShowProfile(ActionEvent event) throws IOException {
        loadCenterPane("frontend/my-profile-view");
    }

    @FXML
    protected void handleShowMessages(ActionEvent event) throws IOException {
        loadCenterPane("frontend/chat-view");
    }

    @FXML
    protected void handleShowTasks(ActionEvent event) throws IOException {
        loadCenterPane("frontend/kanban-view");
    }

    @FXML
    protected void handleLogoutButton(ActionEvent event) throws IOException {
        App.setRoot("frontend/login-view");
    }

    private void loadCenterPane(String fxmlFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxmlFile + ".fxml"));
        Parent pane = fxmlLoader.load();
        mainPane.setCenter(pane);
    }
}