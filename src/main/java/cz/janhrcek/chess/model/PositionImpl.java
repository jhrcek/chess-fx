package cz.janhrcek.chess.model;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 *
 * @author jhrcek
 */
public class PositionImpl implements Position {

    private final Piece[] board;
    private final boolean isWhiteToMove;
    private final PositionFactory positionFactory = new PositionFactoryImpl();
    //Castling availabilities
    private final boolean canWK;
    private final boolean canWQ;
    private final boolean canBK;
    private final boolean canBQ;
    private final Square enPassantSquare;

    PositionImpl(Piece[] board, boolean isWhiteToMove, boolean canWK, boolean canWQ, boolean canBK, boolean canBQ, Square enPassantSquare) {
        this.board = board;
        this.isWhiteToMove = isWhiteToMove;
        this.canWK = canWK;
        this.canWQ = canWQ;
        this.canBK = canBK;
        this.canBQ = canBQ;
        this.enPassantSquare = enPassantSquare;
    }

    @Override
    public Piece getPiece(Square square) {
        return board[square.getIndex()];
    }

    /**
     * @param move the move to make
     * @return new object representing position after move was made from this position
     */
    @Override
    public Position move(Move move) {
        return positionFactory.createPositionFrom(this, move);
    }

    @Override
    public boolean isWhiteToMove() {
        return isWhiteToMove;
    }

    @Override
    public Square getEnPassantSquare() {
        return enPassantSquare;
    }

    @Override
    public int getHalfMoveNumber() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getFullMoveNumber() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        Object[] fenPieces = Arrays.stream(board)
                .map(piece -> piece == null ? " " : piece.getFen())
                .toArray();
        return String.format(BOARD_FORMAT, fenPieces);
    }

    // toString() implemetation - BOARD_FORMAT is table border consisting of unicode characters
    private static final String TOP, MID, BOT, ROW, BOARD_FORMAT;

    static {
        TOP = "┌───┬───┬───┬───┬───┬───┬───┬───┐\n";
        MID = "├───┼───┼───┼───┼───┼───┼───┼───┤\n";
        BOT = "└───┴───┴───┴───┴───┴───┴───┴───┘\n";
        ROW = "│ %s │ %s │ %s │ %s │ %s │ %s │ %s │ %s │\n";

        StringJoiner formatJoiner = new StringJoiner(MID, TOP, BOT);
        for (int i = 0; i < 8; i++) {
            formatJoiner.add(ROW);
        }
        BOARD_FORMAT = formatJoiner.toString();
    }

    /* Package private implementation*/
    Piece[] getBoard() {
        return board;
    }

    @Override
    public boolean canCastleWK() {
        return canWK;
    }

    @Override
    public boolean canCastleWQ() {
        return canWQ;
    }

    @Override
    public boolean canCastleBK() {
        return canBK;
    }

    @Override
    public boolean canCastleBQ() {
        return canBQ;
    }
}
