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
        return new PositionImpl(INITIAL_PIECES.clone(), true, true, true, true, true, null);
    }

    @Override
    public Position fromPosition(Position pos, Move move) {
        Square from = move.getFrom();
        Piece piece = move.getPiece();
        if (pos.getPiece(from) != piece) {
            throw new IllegalArgumentException("Move " + move + " impossible - there is " + pos.getPiece(from) + " on "
                    + from);
        }

        //Copy board + make changes requested in move
        Piece[] newBoard = new Piece[SQUARES];
        System.arraycopy(((PositionImpl) pos).getBoard(), 0, newBoard, 0, SQUARES);
        newBoard[from.getIndex()] = null;
        newBoard[move.getTo().getIndex()] = piece;

        //Castling rights
        boolean wk = pos.canCastleWK(), wq = pos.canCastleWQ(), bk = pos.canCastleBK(), bq = pos.canCastleBQ();
        if (piece == Piece.WHITE_KING) { //Moving the king looses castling rights
            wk = wq = false;
        } else if (piece == Piece.BLACK_KING) {
            bk = bq = false;
        } //TODO: loose castling rights after rook moves

        return new PositionImpl(newBoard, !pos.isWhiteToMove(), wk, wq, bk, bq, null);
    }
}
