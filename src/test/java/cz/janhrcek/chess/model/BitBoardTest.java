package cz.janhrcek.chess.model;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class BitBoardTest {

    public BitBoardTest(Piece piece, Square from, long expected) {
        this.piece = piece;
        this.from = from;
        this.expected = expected;
    }

    private final Piece piece;
    private final Square from;
    private final long expected;

    @Test
    public void generateBitBoardTest() {
        long bitboard = BitBoards.generateCanGoBitboard(piece, from);
        Assert.assertEquals(expected, bitboard);
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {Piece.WHITE_KNIGHT, Square.A1, 0b00000000_00000000_00000000_00000000_00000000_01000000_00100000_00000000L},
            {Piece.WHITE_KNIGHT, Square.A8, 0b00000000_00100000_01000000_00000000_00000000_00000000_00000000_00000000L}
        //TODO: test few bitboards of other pieces
        });
    }
}
