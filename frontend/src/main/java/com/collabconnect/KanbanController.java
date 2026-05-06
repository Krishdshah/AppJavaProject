package com.collabconnect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class KanbanController {

    @FXML private ComboBox<String> projectSelector;
    @FXML private VBox todoColumn;
    @FXML private VBox inProgressColumn;
    @FXML private VBox doneColumn;

    @FXML
    public void initialize() {
        projectSelector.getSelectionModel().selectFirst();
        
        // Mock Tasks
        todoColumn.getChildren().add(createTaskCard("Design Database Schema", "todo"));
        todoColumn.getChildren().add(createTaskCard("Setup Spring Security", "todo"));
        
        inProgressColumn.getChildren().add(createTaskCard("Build UI components", "in_progress"));
        
        doneColumn.getChildren().add(createTaskCard("Initialize Repository", "done"));
    }

    private VBox createTaskCard(String taskName, String currentStatus) {
        VBox card = new VBox();
        card.setSpacing(10);
        card.getStyleClass().add("kanban-card");

        Label title = new Label(taskName);
        title.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        title.setWrapText(true);

        HBox actions = new HBox();
        actions.setSpacing(5);
        actions.setAlignment(Pos.CENTER_RIGHT);

        if (currentStatus.equals("todo")) {
            Button moveRight = new Button("Start ->");
            moveRight.getStyleClass().addAll("small", "accent");
            moveRight.setOnAction(e -> {
                todoColumn.getChildren().remove(card);
                inProgressColumn.getChildren().add(createTaskCard(taskName, "in_progress"));
            });
            actions.getChildren().add(moveRight);
        } else if (currentStatus.equals("in_progress")) {
            Button moveLeft = new Button("<- Back");
            moveLeft.getStyleClass().addAll("small");
            moveLeft.setOnAction(e -> {
                inProgressColumn.getChildren().remove(card);
                todoColumn.getChildren().add(createTaskCard(taskName, "todo"));
            });
            
            Button moveRight = new Button("Done ->");
            moveRight.getStyleClass().addAll("small", "success");
            moveRight.setOnAction(e -> {
                inProgressColumn.getChildren().remove(card);
                doneColumn.getChildren().add(createTaskCard(taskName, "done"));
            });
            
            actions.getChildren().addAll(moveLeft, moveRight);
        }

        card.getChildren().addAll(title, actions);
        return card;
    }

    @FXML
    protected void handleAddTask(ActionEvent event) {
        // Simple mock addition
        todoColumn.getChildren().add(0, createTaskCard("New Task " + (System.currentTimeMillis() % 1000), "todo"));
    }
}
