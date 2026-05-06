package com.collabconnect;

import com.collabconnect.model.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class MyProjectsController {

    @FXML private VBox activeProjectsBox;
    @FXML private VBox completedProjectsBox;

    private ObservableList<Project> allProjects;

    @FXML
    public void initialize() {
        allProjects = FXCollections.observableArrayList(
            new Project("Collaboration Partner Finder", "A platform to find mates.", "In Progress", Arrays.asList("Java", "JavaFX"), Arrays.asList("Krish D Shah", "Laksh Baweja")),
            new Project("University Research Tool", "Tool for collecting data.", "Completed", Arrays.asList("Python", "Pandas"), Arrays.asList("Krish D Shah", "Kabilesh C"))
        );
        refreshProjects();
    }

    private void refreshProjects() {
        activeProjectsBox.getChildren().clear();
        completedProjectsBox.getChildren().clear();

        for (Project proj : allProjects) {
            TitledPane pane = createProjectCard(proj);
            if (proj.getStatus().equals("Completed")) {
                completedProjectsBox.getChildren().add(pane);
            } else {
                activeProjectsBox.getChildren().add(pane);
            }
        }
    }

    private TitledPane createProjectCard(Project proj) {
        VBox cardContent = new VBox();
        cardContent.setSpacing(10);
        cardContent.getStyleClass().add("content-card");

        Label descLbl = new Label(proj.getDescription());
        descLbl.getStyleClass().add("text-muted");

        Label statusLbl = new Label("Status: " + proj.getStatus());
        statusLbl.setStyle(proj.getStatus().equals("Completed") ? "-fx-text-fill: #3fb950;" : "-fx-text-fill: #58a6ff;");

        Label teamLbl = new Label("Team: " + String.join(", ", proj.getTeamMembers()));
        teamLbl.setStyle("-fx-text-fill: white;");
        
        Label skillsLbl = new Label("Required Skills: " + String.join(", ", proj.getRequiredSkills()));
        skillsLbl.setStyle("-fx-text-fill: #e3b341;");

        cardContent.getChildren().addAll(descLbl, statusLbl, teamLbl, skillsLbl);

        TitledPane titledPane = new TitledPane(proj.getTitle(), cardContent);
        titledPane.setAnimated(true);
        titledPane.setExpanded(false);
        return titledPane;
    }

    @FXML
    protected void handleCreateProject(ActionEvent event) {
        Dialog<Project> dialog = new Dialog<>();
        dialog.setTitle("Create New Project");
        dialog.setHeaderText("Enter project details below");

        ButtonType createButtonType = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(createButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField title = new TextField();
        title.setPromptText("Project Title");
        TextField description = new TextField();
        description.setPromptText("Description");
        TextField skills = new TextField();
        skills.setPromptText("Skills (comma separated)");

        grid.add(new Label("Title:"), 0, 0);
        grid.add(title, 1, 0);
        grid.add(new Label("Description:"), 0, 1);
        grid.add(description, 1, 1);
        grid.add(new Label("Skills:"), 0, 2);
        grid.add(skills, 1, 2);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == createButtonType) {
                return new Project(
                    title.getText().isEmpty() ? "Untitled Project" : title.getText(),
                    description.getText().isEmpty() ? "No description" : description.getText(),
                    "In Progress",
                    Arrays.stream(skills.getText().split(",")).map(String::trim).collect(Collectors.toList()),
                    Arrays.asList("Krish D Shah") // Current user
                );
            }
            return null;
        });

        Optional<Project> result = dialog.showAndWait();
        result.ifPresent(project -> {
            allProjects.add(project);
            refreshProjects();
        });
    }
}
