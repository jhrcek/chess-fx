package cz.janhrcek.chess.model;

/**
 *
 * @author jhrcek
 */
public interface FenParser {

    String format(Position position);

    boolean isValidFen(String fen);

    Position parse(String fen);
}
