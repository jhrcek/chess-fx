package cz.janhrcek.chess.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class PositionTest {

    private final PositionFactory factory = new PositionFactoryImpl();

    @Test
    public void moveDoesntChangeOriginalPosition() {
        Position posBefore = factory.createInitialPosition();
        Move m = new Move(Piece.WHITE_PAWN, Square.E2, Square.E4);
        Position posAfter = posBefore.move(m);

        assertEquals(Piece.WHITE_PAWN, posBefore.getPiece(Square.E2));
        assertEquals(null, posBefore.getPiece(Square.E4));

        assertEquals(null, posAfter.getPiece(Square.E2));
        assertEquals(Piece.WHITE_PAWN, posAfter.getPiece(Square.E4));
    }

    @Test
    public void whiteToMoveInInitialPosition() {
        Position pos = factory.createInitialPosition();
        assertTrue(pos.isWhiteToMove());
    }

    @Test
    public void nullEnPassantSquareInInitialPosition() {
        Position pos = factory.createInitialPosition();
        Assert.assertNull(pos.getEnPassantSquare());
    }

    @Test
    public void moveSwitchesPlayerToMove() {
        Position pos = factory.createInitialPosition();
        pos = pos.move(new Move(Piece.WHITE_PAWN, Square.E2, Square.E4));
        assertFalse(pos.isWhiteToMove());

        pos = pos.move(new Move(Piece.BLACK_PAWN, Square.E7, Square.E5));
        assertTrue(pos.isWhiteToMove());
    }

    @Test
    public void initialPosHasAllCastlingsAvailable() {
        Position position = factory.createInitialPosition();
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
        assertEquals(expected, factory.createInitialPosition().toString());
    }

    @Test
    public void diffToTest() {
        Position first = factory.createInitialPosition();
        Position second = factory.createPosition("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2");
        List<Square> differences = first.diffTo(second);

        assertTrue(differences.size() == 6);
        assertTrue(differences.containsAll(
                Arrays.asList(Square.E2, Square.E4, Square.C7, Square.C5, Square.G1, Square.F3)));
    }
}
