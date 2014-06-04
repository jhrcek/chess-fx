package cz.janhrcek.chess.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

public class PositionTest {

    private final PositionFactory pf = new PositionFactoryImpl();

    @Test
    public void moveDoesntChangeOriginalPosition() {
        Position posBefore = pf.createInitialPosition();
        Move m = new Move(Piece.WHITE_PAWN, Square.E2, Square.E4);
        Position posAfter = posBefore.move(m);

        assertEquals(Piece.WHITE_PAWN, posBefore.getPiece(Square.E2));
        assertEquals(null, posBefore.getPiece(Square.E4));

        assertEquals(null, posAfter.getPiece(Square.E2));
        assertEquals(Piece.WHITE_PAWN, posAfter.getPiece(Square.E4));
    }

    @Test
    public void whiteToMoveInInitialPosition() {
        Position pos = pf.createInitialPosition();
        assertTrue(pos.isWhiteToMove());
    }
    
    @Test
    public void nullEnPassantSquareInInitialPosition() {
        Position pos = pf.createInitialPosition();
        Assert.assertNull(pos.getEnPassantSquare());
    }

    @Test
    public void moveSwitchesPlayerToMove() {
        Position pos = pf.createInitialPosition();
        pos = pos.move(new Move(Piece.WHITE_PAWN, Square.E2, Square.E4));
        assertFalse(pos.isWhiteToMove());

        pos = pos.move(new Move(Piece.BLACK_PAWN, Square.E7, Square.E5));
        assertTrue(pos.isWhiteToMove());
    }

    @Test
    public void initialPosHasAllCastlingsAvailable() {
        PositionFactory pf = new PositionFactoryImpl();
        Position position = pf.createInitialPosition();
        assertTrue(position.canCastleWK());
        assertTrue(position.canCastleWQ());
        assertTrue(position.canCastleWQ());
        assertTrue(position.canCastleBQ());
    }

    @Test
    public void toStringTest() {
        String expected
                = "┌───┬───┬───┬───┬───┬───┬───┬───┐\n"
                + "│ r │ n │ b │ q │ k │ b │ n │ r │\n"
                + "├───┼───┼───┼───┼───┼───┼───┼───┤\n"
                + "│ p │ p │ p │ p │ p │ p │ p │ p │\n"
                + "├───┼───┼───┼───┼───┼───┼───┼───┤\n"
                + "│   │   │   │   │   │   │   │   │\n"
                + "├───┼───┼───┼───┼───┼───┼───┼───┤\n"
                + "│   │   │   │   │   │   │   │   │\n"
                + "├───┼───┼───┼───┼───┼───┼───┼───┤\n"
                + "│   │   │   │   │   │   │   │   │\n"
                + "├───┼───┼───┼───┼───┼───┼───┼───┤\n"
                + "│   │   │   │   │   │   │   │   │\n"
                + "├───┼───┼───┼───┼───┼───┼───┼───┤\n"
                + "│ P │ P │ P │ P │ P │ P │ P │ P │\n"
                + "├───┼───┼───┼───┼───┼───┼───┼───┤\n"
                + "│ R │ N │ B │ Q │ K │ B │ N │ R │\n"
                + "└───┴───┴───┴───┴───┴───┴───┴───┘\n";
        assertEquals(expected, pf.createInitialPosition().toString());
    }
}
