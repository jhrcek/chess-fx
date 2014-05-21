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
public class PositionFactoryImpl implements PositionFactory {

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
    private static final int SQUARES = 64;

    @Override
    public Position initialPosition() {
        return new PositionImpl(INITIAL_PIECES.clone(), true);
    }

    @Override
    public Position parseFen(String fen) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Position fromPosition(Position pos, Move move) {
        Square from = move.getFrom();
        if (pos.getPiece(from) != move.getPiece()) {
            throw new IllegalArgumentException("Move " + move + " impossible - there is " + pos.getPiece(from) + " on "
                    + from);
        }
        //Copy board & make changes from move
        Piece[] newBoard = new Piece[SQUARES];
        System.arraycopy(((PositionImpl) pos).getBoard(), 0, newBoard, 0, SQUARES);
        newBoard[from.getIndex()] = null;
        newBoard[move.getTo().getIndex()] = move.getPiece();

        return new PositionImpl(newBoard, !pos.isWhiteToMove());
    }
}
