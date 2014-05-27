package cz.janhrcek.chess.model;

/**
 *
 * @author jhrcek
 */
public interface PositionFactory {

    Position initialPosition();

    Position fromPosition(Position pos, Move move);
}
