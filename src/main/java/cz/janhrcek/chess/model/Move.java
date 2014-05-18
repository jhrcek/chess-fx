package cz.janhrcek.chess.model;

import java.util.Objects;

/**
 * Represents move of a piece from one square to another.
 *
 * @author jhrcek
 */
public class Move {

    private final Piece piece;
    private final Square from;
    private final Square to;

    public Move(Piece piece, Square from, Square to) {
        this.piece = Objects.requireNonNull(piece);
        this.from = Objects.requireNonNull(from);
        this.to = Objects.requireNonNull(to);
    }

    public Piece getPiece() {
        return piece;
    }

    public Square getFrom() {
        return from;
    }

    public Square getTo() {
        return to;
    }

    @Override
    public String toString() {
        return piece.getSan() + (from + "-" + to).toLowerCase();
    }
}
