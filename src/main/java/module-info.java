module pancakeman.sqldbapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires javafx.web;

    opens pancakeman.sqldbapp to javafx.fxml;
    exports pancakeman.sqldbapp;
}