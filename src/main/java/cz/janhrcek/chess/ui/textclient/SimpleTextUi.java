package cz.janhrcek.chess.ui.textclient;

import cz.janhrcek.chess.model.Move;
import cz.janhrcek.chess.model.Piece;
import cz.janhrcek.chess.model.Position;
import cz.janhrcek.chess.model.PositionFactory;
import cz.janhrcek.chess.model.PositionFactoryImpl;
import cz.janhrcek.chess.model.Square;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SimpleTextUi extends Application {

    private final PositionFactory positionFactory = new PositionFactoryImpl();
    private Position currentPosition;
    private TextArea boardArea;

    // Creates moves based on clicks on the boardArea
    private final EventHandler<MouseEvent> MOVE_HANDLER = new EventHandler<MouseEvent>() {
        private Square from, to;
        private Piece piece;

        @Override
        public void handle(MouseEvent event) {
            if (from == null) {
                from = getSquare(event);
                piece = currentPosition.getPiece(from);
            } else {
                to = getSquare(event);
                Move move = new Move(piece, from, to);
                System.out.println("-> " + move);
                currentPosition = positionFactory.fromPosition(currentPosition, move);
                boardArea.setText(currentPosition.toString());
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
        currentPosition = positionFactory.initialPosition();
        boardArea = new TextArea(currentPosition.toString());
        boardArea.setPrefRowCount(19); // Minimum to prevent scrollbar from appearing
        boardArea.setPrefColumnCount(33);
        boardArea.setStyle("-fx-font-family: monospace");
        boardArea.setEditable(false);
        boardArea.setOnMouseClicked(MOVE_HANDLER);
        return new Pane(boardArea);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
