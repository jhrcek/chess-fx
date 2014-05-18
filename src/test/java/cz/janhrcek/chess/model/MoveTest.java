package cz.janhrcek.chess.model;

import static cz.janhrcek.chess.model.Piece.BLACK_QUEEN;
import static cz.janhrcek.chess.model.Piece.WHITE_PAWN;
import static cz.janhrcek.chess.model.Square.A5;
import static cz.janhrcek.chess.model.Square.D8;
import static cz.janhrcek.chess.model.Square.E2;
import static cz.janhrcek.chess.model.Square.E4;

import org.junit.Assert;
import org.junit.Test;

public class MoveTest {

    @Test
    public void toStringTest() {
        Assert.assertEquals("e2-e4", new Move(WHITE_PAWN, E2, E4).toString());
        Assert.assertEquals("Qd8-a5", new Move(BLACK_QUEEN, D8, A5).toString());
    }
}
