package cz.janhrcek.chess.model;

import java.util.HashSet;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

public class GameTest {

    private static final Move[] MOVES = { //First few moves of Ruy Lopez
        new Move(Piece.WHITE_PAWN, Square.E2, Square.E4),
        new Move(Piece.BLACK_PAWN, Square.E7, Square.E5),
        new Move(Piece.WHITE_KNIGHT, Square.G1, Square.F3),
        new Move(Piece.BLACK_KNIGHT, Square.B8, Square.C6),
        new Move(Piece.WHITE_BISHOP, Square.F1, Square.B5)
    };

    @Test
    public void initialPositionTest() {
        Game game = GameFactory.createGame();
        Position initPos = game.getCurrentPosition();

        Assert.assertEquals(Piece.WHITE_KING, initPos.getPiece(Square.E1));
        Assert.assertTrue(initPos.isWhiteToMove());
        Assert.assertNull(initPos.getEnPassantSquare());
    }

    @Test
    public void addMoveChangesCurrentPosition() throws IllegalMoveException {
        Game game = GameFactory.createGame();
        Move move = new Move(Piece.WHITE_PAWN, Square.E2, Square.E4);
        game.makeMove(move);
        Position curPos = game.getCurrentPosition();

        Assert.assertEquals(Piece.WHITE_PAWN, curPos.getPiece(Square.E4));
        Assert.assertFalse(curPos.isWhiteToMove());
    }

    @Test
    public void eachMoveCreatesNewHistoryId() throws IllegalMoveException {
        Game game = GameFactory.createGame();
        int id1 = game.makeMove(MOVES[0]);
        int id2 = game.makeMove(MOVES[1]);
        int id3 = game.makeMove(MOVES[2]);

        Set<Integer> set = new HashSet<>();
        set.add(id1);
        set.add(id2);
        set.add(id3);

        Assert.assertEquals(3, set.size());
    }

    @Test
    public void navigateToInitialPosition() throws IllegalMoveException {
        Game game = GameFactory.createGame();
        for (Move move : MOVES) {
            game.makeMove(move);
        }
        game.navigateTo().initialPosition();
        Position position = game.getCurrentPosition();

        //Asert that navigation took us to initial position
        Assert.assertEquals(Piece.WHITE_PAWN, position.getPiece(Square.E2));
        Assert.assertTrue(position.isWhiteToMove());
    }

    @Test
    public void navigatingFromInitialToInitialDoesNothing() {
        Game game = GameFactory.createGame();
        Position p1 = game.getCurrentPosition();
        game.navigateTo().initialPosition();
        Position p2 = game.getCurrentPosition();

        Assert.assertTrue(p1.diffTo(p2).isEmpty());
    }
}
