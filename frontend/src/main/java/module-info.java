module com.collabconnect {
    requires javafx.controls;
    requires javafx.fxml;
    opens com.collabconnect to javafx.fxml;
    exports com.collabconnect;
}
