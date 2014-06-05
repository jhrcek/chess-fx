package cz.janhrcek.chess.model;

/**
 *
 * @author jhrcek
 */
public interface GameNavigator {

    void initialPosition();

    void previousPosition();

    void positionAfter(Move m);

    void positionWithId(int id);
}
