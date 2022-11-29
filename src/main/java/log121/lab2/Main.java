package log121.lab2;

import javafx.application.Application;
import javafx.stage.Stage;
import log121.lab2.controller.CommandManager;
import log121.lab2.view.MainView;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        MainView mainView = new MainView();
        CommandManager.launch();
    }
}
