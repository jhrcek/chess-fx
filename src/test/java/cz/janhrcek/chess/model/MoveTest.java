package cz.janhrcek.chess.model;

import static cz.janhrcek.chess.model.Piece.*;
import static cz.janhrcek.chess.model.Square.*;

import org.junit.Assert;
import org.junit.Test;

public class MoveTest {

    @Test
    public void toStringTest() {
        Assert.assertEquals("e2-e4", new Move(WHITE_PAWN, E2, E4).toString());
        Assert.assertEquals("Qd8-a5", new Move(BLACK_QUEEN, D8, A5).toString());
    }

    @Test
    public void equalsTest() {
        Assert.assertTrue(new Move(WHITE_PAWN, E2, E4).equals(new Move(WHITE_PAWN, E2, E4)));

        Assert.assertFalse(new Move(WHITE_KING, E2, E4).equals(new Move(WHITE_PAWN, E2, E4))); //diff in piece
        Assert.assertFalse(new Move(WHITE_PAWN, E2, E4).equals(new Move(WHITE_PAWN, E3, E4))); //diff in from
        Assert.assertFalse(new Move(WHITE_PAWN, E2, E4).equals(new Move(WHITE_PAWN, E2, E3))); //diff in to

        //Promotion
        Assert.assertTrue(new Promotion(WHITE_PAWN, E2, E4, WHITE_QUEEN)
                .equals(new Promotion(WHITE_PAWN, E2, E4, WHITE_QUEEN)));
        Assert.assertFalse(new Promotion(WHITE_PAWN, E2, E4, WHITE_QUEEN)
                .equals(new Promotion(WHITE_PAWN, E2, E4, WHITE_ROOK))); //diff in promo

        //Promo not equal to normal move
        Assert.assertFalse(new Promotion(WHITE_PAWN, E2, E4, WHITE_QUEEN).equals(new Move(WHITE_PAWN, E2, E4)));
        Assert.assertFalse(new Move(WHITE_PAWN, E2, E4).equals(new Promotion(WHITE_PAWN, E2, E4, WHITE_QUEEN)));
    }
}
