module org.example.demo9 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires javafx.media;


    opens org.example.demo9 to javafx.fxml;
    exports org.example.demo9;
    exports org.example.demo9.model;
    opens org.example.demo9.model to javafx.fxml;
    exports org.example.demo9.model.towers;
    opens org.example.demo9.model.towers to javafx.fxml;
    exports org.example.demo9.model.raiders;
    opens org.example.demo9.model.raiders to javafx.fxml;
    exports org.example.demo9.model.spells;
    opens org.example.demo9.model.spells to javafx.fxml;


}