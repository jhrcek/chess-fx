package cz.janhrcek.chess.model;

import static cz.janhrcek.chess.model.Piece.BLACK_BISHOP;
import static cz.janhrcek.chess.model.Piece.BLACK_KING;
import static cz.janhrcek.chess.model.Piece.BLACK_KNIGHT;
import static cz.janhrcek.chess.model.Piece.BLACK_PAWN;
import static cz.janhrcek.chess.model.Piece.BLACK_QUEEN;
import static cz.janhrcek.chess.model.Piece.BLACK_ROOK;
import static cz.janhrcek.chess.model.Piece.WHITE_BISHOP;
import static cz.janhrcek.chess.model.Piece.WHITE_KING;
import static cz.janhrcek.chess.model.Piece.WHITE_KNIGHT;
import static cz.janhrcek.chess.model.Piece.WHITE_PAWN;
import static cz.janhrcek.chess.model.Piece.WHITE_QUEEN;
import static cz.janhrcek.chess.model.Piece.WHITE_ROOK;

/**
 *
 * @author jhrcek
 */
public class PositionImpl implements Position {

    private static final Piece[] INITIAL_PIECES = {
        BLACK_ROOK, BLACK_KNIGHT, BLACK_BISHOP, BLACK_QUEEN, BLACK_KING, BLACK_BISHOP, BLACK_KNIGHT, BLACK_ROOK,
        BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN,
        null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null,
        WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN,
        WHITE_ROOK, WHITE_KNIGHT, WHITE_BISHOP, WHITE_QUEEN, WHITE_KING, WHITE_BISHOP, WHITE_KNIGHT, WHITE_ROOK
    };
    private final Piece[] pieces;
    private boolean isWhiteToMove;

    public PositionImpl() {
        pieces = INITIAL_PIECES.clone();
        isWhiteToMove = true;
    }

    @Override
    public Piece getPiece(Square square) {
        return pieces[square.getIndex()];
    }

    /**
     * @param move the move to make
     * @return new object representing position after move was made from this position
     */
    @Override
    public Position move(Move move) {
        //TODO - move logic to position factory
        final Square from = move.getFrom();
        if (getPiece(from) != move.getPiece()) {
            throw new IllegalArgumentException("Move " + move + " impossible - there is " + getPiece(from) + " on "
                    + from);
        }

        PositionImpl newPosition = new PositionImpl();
        System.arraycopy(pieces, 0, newPosition.pieces, 0, pieces.length);
        newPosition.pieces[from.getIndex()] = null;
        newPosition.pieces[move.getTo().getIndex()] = move.getPiece();
        newPosition.isWhiteToMove = !isWhiteToMove;
        return newPosition;
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
}
