package cz.janhrcek.chess.model;

/**
 *
 * @author jhrcek
 */
public interface Game {

    GameNavigator navigateTo();

    Position getCurrentPosition();

    public int makeMove(Move move) throws IllegalMoveException;
}
