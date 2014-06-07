package cz.janhrcek.chess.model;

import java.util.EnumSet;
import java.util.Set;

/**
 *
 * @author jhrcek
 */
public enum Square {

    A8, B8, C8, D8, E8, F8, G8, H8,
    A7, B7, C7, D7, E7, F7, G7, H7,
    A6, B6, C6, D6, E6, F6, G6, H6,
    A5, B5, C5, D5, E5, F5, G5, H5,
    A4, B4, C4, D4, E4, F4, G4, H4,
    A3, B3, C3, D3, E3, F3, G3, H3,
    A2, B2, C2, D2, E2, F2, G2, H2,
    A1, B1, C1, D1, E1, F1, G1, H1;

    private final long bitboard; // Bitboard with exacly one '1' in the place correcponding to this square
    private final int fileIndex; // 0-based indes of file (A has 0, B has 1, ..., H has 7)
    private final int rankIndex; // 0-based indes of rank (8th has 0, 7th has 1, ..., 1st has 7)
    private final boolean isDark;

    private static final Square[] SQUARES = {
        A8, B8, C8, D8, E8, F8, G8, H8,
        A7, B7, C7, D7, E7, F7, G7, H7,
        A6, B6, C6, D6, E6, F6, G6, H6,
        A5, B5, C5, D5, E5, F5, G5, H5,
        A4, B4, C4, D4, E4, F4, G4, H4,
        A3, B3, C3, D3, E3, F3, G3, H3,
        A2, B2, C2, D2, E2, F2, G2, H2,
        A1, B1, C1, D1, E1, F1, G1, H1
    };

    private Square() {
        bitboard = 1L << (63 - ordinal());
        fileIndex = ordinal() % 8;
        rankIndex = ordinal() / 8;
        isDark = (fileIndex + rankIndex) % 2 != 0;
    }

    public int getFileIndex() {
        return fileIndex;
    }

    public int getRankIndex() {
        return rankIndex;
    }

    public boolean isDark() {
        return isDark;
    }

    /* Package private implementation */
    static Square valueOf(int index) {
        return SQUARES[index];
    }

    long getBitBoard() {
        return bitboard;
    }

    int getIndex() {
        return ordinal();
    }

    static Set<Square> toSquareSet(long bitboard) {
        Set<Square> result = EnumSet.noneOf(Square.class);
        for (Square sq : Square.values()) {
            if ((bitboard & sq.getBitBoard()) != 0) { //Add square if bitboard has 1 bit in place corresponding to that square
                result.add(sq);
            }
        }
        return result;
    }
}
