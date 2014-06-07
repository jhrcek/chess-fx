package cz.janhrcek.chess.ui;

import cz.janhrcek.chess.model.Piece;
import cz.janhrcek.chess.model.Square;
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
    private final Board parent;
    private Piece piece;

    public SquarePicture(Square square, double size, Board parent) {
        this.square = square;
        this.size = size;
        this.parent = parent;
        Color color = square.isDark() ? Color.SIENNA : Color.SANDYBROWN;
        background = new Rectangle(size, size, color);
        getChildren().add(background);
        setOnMouseClicked((MouseEvent event) -> { //Just let the board know the square was clicked
            this.parent.handleSquareClick(this);
        });
    }

    public void putPiece(Piece piece) {
        this.piece = piece;
        if (piece == null && getChildren().size() > 1) {
            getChildren().remove(1); // remove piece image
        } else {
            ImageView pieceImage = new ImageView(PieceImages.get(piece));
            pieceImage.setFitHeight(size);
            pieceImage.setFitWidth(size);
            getChildren().add(pieceImage);
        }
    }

    public Piece getPiece() {
        return piece;
    }

    public Square getSquare() {
        return square;
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
