package cz.janhrcek.chess.model;

import java.util.HashSet;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

public class GameTest {

    @Test
    public void initialStateTest() {
        Game game = GameFactory.createGame();
        Position initPos = game.getInitialPosition();

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
        int id1 = game.makeMove(new Move(Piece.WHITE_PAWN, Square.E2, Square.E4));
        int id2 = game.makeMove(new Move(Piece.BLACK_PAWN, Square.E7, Square.E5));
        int id3 = game.makeMove(new Move(Piece.WHITE_KNIGHT, Square.G1, Square.F3));

        Set<Integer> set = new HashSet<>();
        set.add(id1);
        set.add(id2);
        set.add(id3);
        System.out.println(set);
        Assert.assertEquals(3, set.size());
    }
}
