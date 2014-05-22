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

    PositionImpl(Piece[] board, boolean isWhiteToMove) {
        this.board = board;
        this.isWhiteToMove = isWhiteToMove;
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
        return positionFactory.fromPosition(this, move);
    }

    @Override
    public boolean isWhiteToMove() {
        return isWhiteToMove;
    }

    @Override
    public Square getEnPassantSquare() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isCastlinAvailable(boolean isWhite, boolean isKingside) {
        throw new UnsupportedOperationException("Not supported yet.");
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
        StringJoiner topJoiner = new StringJoiner("\u252C", "\u250C", "\u2510%n");
        StringJoiner midJoiner = new StringJoiner("\u253C", "\u251C", "\u2524%n");
        StringJoiner botJoiner = new StringJoiner("\u2534", "\u2514", "\u2518%n");
        StringJoiner rowJoiner = new StringJoiner("\u2502", "\u2502", "\u2502%n");
        String notch = "\u2500\u2500\u2500";

        for (int i = 0; i < 8; i++) {
            topJoiner.add(notch);
            midJoiner.add(notch);
            botJoiner.add(notch);
            rowJoiner.add(" %s ");
        }

        TOP = topJoiner.toString();
        MID = midJoiner.toString();
        BOT = botJoiner.toString();
        ROW = rowJoiner.toString();

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
}
