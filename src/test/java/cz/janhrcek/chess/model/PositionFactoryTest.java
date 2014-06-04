package cz.janhrcek.chess.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PositionFactoryTest {

    @Test
    public void defaultCtorHasPiecesInInitialPosition() {
        PositionFactory pf = new PositionFactoryImpl();
        Position position = pf.createInitialPosition();
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
    public void movingKingLoosesBothCastleRights() {
        PositionFactory pf = new PositionFactoryImpl();
        Position pos0 = pf.createInitialPosition();

        Position pos1 = pos0.move(new Move(Piece.WHITE_KING, Square.E1, Square.E2));
        assertFalse(pos1.canCastleWK());
        assertFalse(pos1.canCastleWQ());
        assertTrue(pos1.canCastleBK()); //Black doesn't loose them
        assertTrue(pos1.canCastleBQ());

        Position pos2 = pos1.move(new Move(Piece.BLACK_KING, Square.E8, Square.D8));
        assertFalse(pos2.canCastleBK());
        assertFalse(pos2.canCastleBQ());
    }

    @Test
    public void movingRookLosesCastleRight() {
        //TODO
    }
}
