package cz.janhrcek.chess.model;

import static cz.janhrcek.chess.model.Square.*;
import static cz.janhrcek.chess.model.Square.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;
import org.junit.Test;

public class SquareTest {

    @Test
    public void sqareMapsToIndex() {
        assertEquals(0, A8.getIndex());
        assertEquals(7, H8.getIndex());
        assertEquals(56, A1.getIndex());
        assertEquals(63, H1.getIndex());
    }

    @Test
    public void indexMapsToSquare() {
        assertEquals(A8, Square.valueOf(0));
        assertEquals(H8, Square.valueOf(7));
        assertEquals(A1, Square.valueOf(56));
        assertEquals(H1, Square.valueOf(63));
    }

    @Test
    public void fileIndexTest() {
        assertEquals(0, A8.getFileIndex());
        assertEquals(0, A1.getFileIndex());
        assertEquals(4, E5.getFileIndex());
        assertEquals(7, H2.getFileIndex());
    }

    @Test
    public void rankIndexTest() {
        assertEquals(0, A8.getRankIndex());
        assertEquals(7, A1.getRankIndex());
        assertEquals(3, E5.getRankIndex());
        assertEquals(6, H2.getRankIndex());
    }

    @Test
    public void isDarkTest() {
        assertTrue(A1.isDark());
        assertTrue(E3.isDark());
        assertTrue(H6.isDark());

        assertFalse(H1.isDark());
        assertFalse(E6.isDark());
        assertFalse(B3.isDark());
    }

    @Test
    public void toSquareSetTest() {
        long bbRepresentingA8 = 0b10000000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        Set<Square> a8Set = Square.toSquareSet(bbRepresentingA8);
        assertThat(a8Set)
                .contains(A8)
                .hasSize(1);

        long bbRepresentingAFile = 0b10000000_10000000_10000000_10000000_10000000_10000000_10000000_10000000L;
        Set<Square> aFileSet = Square.toSquareSet(bbRepresentingAFile);
        assertThat(aFileSet)
                .contains(A1, A2, A3, A4, A5, A6, A7, A8)
                .hasSize(8);

        long bbRepresenting3rdRank = 0b00000000_00000000_00000000_00000000_00000000_11111111_00000000_00000000L;
        Set<Square> thirdRankSet = Square.toSquareSet(bbRepresenting3rdRank);
        assertThat(thirdRankSet)
                .contains(A3, B3, C3, D3, E3, F3, G3, H3)
                .hasSize(8);
    }

    @Test
    public void squareIterationTest() {
        assertThat(A1.squaresTo(NORTH))
                .contains(A2, A3, A4, A5, A6, A7, A8)
                .hasSize(7);

        //TODO - more tests for square iteration
    }
}
