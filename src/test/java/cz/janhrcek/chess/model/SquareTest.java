package cz.janhrcek.chess.model;

import org.junit.Assert;
import org.junit.Test;

public class SquareTest {

    @Test
    public void sqareMapsToIndex() {
        Assert.assertEquals(0, Square.A8.getIndex());
        Assert.assertEquals(7, Square.H8.getIndex());
        Assert.assertEquals(56, Square.A1.getIndex());
        Assert.assertEquals(63, Square.H1.getIndex());
    }

    @Test
    public void indexMapsToSquare() {
        Assert.assertEquals(Square.A8, Square.valueOf(0));
        Assert.assertEquals(Square.H8, Square.valueOf(7));
        Assert.assertEquals(Square.A1, Square.valueOf(56));
        Assert.assertEquals(Square.H1, Square.valueOf(63));
    }

    @Test
    public void fileIndexTest() {
        Assert.assertEquals(0, Square.A8.getFileIndex());
        Assert.assertEquals(0, Square.A1.getFileIndex());
        Assert.assertEquals(4, Square.E5.getFileIndex());
        Assert.assertEquals(7, Square.H2.getFileIndex());
    }

    @Test
    public void rankIndexTest() {
        Assert.assertEquals(0, Square.A8.getRankIndex());
        Assert.assertEquals(7, Square.A1.getRankIndex());
        Assert.assertEquals(3, Square.E5.getRankIndex());
        Assert.assertEquals(6, Square.H2.getRankIndex());
    }

    @Test
    public void isDarkTest() {
        Assert.assertTrue(Square.A1.isDark());
        Assert.assertTrue(Square.E3.isDark());
        Assert.assertTrue(Square.H6.isDark());

        Assert.assertFalse(Square.H1.isDark());
        Assert.assertFalse(Square.E6.isDark());
        Assert.assertFalse(Square.B3.isDark());
    }
}
