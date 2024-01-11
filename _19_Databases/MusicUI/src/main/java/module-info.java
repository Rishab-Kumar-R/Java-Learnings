module com.rishab.musicui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.rishab.musicui to javafx.fxml;
    opens com.rishab.musicui.model to javafx.base;
    exports com.rishab.musicui;
}