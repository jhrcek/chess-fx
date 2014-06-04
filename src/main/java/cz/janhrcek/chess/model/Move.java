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
        this.piece = Objects.requireNonNull(piece, "piece");
        this.from = Objects.requireNonNull(from, "from");
        this.to = Objects.requireNonNull(to, "to");
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

    @Override
    public int hashCode() {
        int hash = piece.hashCode();
        hash = 37 * hash + from.hashCode();
        hash = 37 * hash + to.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Move other = (Move) obj;
        return this.piece == other.piece && this.from == other.from && this.to == other.to;
    }
}
