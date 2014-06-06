package cz.janhrcek.chess.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FenParserTest {

    @Test
    public void parseInitialPositionFen() {
        FenParser parser = new FenParserImpl();
        Position position = parser.parse("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");

        assertEquals(Piece.BLACK_ROOK, position.getPiece(Square.A8));
        assertEquals(Piece.BLACK_KNIGHT, position.getPiece(Square.B8));
        assertEquals(Piece.BLACK_BISHOP, position.getPiece(Square.C8));
        assertEquals(Piece.BLACK_QUEEN, position.getPiece(Square.D8));
        assertEquals(Piece.BLACK_KING, position.getPiece(Square.E8));
        assertEquals(Piece.BLACK_PAWN, position.getPiece(Square.A7));
        assertEquals(Piece.BLACK_PAWN, position.getPiece(Square.H7));

        assertEquals(null, position.getPiece(Square.A6));
        assertEquals(null, position.getPiece(Square.E4));
        assertEquals(null, position.getPiece(Square.H3));

        assertEquals(Piece.WHITE_ROOK, position.getPiece(Square.A1));
        assertEquals(Piece.WHITE_KNIGHT, position.getPiece(Square.B1));
        assertEquals(Piece.WHITE_BISHOP, position.getPiece(Square.C1));
        assertEquals(Piece.WHITE_QUEEN, position.getPiece(Square.D1));
        assertEquals(Piece.WHITE_KING, position.getPiece(Square.E1));
        assertEquals(Piece.WHITE_PAWN, position.getPiece(Square.A2));
        assertEquals(Piece.WHITE_PAWN, position.getPiece(Square.H2));

        assertTrue(position.isWhiteToMove());

        assertTrue(position.canCastleWK());
        assertTrue(position.canCastleWQ());
        assertTrue(position.canCastleBK());
        assertTrue(position.canCastleBQ());

        assertNull(position.getEnPassantSquare());

        assertEquals(0, position.getHalfMoveClock());
        assertEquals(1, position.getFullMoveNumber());
    }
}
