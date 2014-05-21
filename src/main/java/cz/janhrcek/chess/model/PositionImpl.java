package cz.janhrcek.chess.model;

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
    /* Package private implementation*/

    Piece[] getBoard() {
        return board;
    }
}
