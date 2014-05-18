package cz.janhrcek.chess.model;

import java.util.List;

/**
 *
 * @author jhrcek
 */
public interface Rules {

    List<Move> getLegalMoves(Position position);

    boolean isLegalMove(Position position, Move move);
}
