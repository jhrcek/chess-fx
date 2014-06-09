package cz.janhrcek.chess.model;

import static cz.janhrcek.chess.model.Piece.*;

import java.util.Objects;

/**
 *
 * @author jhrcek
 */
public class Promotion extends Move {

    private final Piece promo;

    public Promotion(Piece piece, Square from, Square to, Piece promo) {
        super(piece, from, to);
        this.promo = Objects.requireNonNull(promo, "promo");
        if (piece != WHITE_PAWN && piece != BLACK_PAWN) {
            throw new IllegalArgumentException("Pawn promotion allowed for pawns only, not for " + piece);
        }
        boolean isValidPromoPiece = piece.isWhite()
                ? (promo == WHITE_QUEEN || promo == WHITE_ROOK || promo == WHITE_BISHOP || promo == WHITE_KNIGHT)
                : (promo == BLACK_QUEEN || promo == BLACK_ROOK || promo == BLACK_BISHOP || promo == BLACK_KNIGHT);
        if (!isValidPromoPiece) {
            throw new IllegalArgumentException("Can't promote " + piece + " to " + promo);
        }
    }

    public Piece getPromoPiece() {
        return promo;
    }

    @Override
    public int hashCode() {
        return 37 * super.hashCode() + promo.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Promotion other = (Promotion) obj;
        return getPiece() == other.getPiece()
                && getFrom() == other.getFrom()
                && getTo() == other.getTo()
                && promo == other.promo;
    }
}
