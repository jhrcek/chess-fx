package cz.janhrcek.chess.ui.client;

import static javafx.application.Application.launch;

import cz.janhrcek.chess.model.Piece;
import cz.janhrcek.chess.model.Position;
import cz.janhrcek.chess.model.PositionFactoryImpl;
import cz.janhrcek.chess.model.Square;
import cz.janhrcek.chess.ui.SquarePicture;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UiPrototype extends Application {

    private Pane createBoard() {
        GridPane grid = new GridPane();
        Position position = new PositionFactoryImpl().createInitialPosition();
        for (Square sq : Square.values()) {
            SquarePicture boardSquare = new SquarePicture(sq, 50.0);
            Piece pieceOnSq = position.getPiece(sq);
            if (pieceOnSq != null) {
                boardSquare.putPiece(position.getPiece(sq));
            }
            grid.add(boardSquare, sq.getFileIndex(), sq.getRankIndex());
        }
        return grid;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createBoard());
        //scene.setCursor(new ImageCursor(PIECE_IMAGES.get(Piece.WHITE_KNIGHT))); //Set custom cursor
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
