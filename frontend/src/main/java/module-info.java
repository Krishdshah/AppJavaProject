module com.collabconnect {
    requires javafx.controls;
    requires javafx.fxml;
    requires atlantafx.base;
    opens com.collabconnect to javafx.fxml;
    exports com.collabconnect;
}
