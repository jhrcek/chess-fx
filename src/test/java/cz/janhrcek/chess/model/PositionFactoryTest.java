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
        PositionFactory pf = new PositionFactoryImpl();
        //Position with justs rooks and kings with all the castling rights
        Position pos = pf.createPosition("r111k11r/8/8/8/8/8/8/R111K11R w KQkq - 0 1");
        assertTrue(pos.canCastleWK() && pos.canCastleWQ() && pos.canCastleBK() && pos.canCastleBQ());

        //Moving A1 Rook looses only WQ castling right
        Position p1 = pos.move(new Move(Piece.WHITE_ROOK, Square.A1, Square.A2));
        assertTrue(p1.canCastleWK() && p1.canCastleBK() && p1.canCastleBQ());
        assertFalse(p1.canCastleWQ());

        //Moving H1 Rook looses only WK castling right
        Position p2 = pos.move(new Move(Piece.WHITE_ROOK, Square.H1, Square.H8));
        assertTrue(p2.canCastleWQ() && p2.canCastleBK() && p2.canCastleBQ());
        assertFalse(p2.canCastleWK());

        //Moving H8 Rook looses only BK castling right
        Position p3 = pos.move(new Move(Piece.BLACK_ROOK, Square.H8, Square.G8));
        assertTrue(p3.canCastleWK() && p3.canCastleWQ() && p3.canCastleBQ());
        assertFalse(p3.canCastleBK());

        //Moving H8 Rook looses only BQ castling right
        Position p4 = pos.move(new Move(Piece.BLACK_ROOK, Square.A8, Square.A5));
        assertTrue(p4.canCastleWK() && p4.canCastleWQ() && p4.canCastleBK());
        assertFalse(p4.canCastleBQ());
    }
}
