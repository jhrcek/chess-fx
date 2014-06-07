package cz.janhrcek.chess.ui.client;

import cz.janhrcek.chess.ui.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UiPrototype extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new Board(50));
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
