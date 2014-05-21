package cz.janhrcek.chess.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PositionTest {

    private final PositionFactory pf = new PositionFactoryImpl();

    @Test
    public void moveDoesntChangeOriginalPosition() {
        Position posBefore = pf.initialPosition();
        Move m = new Move(Piece.WHITE_PAWN, Square.E2, Square.E4);
        Position posAfter = posBefore.move(m);

        assertEquals(Piece.WHITE_PAWN, posBefore.getPiece(Square.E2));
        assertEquals(null, posBefore.getPiece(Square.E4));

        assertEquals(null, posAfter.getPiece(Square.E2));
        assertEquals(Piece.WHITE_PAWN, posAfter.getPiece(Square.E4));
    }

    @Test
    public void whiteToMoveInInitialPosition() {
        Position pos = pf.initialPosition();
        assertTrue(pos.isWhiteToMove());
    }

    @Test
    public void moveSwitchesPlayerToMove() {
        Position pos = pf.initialPosition();
        pos = pos.move(new Move(Piece.WHITE_PAWN, Square.E2, Square.E4));
        assertFalse(pos.isWhiteToMove());

        pos = pos.move(new Move(Piece.BLACK_PAWN, Square.E7, Square.E5));
        assertTrue(pos.isWhiteToMove());
    }
}