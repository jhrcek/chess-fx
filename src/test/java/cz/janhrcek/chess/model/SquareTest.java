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
}
