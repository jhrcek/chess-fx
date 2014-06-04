package cz.janhrcek.chess.ui;

import cz.janhrcek.chess.model.Piece;
import cz.janhrcek.chess.ui.client.UiPrototype;
import java.util.EnumMap;
import java.util.Map;
import javafx.scene.image.Image;

/**
 *
 * @author jhrcek
 */
public class PieceImages {

    private static final Map<Piece, Image> PIECE_IMAGES = new EnumMap<>(Piece.class);

    static {
        for (Piece piece : Piece.values()) {
            PIECE_IMAGES.put(piece,
                    new Image(UiPrototype.class.getResource(String.format("/pieces/basic/%s.png", piece)).toString()));
        }
    }

    public static Image get(Piece piece) {
        return PIECE_IMAGES.get(piece);
    }
}
