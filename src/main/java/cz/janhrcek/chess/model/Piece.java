package cz.janhrcek.chess.model;

import java.util.Random;
import java.util.Set;

/**
 *
 * @author jhrcek
 */
public enum Piece {

    WHITE_PAWN(true, "P", ""),
    WHITE_KNIGHT(true, "N", "N"),
    WHITE_BISHOP(true, "B", "B"),
    WHITE_ROOK(true, "R", "R"),
    WHITE_QUEEN(true, "Q", "Q"),
    WHITE_KING(true, "K", "K"),
    BLACK_PAWN(false, "p", ""),
    BLACK_KNIGHT(false, "n", "N"),
    BLACK_BISHOP(false, "b", "B"),
    BLACK_ROOK(false, "r", "R"),
    BLACK_QUEEN(false, "q", "Q"),
    BLACK_KING(false, "k", "K");

    private static final Random RANDOM = new Random();
    private static final Piece[] PIECES = {
        WHITE_PAWN, WHITE_KNIGHT, WHITE_BISHOP, WHITE_ROOK, WHITE_QUEEN, WHITE_KING,
        BLACK_PAWN, BLACK_KNIGHT, BLACK_BISHOP, BLACK_ROOK, BLACK_QUEEN, BLACK_KING
    };
    private static final String FEN_LETTERS = "PNBRQKpnbrqk";

    private final boolean isWhite;
    private final String fen; // Shortcut for piece name used in Forsythe-Edwards notation
    private final String san; // Shortcut for piece name used in Short Algebraic notation
    private long[] canGoBitBoards;

    private Piece(boolean isWhite, String fenLetter, String sanLetter) {
        this.isWhite = isWhite;
        this.fen = fenLetter;
        this.san = sanLetter;
    }

    public String getFen() {
        return fen;
    }

    public String getSan() {
        return san;
    }

    public static Piece fromFen(char fenLetter) {
        int pieceIndex = FEN_LETTERS.indexOf(fenLetter);
        if (pieceIndex < 0) {
            throw new IllegalArgumentException("Invalid FEN letter: '" + fenLetter + "'");
        }
        return PIECES[pieceIndex];
    }

    public boolean isWhite() {
        return isWhite;
    }

    public static Piece getRandomPiece() {
        return values()[RANDOM.nextInt(values().length)];
    }

    public boolean canGo(Square from, Square to) {
        lazyInitCanGoBitboards();
        long canGoBB = canGoBitBoards[from.getIndex()];
        return (canGoBB & to.getBitBoard()) != 0;
    }

    public Set<Square> whereCanGoFrom(Square from) {
        lazyInitCanGoBitboards();
        return Square.toSquareSet(canGoBitBoards[from.getIndex()]);
    }

    private void lazyInitCanGoBitboards() {
        // TODO get rid of lazy initialization.  As it is now it can however not be placed into piece constructor,
        // because then we would have an initialization cycle (Piece constructor needs to call 
        // BitBoards.generateBitboard, which swichtes on Piece, which is not yet initialized
        if (canGoBitBoards == null) {
            canGoBitBoards = new long[Square.values().length];
            for (Square square : Square.values()) {
                canGoBitBoards[square.getIndex()] = BitBoards.generateCanGoBitboard(this, square);
            }
        }
    }
}
