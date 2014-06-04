package cz.janhrcek.chess.model;

/**
 *
 * @author jhrcek
 */
public interface Game {

    Position getInitialPosition();

    Position getCurrentPosition();

    Position getPosition(int id);

    void focusPreviousPosition();

    public int makeMove(Move move) throws IllegalMoveException;
}
