package cz.janhrcek.chess.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PositionTest {

    @Test
    public void defaultCtorHasPiecesInInitialPosition() {
        Position position = new PositionImpl();
        assertEquals(Piece.WHITE_PAWN, position.getPiece(Square.E2));
        assertEquals(Piece.WHITE_ROOK, position.getPiece(Square.A1));
        assertEquals(Piece.WHITE_QUEEN, position.getPiece(Square.D1));
        assertEquals(Piece.WHITE_KING, position.getPiece(Square.E1));

        assertEquals(Piece.BLACK_PAWN, position.getPiece(Square.A7));
        assertEquals(Piece.BLACK_ROOK, position.getPiece(Square.H8));
        assertEquals(Piece.BLACK_QUEEN, position.getPiece(Square.D8));
        assertEquals(Piece.BLACK_KING, position.getPiece(Square.E8));
    }

    @Test
    public void moveDoesntChangeOriginalPosition() {
        Position posBefore = new PositionImpl();
        Move m = new Move(Piece.WHITE_PAWN, Square.E2, Square.E4);
        Position posAfter = posBefore.move(m);

        assertEquals(Piece.WHITE_PAWN, posBefore.getPiece(Square.E2));
        assertEquals(null, posBefore.getPiece(Square.E4));

        assertEquals(null, posAfter.getPiece(Square.E2));
        assertEquals(Piece.WHITE_PAWN, posAfter.getPiece(Square.E4));
    }
}
