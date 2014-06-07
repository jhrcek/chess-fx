package cz.janhrcek.chess.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 *
 * @author jhrcek
 */
public class PieceTest {

    @Test
    public void fromFenTest() {
        assertEquals(Piece.WHITE_PAWN, Piece.fromFen('P'));
        assertEquals(Piece.BLACK_PAWN, Piece.fromFen('p'));
        assertEquals(Piece.WHITE_KING, Piece.fromFen('K'));
        assertEquals(Piece.BLACK_QUEEN, Piece.fromFen('q'));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromFenThrowsIaeForNonFenLetter() {
        Piece.fromFen('x');
    }

    @Test
    public void canGoTest() {
        assertTrue(Piece.WHITE_KNIGHT.canGo(Square.A1, Square.B3));
        assertTrue(Piece.WHITE_KNIGHT.canGo(Square.A1, Square.C2));
    }
}
