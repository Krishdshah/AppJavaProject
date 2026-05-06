package com.collabconnect;

import com.collabconnect.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.Arrays;

public class MyProfileController {

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField skillsField;
    @FXML private TextField availabilityField;
    @FXML private Button editButton;

    private User currentUser;
    private boolean isEditing = false;

    @FXML
    public void initialize() {
        // Load mock current user
        currentUser = new User(
            "Krish D Shah",
            "Full Stack Developer",
            Arrays.asList("Java", "JavaFX", "Spring Boot"),
            "Available",
            "thekrishdshahbhs@gmail.com"
        );
        populateFields();
    }

    private void populateFields() {
        nameField.setText(currentUser.getName());
        emailField.setText(currentUser.getEmail());
        skillsField.setText(String.join(", ", currentUser.getSkills()));
        availabilityField.setText(currentUser.getAvailability());
    }

    @FXML
    protected void handleEditProfile(ActionEvent event) {
        isEditing = !isEditing;
        
        if (isEditing) {
            // Enter edit mode
            editButton.setText("Save Changes");
            editButton.getStyleClass().remove("accent");
            editButton.getStyleClass().add("success");
            
            toggleFieldEditing(true);
        } else {
            // Save changes
            editButton.setText("Edit Profile");
            editButton.getStyleClass().remove("success");
            editButton.getStyleClass().add("accent");
            
            // Save logic (mock)
            currentUser.setSkills(Arrays.asList(skillsField.getText().split(",")));
            currentUser.setAvailability(availabilityField.getText());
            
            toggleFieldEditing(false);
            populateFields(); // refresh
        }
    }

    private void toggleFieldEditing(boolean editable) {
        // We only allow editing skills and availability for now
        skillsField.setEditable(editable);
        availabilityField.setEditable(editable);
        
        String editStyle = "-fx-background-color: rgba(255,255,255,0.1); -fx-text-fill: white; -fx-padding: 5;";
        String readStyle = "-fx-background-color: transparent; -fx-text-fill: white;";
        
        skillsField.setStyle(editable ? editStyle : readStyle);
        
        String availEditStyle = "-fx-background-color: rgba(255,255,255,0.1); -fx-text-fill: #e3b341; -fx-padding: 5;";
        String availReadStyle = "-fx-background-color: transparent; -fx-text-fill: #e3b341;";
        availabilityField.setStyle(editable ? availEditStyle : availReadStyle);
    }
}
