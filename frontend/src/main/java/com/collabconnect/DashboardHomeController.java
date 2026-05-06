package com.collabconnect;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class DashboardHomeController {

    @FXML private Label activeProjectsLabel;
    @FXML private Label connectionsLabel;
    @FXML private Label pendingInvitesLabel;
    @FXML private PieChart skillsChart;
    @FXML private VBox recentActivityBox;

    @FXML
    public void initialize() {
        // Load stats
        activeProjectsLabel.setText("5");
        connectionsLabel.setText("18");
        pendingInvitesLabel.setText("2");

        // Load Chart Data
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
            new PieChart.Data("Java", 40),
            new PieChart.Data("UI/UX", 25),
            new PieChart.Data("Python", 20),
            new PieChart.Data("React", 15)
        );
        skillsChart.setData(pieChartData);

        // Load Recent Activity
        addActivityItem("[NEW]", "#58a6ff", "Laksh Baweja joined Project Alpha", "2 hours ago");
        addActivityItem("[UPDATE]", "#3fb950", "Project Beta milestone completed", "Yesterday");
        addActivityItem("[INVITE]", "#e3b341", "Kabilesh C invited you to EcoTracker", "2 days ago");
        addActivityItem("[NEW]", "#58a6ff", "Alice Smith created AI Chatbot", "3 days ago");
    }

    private void addActivityItem(String tag, String tagColor, String text, String time) {
        HBox hbox = new HBox();
        hbox.setSpacing(15);
        hbox.getStyleClass().add("content-card");

        Label tagLbl = new Label(tag);
        tagLbl.setStyle("-fx-text-fill: " + tagColor + ";");
        if(tag.equals("[NEW]")) tagLbl.getStyleClass().add("accent");
        else if(tag.equals("[UPDATE]")) tagLbl.getStyleClass().add("success");
        else tagLbl.getStyleClass().add("warning");

        VBox textBox = new VBox();
        HBox.setHgrow(textBox, Priority.ALWAYS);
        
        Label textLbl = new Label(text);
        textLbl.setStyle("-fx-text-fill: white;");
        
        Label timeLbl = new Label(time);
        timeLbl.getStyleClass().addAll("text-small", "text-muted");
        
        textBox.getChildren().addAll(textLbl, timeLbl);
        hbox.getChildren().addAll(tagLbl, textBox);

        recentActivityBox.getChildren().add(hbox);
    }
}
