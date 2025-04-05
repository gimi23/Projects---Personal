package TetrisFiles;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class tetris extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("TETRIS");
        PaneOrganizer organizer = new PaneOrganizer();
        Scene scene = new Scene(organizer.getRoot(), 1200, 1000);
        primaryStage.setScene(scene);
        organizer.getRoot().requestFocus();
        primaryStage.show();
    }
}