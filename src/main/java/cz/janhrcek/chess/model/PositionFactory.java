package cz.janhrcek.chess.model;

/**
 *
 * @author jhrcek
 */
public interface PositionFactory {

    Position initialPosition();

    Position parseFen(String fen);
}
