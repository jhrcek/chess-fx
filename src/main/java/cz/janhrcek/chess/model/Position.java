package cz.janhrcek.chess.model;

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

    int getHalfMoveNumber();

    int getFullMoveNumber();
}
