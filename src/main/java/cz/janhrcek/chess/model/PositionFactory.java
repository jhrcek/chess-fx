package cz.janhrcek.chess.model;

/**
 *
 * @author jhrcek
 */
public interface PositionFactory {

    Position createInitialPosition();

    Position createPositionFrom(Position pos, Move move);
    
    Position createPosition(String fen);
}
