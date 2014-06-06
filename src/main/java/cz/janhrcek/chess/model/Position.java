package cz.janhrcek.chess.model;

import java.util.List;

/**
 * Represents state of a chess game.
 *
 * @author jhrcek
 */
public interface Position {

    Piece getPiece(Square square);

    Position move(Move move);

    boolean isWhiteToMove();

    Square getEnPassantSquare();

    boolean canCastleWK();

    boolean canCastleWQ();

    boolean canCastleBK();

    boolean canCastleBQ();

    int getHalfMoveClock();

    int getFullMoveNumber();

    /**
     * @param other
     * @return list of squares that differ (have different or no piece on it) between this and other position
     */
    public List<Square> diffTo(Position other);
}
