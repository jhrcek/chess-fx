package cz.janhrcek.chess.model;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author jhrcek
 */
public class PieceTest {

    @Test
    public void fromFenTest() {
        Assert.assertEquals(Piece.WHITE_PAWN, Piece.fromFen('P'));
        Assert.assertEquals(Piece.BLACK_PAWN, Piece.fromFen('p'));
        Assert.assertEquals(Piece.WHITE_KING, Piece.fromFen('K'));
        Assert.assertEquals(Piece.BLACK_QUEEN, Piece.fromFen('q'));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromFenThrowsIaeForNonFenLetter() {
        Piece.fromFen('x');
    }
}
