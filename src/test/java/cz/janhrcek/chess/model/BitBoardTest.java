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
            {Piece.WHITE_KNIGHT, Square.A8, 0b00000000_00100000_01000000_00000000_00000000_00000000_00000000_00000000L},
            {Piece.BLACK_QUEEN, Square.E5, 0b01001001_00101010_00011100_11110111_00011100_00101010_01001001_10001000L},
            {Piece.WHITE_ROOK, Square.G2, 0b00000010_00000010_00000010_00000010_00000010_00000010_11111101_00000010L},
            {Piece.BLACK_BISHOP, Square.H5, 0b00001000_00000100_00000010_00000000_00000010_00000100_00001000_00010000L},
            {Piece.WHITE_KING, Square.C6, 0b00000000_01110000_01010000_01110000_00000000_00000000_00000000_00000000L}
        });
    }
}
