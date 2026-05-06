package com.collabconnect;

import com.collabconnect.model.Project;
import com.collabconnect.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FindMatesController {

    @FXML private TextField searchField;
    @FXML private VBox peopleList;
    @FXML private VBox projectList;
    
    @FXML private Label javaChip;
    @FXML private Label uiuxChip;
    @FXML private Label springChip;
    @FXML private Label pythonChip;

    private ObservableList<User> allUsers;
    private ObservableList<Project> allProjects;
    private String activeFilter = "";

    @FXML
    public void initialize() {
        // Load Mock Data
        allUsers = FXCollections.observableArrayList(
            new User("Laksh Baweja", "Backend Developer", Arrays.asList("Java", "Spring Boot", "SQL"), "Available", "laksh@test.com"),
            new User("Kabilesh C", "UI/UX Designer", Arrays.asList("UI/UX", "Figma", "CSS"), "Busy", "kabi@test.com"),
            new User("Alice Smith", "Data Scientist", Arrays.asList("Python", "Machine Learning", "SQL"), "Available", "alice@test.com"),
            new User("Bob Johnson", "Full Stack Dev", Arrays.asList("Java", "React", "Node.js"), "Available", "bob@test.com"),
            new User("Emma Davis", "Frontend Dev", Arrays.asList("HTML", "CSS", "UI/UX"), "Available", "emma@test.com")
        );

        allProjects = FXCollections.observableArrayList(
            new Project("EcoTracker App", "Track carbon footprint", "Open", Arrays.asList("Java", "REST APIs", "UI/UX"), Arrays.asList()),
            new Project("AI Chatbot", "Customer support bot", "Open", Arrays.asList("Python", "NLP"), Arrays.asList("Alice Smith")),
            new Project("E-commerce Backend", "Spring Boot microservices", "Open", Arrays.asList("Java", "Spring Boot", "SQL"), Arrays.asList("Laksh Baweja"))
        );

        // Setup Search Listener
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData(newValue, activeFilter);
        });

        // Initial Load
        filterData("", "");
    }

    @FXML
    protected void handleChipClick(MouseEvent event) {
        Label clickedChip = (Label) event.getSource();
        String skill = clickedChip.getText().trim();
        
        if (activeFilter.equals(skill)) {
            // Toggle off
            activeFilter = "";
            clickedChip.setStyle(""); // Reset to default style from CSS
        } else {
            // Toggle on
            activeFilter = skill;
            // Highlight it (basic visual feedback)
            javaChip.setOpacity(0.5); uiuxChip.setOpacity(0.5); springChip.setOpacity(0.5); pythonChip.setOpacity(0.5);
            clickedChip.setOpacity(1.0);
        }
        
        filterData(searchField.getText(), activeFilter);
    }

    private void filterData(String searchText, String skillFilter) {
        peopleList.getChildren().clear();
        projectList.getChildren().clear();

        String lowerSearch = searchText != null ? searchText.toLowerCase() : "";

        // Filter Users
        List<User> filteredUsers = allUsers.stream()
            .filter(u -> (u.getName().toLowerCase().contains(lowerSearch) || u.getRole().toLowerCase().contains(lowerSearch)))
            .filter(u -> skillFilter.isEmpty() || u.getSkills().contains(skillFilter))
            .collect(Collectors.toList());

        for (User user : filteredUsers) {
            VBox card = new VBox();
            card.getStyleClass().add("content-card");
            
            Label nameLbl = new Label(user.getName());
            nameLbl.getStyleClass().add("title-4");
            nameLbl.setStyle("-fx-text-fill: white;");
            
            Label roleLbl = new Label(user.getRole());
            roleLbl.getStyleClass().add("text-muted");
            
            Label skillsLbl = new Label("Skills: " + String.join(", ", user.getSkills()));
            skillsLbl.setStyle("-fx-text-fill: #58a6ff;");
            VBox.setMargin(skillsLbl, new Insets(10, 0, 0, 0));

            card.getChildren().addAll(nameLbl, roleLbl, skillsLbl);
            peopleList.getChildren().add(card);
        }

        // Filter Projects
        List<Project> filteredProjects = allProjects.stream()
            .filter(p -> p.getTitle().toLowerCase().contains(lowerSearch) || p.getDescription().toLowerCase().contains(lowerSearch))
            .filter(p -> skillFilter.isEmpty() || p.getRequiredSkills().contains(skillFilter))
            .collect(Collectors.toList());

        for (Project proj : filteredProjects) {
            VBox card = new VBox();
            card.getStyleClass().add("content-card");
            
            Label titleLbl = new Label(proj.getTitle());
            titleLbl.getStyleClass().add("title-4");
            titleLbl.setStyle("-fx-text-fill: white;");
            
            Label descLbl = new Label(proj.getDescription());
            descLbl.getStyleClass().add("text-muted");
            
            Label reqLbl = new Label("Needs: " + String.join(", ", proj.getRequiredSkills()));
            reqLbl.setStyle("-fx-text-fill: #3fb950;");
            VBox.setMargin(reqLbl, new Insets(10, 0, 0, 0));

            card.getChildren().addAll(titleLbl, descLbl, reqLbl);
            projectList.getChildren().add(card);
        }
    }
}
