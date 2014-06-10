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
        assertThat(A1.squaresTo(NORTH)).containsSequence(A2, A3, A4, A5, A6, A7, A8).hasSize(7);
        assertThat(A1.squaresTo(NORTH_EAST)).containsSequence(B2, C3, D4, E5, F6, G7, H8).hasSize(7);
        assertThat(A1.squaresTo(EAST)).containsSequence(B1, C1, D1, E1, F1, G1, H1).hasSize(7);
        assertThat(A1.squaresTo(SOUTH_EAST)).isEmpty();
        assertThat(A1.squaresTo(SOUTH)).isEmpty();
        assertThat(A1.squaresTo(SOUTH_WEST)).isEmpty();
        assertThat(A1.squaresTo(WEST)).isEmpty();
        assertThat(A1.squaresTo(NORTH_WEST)).isEmpty();

        assertThat(A8.squaresTo(NORTH)).isEmpty();
        assertThat(A8.squaresTo(NORTH_EAST)).isEmpty();
        assertThat(A8.squaresTo(EAST)).containsSequence(B8, C8, D8, E8, F8, G8, H8).hasSize(7);
        assertThat(A8.squaresTo(SOUTH_EAST)).containsSequence(B7, C6, D5, E4, F3, G2, H1).hasSize(7);
        assertThat(A8.squaresTo(SOUTH)).containsSequence(A7, A6, A5, A4, A3, A2, A1).hasSize(7);
        assertThat(A8.squaresTo(SOUTH_WEST)).isEmpty();
        assertThat(A8.squaresTo(WEST)).isEmpty();
        assertThat(A8.squaresTo(NORTH_WEST)).isEmpty();

        assertThat(E5.squaresTo(NORTH)).containsSequence(E6, E7, E8).hasSize(3);
        assertThat(E5.squaresTo(NORTH_EAST)).containsSequence(F6, G7, H8).hasSize(3);
        assertThat(E5.squaresTo(EAST)).containsSequence(F5, G5, H5).hasSize(3);
        assertThat(E5.squaresTo(SOUTH_EAST)).containsSequence(F4, G3, H2).hasSize(3);
        assertThat(E5.squaresTo(SOUTH)).containsSequence(E4, E3, E2, E1).hasSize(4);
        assertThat(E5.squaresTo(SOUTH_WEST)).containsSequence(D4, C3, B2, A1).hasSize(4);
        assertThat(E5.squaresTo(WEST)).containsSequence(D5, C5, B5, A5).hasSize(4);
        assertThat(E5.squaresTo(NORTH_WEST)).containsSequence(D6, C7, B8).hasSize(3);

        assertThat(H8.squaresTo(NORTH)).isEmpty();
        assertThat(H8.squaresTo(NORTH_EAST)).isEmpty();
        assertThat(H8.squaresTo(EAST)).isEmpty();
        assertThat(H8.squaresTo(SOUTH_EAST)).isEmpty();
        assertThat(H8.squaresTo(SOUTH)).containsSequence(H7, H6, H5, H4, H3, H2, H1).hasSize(7);
        assertThat(H8.squaresTo(SOUTH_WEST)).containsSequence(G7, F6, E5, D4, C3, B2, A1).hasSize(7);
        assertThat(H8.squaresTo(WEST)).containsSequence(G8, F8, E8, D8, C8, B8, A8).hasSize(7);
        assertThat(H8.squaresTo(NORTH_WEST)).isEmpty();

        assertThat(H1.squaresTo(NORTH)).containsSequence(H2, H3, H4, H5, H6, H7, H8).hasSize(7);
        assertThat(H1.squaresTo(NORTH_EAST)).isEmpty();
        assertThat(H1.squaresTo(EAST)).isEmpty();
        assertThat(H1.squaresTo(SOUTH_EAST)).isEmpty();
        assertThat(H1.squaresTo(SOUTH)).isEmpty();
        assertThat(H1.squaresTo(SOUTH_WEST)).isEmpty();
        assertThat(H1.squaresTo(WEST)).containsSequence(G1, F1, E1, D1, C1, B1, A1).hasSize(7);
        assertThat(H1.squaresTo(NORTH_WEST)).containsSequence(G2, F3, E4, D5, C6, B7, A8).hasSize(7);
    }
}
