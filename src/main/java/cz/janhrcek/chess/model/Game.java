package cz.janhrcek.chess.model;

/**
 *
 * @author jhrcek
 */
public interface Game {

    GameNavigator navigateTo();

    Position getCurrentPosition();

    int makeMove(Move move) throws IllegalMoveException;
}
