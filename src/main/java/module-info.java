module log121.lab2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires json.simple;


    opens log121.lab2 to javafx.fxml;
    exports log121.lab2;
}