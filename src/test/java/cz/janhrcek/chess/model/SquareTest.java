package cz.janhrcek.chess.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Set;
import org.junit.Test;

public class SquareTest {

    @Test
    public void sqareMapsToIndex() {
        assertEquals(0, Square.A8.getIndex());
        assertEquals(7, Square.H8.getIndex());
        assertEquals(56, Square.A1.getIndex());
        assertEquals(63, Square.H1.getIndex());
    }

    @Test
    public void indexMapsToSquare() {
        assertEquals(Square.A8, Square.valueOf(0));
        assertEquals(Square.H8, Square.valueOf(7));
        assertEquals(Square.A1, Square.valueOf(56));
        assertEquals(Square.H1, Square.valueOf(63));
    }

    @Test
    public void fileIndexTest() {
        assertEquals(0, Square.A8.getFileIndex());
        assertEquals(0, Square.A1.getFileIndex());
        assertEquals(4, Square.E5.getFileIndex());
        assertEquals(7, Square.H2.getFileIndex());
    }

    @Test
    public void rankIndexTest() {
        assertEquals(0, Square.A8.getRankIndex());
        assertEquals(7, Square.A1.getRankIndex());
        assertEquals(3, Square.E5.getRankIndex());
        assertEquals(6, Square.H2.getRankIndex());
    }

    @Test
    public void isDarkTest() {
        assertTrue(Square.A1.isDark());
        assertTrue(Square.E3.isDark());
        assertTrue(Square.H6.isDark());

        assertFalse(Square.H1.isDark());
        assertFalse(Square.E6.isDark());
        assertFalse(Square.B3.isDark());
    }

    @Test
    public void toSquareSetTest() {
        long bbRepresentingA8 = 0b10000000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        Set<Square> a8Set = Square.toSquareSet(bbRepresentingA8);
        assertEquals(1, a8Set.size());
        assertTrue(a8Set.contains(Square.A8));

        long bbRepresentingAFile = 0b10000000_10000000_10000000_10000000_10000000_10000000_10000000_10000000L;
        Set<Square> aFileSet = Square.toSquareSet(bbRepresentingAFile);
        assertEquals(8, aFileSet.size());
        assertTrue(aFileSet.containsAll(
                Arrays.asList(Square.A1, Square.A2, Square.A3, Square.A4, Square.A5, Square.A6, Square.A7, Square.A8)));

        long bbRepresenting3rdRank = 0b00000000_00000000_00000000_00000000_00000000_11111111_00000000_00000000L;
        Set<Square> thirdRankSet = Square.toSquareSet(bbRepresenting3rdRank);
        assertEquals(8, thirdRankSet.size());
        assertTrue(thirdRankSet.containsAll(
                Arrays.asList(Square.A3, Square.B3, Square.C3, Square.D3, Square.E3, Square.F3, Square.G3, Square.H3)));
    }
}
