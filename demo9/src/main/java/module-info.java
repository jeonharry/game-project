module org.example.demo9 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens org.example.demo9 to javafx.fxml;
    exports org.example.demo9;
    exports org.example.demo9.model;
    opens org.example.demo9.model to javafx.fxml;


}