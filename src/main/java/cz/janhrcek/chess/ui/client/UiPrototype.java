package cz.janhrcek.chess.ui.client;

import static javafx.application.Application.launch;

import cz.janhrcek.chess.model.Piece;
import cz.janhrcek.chess.model.Position;
import cz.janhrcek.chess.model.PositionFactoryImpl;
import cz.janhrcek.chess.model.Square;
import java.util.EnumMap;
import java.util.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class UiPrototype extends Application {

    private static final Map<Piece, Image> PIECE_IMAGES = new EnumMap<>(Piece.class);

    static {
        for (Piece piece : Piece.values()) {
            PIECE_IMAGES.put(piece,
                    new Image(UiPrototype.class.getResource(String.format("/pieces/basic/%s.png", piece)).toString()));
        }
    }

    private Pane createBoard() {
        GridPane root = new GridPane();
        Position position = new PositionFactoryImpl().initialPosition();
        for (Square sq : Square.values()) {
            Pane boardSquare = createSquare(position.getPiece(sq), getColor(sq), 50);
            root.add(boardSquare, sq.getFileIndex(), sq.getRankIndex());
        }
        return root;
    }

    private Color getColor(Square square) {
        return square.isDark() ? Color.SIENNA : Color.SANDYBROWN;
    }

    private Pane createSquare(Piece piece, Color backgrColor, int size) {
        Pane square = new StackPane();
        Rectangle squareBackground = new Rectangle(size, size, backgrColor);
        square.getChildren().add(squareBackground);
        if (piece != null) {
            ImageView pieceImage = new ImageView(PIECE_IMAGES.get(piece));
            pieceImage.setFitHeight(size);
            pieceImage.setFitWidth(size);
            square.getChildren().add(pieceImage);
        }
        return square;
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
