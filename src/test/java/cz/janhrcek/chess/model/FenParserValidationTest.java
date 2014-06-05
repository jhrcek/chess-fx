package cz.janhrcek.chess.model;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class FenParserValidationTest {

    public FenParserValidationTest(String fen, Boolean isValid) {
        this.fen = fen;
        this.isValid = isValid;
    }

    private final String fen;
    private final Boolean isValid;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", true},
            {"rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1", true},
            {"rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2", true},
            {"rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2", true},
            //invalid
            {"rnbqkbn/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2", false},
            {"rnbqkbnr/pp1pppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2", false},
            {"rnbqkbnr/pp1ppppp/7/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2", false},
            {"rnbqkbnr/pp1ppppp/8/2p5k/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2", false},
            {"rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b Khq - 1 2", false},
            {"rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq a7 1 2", false},
            {"rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1", false},
            {"rnbqkbnr/pp1ppppp/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2", false}
        });
    }

    @Test
    public void isValidTest() {
        Assert.assertTrue(isValid == new FenParserImpl().isValidFen(fen));
    }
}
