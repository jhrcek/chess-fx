package cz.janhrcek.chess.ui.client;

import cz.janhrcek.chess.model.Game;
import cz.janhrcek.chess.model.GameFactory;
import cz.janhrcek.chess.model.Move;
import cz.janhrcek.chess.model.Piece;
import cz.janhrcek.chess.model.Square;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SimpleTextUi extends Application {

    private Game game = GameFactory.createGame();
    private TextArea boardArea;

    // Creates moves based on clicks on the boardArea
    private final EventHandler<MouseEvent> MOVE_HANDLER = new EventHandler<MouseEvent>() {
        private Square from, to;
        private Piece piece;

        @Override
        public void handle(MouseEvent event) {
            if (from == null) {
                from = getSquare(event);
                piece = game.getCurrentPosition().getPiece(from);
            } else {
                to = getSquare(event);
                Move move = new Move(piece, from, to);
                System.out.println("-> " + move);
                game.makeMove(move);
                boardArea.setText(game.getCurrentPosition().toString());
                from = to = null;
                piece = null;
            }
        }
    };

    private Square getSquare(MouseEvent event) {
        int file = (int) (event.getX() - 12) / 32;
        int rank = (int) (event.getY() - 12) / 32;
        Square square = Square.valueOf("" + "ABCDEFGH".charAt(file) + "87654321".charAt(rank));
        System.out.print(square + " ");
        return square;
    }

    private Pane createContent() {
        boardArea = new TextArea(game.getCurrentPosition().toString());
        boardArea.setPrefRowCount(19); // Minimum to prevent scrollbar from appearing
        boardArea.setPrefColumnCount(33);
        boardArea.setStyle("-fx-font-family: monospace");
        boardArea.setEditable(false);
        boardArea.setOnMouseClicked(MOVE_HANDLER);

        Button backButton = new Button("Back");
        backButton.setOnAction((ActionEvent event) -> {
            game.navigateTo().previousPosition();
            boardArea.setText(game.getCurrentPosition().toString());
        });

        VBox box = new VBox();
        box.getChildren().addAll(boardArea, backButton);
        return box;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.setTitle("Chess FX : Text UI");
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
