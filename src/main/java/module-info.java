module log121.lab2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens log121.lab2 to javafx.fxml;
    exports log121.lab2;
}