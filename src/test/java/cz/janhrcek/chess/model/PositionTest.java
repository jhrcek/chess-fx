package cz.janhrcek.chess.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class PositionTest {

    private final PositionFactory factory = new PositionFactoryImpl();
    private static final Move[] MOVES = {
        new Move(Piece.WHITE_PAWN, Square.E2, Square.E4),
        new Move(Piece.BLACK_PAWN, Square.E7, Square.E5),
        new Move(Piece.WHITE_KNIGHT, Square.G1, Square.F3)
    };

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
        assertNull(pos.getEnPassantSquare());
    }

    @Test
    public void moveSwitchesPlayerToMove() {
        Position pos = factory.createInitialPosition();
        pos = pos.move(MOVES[0]);
        assertFalse(pos.isWhiteToMove());

        pos = pos.move(MOVES[1]);
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

    @Test
    public void halfmoveClockResetTo0AfterPawnMove() {
        Position p1 = factory.createInitialPosition();
        Position p2 = factory.createPositionFrom(p1, MOVES[0]);
        Position p3 = factory.createPositionFrom(p2, MOVES[1]);
        Position p4 = factory.createPositionFrom(p3, MOVES[2]);

        assertEquals(0, p1.getHalfMoveClock());
        assertEquals(0, p2.getHalfMoveClock());
        assertEquals(0, p3.getHalfMoveClock());
        assertEquals(1, p4.getHalfMoveClock());
    }

    @Test
    public void halfmoveClockResetTo0AfterCapture() {
        Position p1 = factory.createInitialPosition();
        Position p2 = factory.createPositionFrom(p1, MOVES[0]);
        Position p3 = factory.createPositionFrom(p2, MOVES[1]);
        Position p4 = factory.createPositionFrom(p3, MOVES[2]); //TODO - simplify with simpler initial position
        Position p5 = factory.createPositionFrom(p4, new Move(Piece.BLACK_KNIGHT, Square.G8, Square.F6));
        Position p6 = factory.createPositionFrom(p5, new Move(Piece.WHITE_KNIGHT, Square.F3, Square.E5)); //capture

        assertEquals(2, p5.getHalfMoveClock()); //2 moves that are not capture not pawn move
        assertEquals(0, p6.getHalfMoveClock()); //capture resets it to 0
    }

    @Test
    public void fullmoveNumberIncrementsAfterBlacksMove() {
        Position p1 = factory.createInitialPosition();
        Position p2 = factory.createPositionFrom(p1, MOVES[0]);
        Position p3 = factory.createPositionFrom(p2, MOVES[1]);
        Position p4 = factory.createPositionFrom(p3, MOVES[2]);
        Position p5 = factory.createPositionFrom(p4, new Move(Piece.BLACK_KNIGHT, Square.B8, Square.C6));

        assertEquals(1, p1.getFullMoveNumber());
        assertEquals(1, p2.getFullMoveNumber());
        assertEquals(2, p3.getFullMoveNumber());
        assertEquals(2, p4.getFullMoveNumber());
        assertEquals(3, p5.getFullMoveNumber());
    }
}
