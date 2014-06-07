package cz.janhrcek.chess.ui;

import cz.janhrcek.chess.model.Piece;
import cz.janhrcek.chess.model.Square;
import java.util.Objects;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author jhrcek
 */
public class SquarePicture extends StackPane {

    private final Square square; //TODO - use css style to color the background of square. For that we need empty transparent piece image.
    private final double size;
    private final Rectangle background;
    private Piece piece;

    private final EventHandler<MouseEvent> RANDOM_PIECE_PUTTER = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            System.out.println("Clicked " + square);
            if (hasPiece()) {
                removePiece();
            } else {
                putPiece(Piece.getRandomPiece());
            }
        }
    };

    private final EventHandler<MouseEvent> WHERE_CAN_PIECE_GO = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            System.out.println("Clicked " + square);
            if (hasPiece()) {
                System.out.println("Piece " + piece + " can go to " + piece.whereCanGoFrom(square));
                highlight(true);
            }
        }
    };

    public SquarePicture(Square square, double size) {
        this.square = square;
        this.size = size;
        Color color = square.isDark() ? Color.SIENNA : Color.SANDYBROWN;
        background = new Rectangle(size, size, color);
        getChildren().add(background);
        //setOnMouseClicked(RANDOM_PIECE_PUTTER);
        setOnMouseClicked(WHERE_CAN_PIECE_GO);
    }

    public void putPiece(Piece piece) {
        Objects.requireNonNull(piece, "piece");

        if (getChildren().size() > 1) {
            removePiece();
        }
        this.piece = piece;
        ImageView pieceImage = new ImageView(PieceImages.get(piece));
        pieceImage.setFitHeight(size);
        pieceImage.setFitWidth(size);
        getChildren().add(pieceImage);
    }

    public void removePiece() {
        int childrenCount;
        while ((childrenCount = getChildren().size()) > 1) {
            getChildren().remove(childrenCount - 1);
        }
        this.piece = null;
    }

    public boolean hasPiece() {
        return piece != null;
    }

    @Override
    public String toString() {
        return "Picture of " + square;
    }

    public void highlight(boolean enable) {
        if (enable) {
            background.setStrokeType(StrokeType.INSIDE);
            background.setStroke(Color.RED);
            background.setStrokeWidth(10);
        } else {
            background.setStroke(null);
        }
    }
}
