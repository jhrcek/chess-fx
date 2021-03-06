package cz.janhrcek.chess.model;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Stream;

/**
 *
 * @author jhrcek
 */
public class PositionImpl implements Position {

    private final Piece[] board;
    private final boolean isWhiteToMove;
    private final PositionFactory positionFactory = new PositionFactoryImpl();
    //Castling availabilities
    private final boolean canWK, canWQ, canBK, canBQ;
    private final Square enPassantSquare;
    private final int halfmoveClock;
    private final int fullmoveNumber;

    PositionImpl(Piece[] board, boolean isWhiteToMove,
            boolean canWK, boolean canWQ, boolean canBK, boolean canBQ,
            Square enPassantSquare, int halfmoveClock, int fullmoveNumber) {
        this.board = board;
        this.isWhiteToMove = isWhiteToMove;
        this.canWK = canWK;
        this.canWQ = canWQ;
        this.canBK = canBK;
        this.canBQ = canBQ;
        this.enPassantSquare = enPassantSquare;
        this.halfmoveClock = halfmoveClock;
        this.fullmoveNumber = fullmoveNumber;
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
    public int getHalfMoveClock() {
        return halfmoveClock;
    }

    @Override
    public int getFullMoveNumber() {
        return fullmoveNumber;
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

    @Override
    public List<Square> diffTo(Position other) {
        return Stream.of(Square.values())
                .filter(square -> getPiece(square) != other.getPiece(square))
                .collect(toList());
    }
}
