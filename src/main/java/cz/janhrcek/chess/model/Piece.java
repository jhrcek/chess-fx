package cz.janhrcek.chess.model;

import java.util.Random;

/**
 *
 * @author jhrcek
 */
public enum Piece {

    WHITE_PAWN("P", ""),
    WHITE_KNIGHT("N", "N"),
    WHITE_BISHOP("B", "B"),
    WHITE_ROOK("R", "R"),
    WHITE_QUEEN("Q", "Q"),
    WHITE_KING("K", "K"),
    BLACK_PAWN("p", ""),
    BLACK_KNIGHT("n", "N"),
    BLACK_BISHOP("b", "B"),
    BLACK_ROOK("r", "R"),
    BLACK_QUEEN("q", "Q"),
    BLACK_KING("k", "K");

    private static final Random RANDOM = new Random();
    private static final Piece[] PIECES = {
        WHITE_PAWN, WHITE_KNIGHT, WHITE_BISHOP, WHITE_ROOK, WHITE_QUEEN, WHITE_KING,
        BLACK_PAWN, BLACK_KNIGHT, BLACK_BISHOP, BLACK_ROOK, BLACK_QUEEN, BLACK_KING
    };
    private static final String FEN_LETTERS = "PNBRQKpnbrqk";

    private final String fen; // Shortcut for piece name used in Forsythe-Edwards notation
    private final String san; // Shortcut for piece name used in Short Algebraic notation

    private Piece(String fenLetter, String sanLetter) {
        this.fen = fenLetter;
        this.san = sanLetter;
    }

    public String getFen() {
        return fen;
    }

    public String getSan() {
        return san;
    }

    public static Piece fromFen(String fenLetter) {
        int pieceIndex = FEN_LETTERS.indexOf(fenLetter);
        if (pieceIndex < 0) {
            throw new IllegalArgumentException("Invalid FEN letter: '" + fenLetter + "'");
        }
        return PIECES[pieceIndex];
    }

    public static Piece getRandomPiece() {
        return values()[RANDOM.nextInt(values().length)];
    }
}
