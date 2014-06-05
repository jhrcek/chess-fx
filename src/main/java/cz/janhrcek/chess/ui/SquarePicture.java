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

/**
 *
 * @author jhrcek
 */
public class SquarePicture extends StackPane {

    private final Square square; //TODO - use css style to color the background of square. For that we need empty transparent piece image.
    private final double size;
    private final Rectangle background;
    private Piece piece;

    public SquarePicture(Square square, double size) {
        this.square = square;
        this.size = size;
        Color color = square.isDark() ? Color.SIENNA : Color.SANDYBROWN;
        background = new Rectangle(size, size, color);
        getChildren().add(background);
        setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println("Clicked " + square);
                if (hasPiece()) {
                    removePiece();
                } else {
                    putPiece(Piece.getRandomPiece());
                }
            }
        });
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
}
