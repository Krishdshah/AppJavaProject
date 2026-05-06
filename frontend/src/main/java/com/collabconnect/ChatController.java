package com.collabconnect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ChatController {

    @FXML private VBox contactsList;
    @FXML private Label chatHeaderName;
    @FXML private VBox messageHistory;
    @FXML private TextField messageInput;

    @FXML
    public void initialize() {
        // Load mock contacts
        addContact("Laksh Baweja", "Project Alpha", true);
        addContact("Kabilesh C", "Hey, are we still on...", false);
        addContact("Alice Smith", "Thanks!", false);

        // Load mock history for Laksh
        addMessage("Hey Laksh, how is the backend coming along?", true);
        addMessage("Almost done! Just finishing up the API endpoints.", false);
        addMessage("Great! Let me know when it's ready to test.", true);
    }

    private void addContact(String name, String lastMessage, boolean active) {
        VBox item = new VBox();
        item.getStyleClass().add("chat-sidebar-item");
        if (active) item.getStyleClass().add("active");

        Label nameLbl = new Label(name);
        nameLbl.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        Label msgLbl = new Label(lastMessage);
        msgLbl.getStyleClass().add("text-muted");

        item.getChildren().addAll(nameLbl, msgLbl);
        contactsList.getChildren().add(item);
    }

    private void addMessage(String text, boolean isMe) {
        HBox row = new HBox();
        row.setAlignment(isMe ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);

        Label bubble = new Label(text);
        bubble.setWrapText(true);
        bubble.setMaxWidth(300);
        bubble.getStyleClass().addAll("chat-text", isMe ? "chat-bubble-me" : "chat-bubble-other");

        row.getChildren().add(bubble);
        messageHistory.getChildren().add(row);
    }

    @FXML
    protected void handleSendMessage(ActionEvent event) {
        String msg = messageInput.getText().trim();
        if (!msg.isEmpty()) {
            addMessage(msg, true);
            messageInput.clear();
        }
    }
}
