package cz.janhrcek.chess.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PositionFactoryTest {

    @Test
    public void defaultCtorHasPiecesInInitialPosition() {
        PositionFactory pf = new PositionFactoryImpl();
        Position position = pf.initialPosition();
        assertEquals(Piece.WHITE_PAWN, position.getPiece(Square.E2));
        assertEquals(Piece.WHITE_ROOK, position.getPiece(Square.A1));
        assertEquals(Piece.WHITE_QUEEN, position.getPiece(Square.D1));
        assertEquals(Piece.WHITE_KING, position.getPiece(Square.E1));

        assertEquals(Piece.BLACK_PAWN, position.getPiece(Square.A7));
        assertEquals(Piece.BLACK_ROOK, position.getPiece(Square.H8));
        assertEquals(Piece.BLACK_QUEEN, position.getPiece(Square.D8));
        assertEquals(Piece.BLACK_KING, position.getPiece(Square.E8));
    }
}
