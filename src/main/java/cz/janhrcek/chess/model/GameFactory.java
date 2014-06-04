package cz.janhrcek.chess.model;

/**
 *
 * @author jhrcek
 */
public class GameFactory {

    public static Game createGame() {
        return new GameImpl(new PositionFactoryImpl());
    }
}
