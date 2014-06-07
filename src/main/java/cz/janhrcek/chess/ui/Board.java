package cz.janhrcek.chess.ui;

import cz.janhrcek.chess.model.Position;
import cz.janhrcek.chess.model.PositionFactoryImpl;
import cz.janhrcek.chess.model.Square;
import java.util.Set;
import javafx.scene.layout.GridPane;

/**
 *
 * @author jhrcek
 */
public class Board extends GridPane {

    SquarePicture[] squarePictures = new SquarePicture[64]; //TODO ugly - duplicating of SquarePicture references. Is there no way to get them from the grid?

    public Board(int squareSize) {
        Position position = new PositionFactoryImpl().createInitialPosition();
        for (Square square : Square.values()) {
            SquarePicture squarePicture = new SquarePicture(square, squareSize, this);
            squarePicture.putPiece(position.getPiece(square));
            add(squarePicture, square.getFileIndex(), square.getRankIndex());
            squarePictures[square.getIndex()] = squarePicture;
        }
    }

    public void handleSquareClick(SquarePicture sqPic) {
        // Testing implementation - higlights all squares clicked piece can go to
        for (SquarePicture sp : squarePictures) {
            sp.highlight(false);
        }
        if (sqPic.hasPiece()) {
            Set<Square> whereCanGo = sqPic.getPiece().whereCanGoFrom(sqPic.getSquare());
            for (Square sq : whereCanGo) {
                squarePictures[sq.getIndex()].highlight(true);
            }
        }
    }
}
