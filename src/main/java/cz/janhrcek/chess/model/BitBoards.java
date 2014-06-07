package cz.janhrcek.chess.model;

import java.math.BigInteger;

/**
 * BitBoard is a 64-bit vector (represented as java long) capable of representing yes/no predicate about each of the 64
 * squares on the board. The first bit of the bitboard (MSB) corresponds to A8, 2nd bit to B8, ... last bit (LSB) to H1.
 *
 * @author jhrcek
 */
class BitBoards {

    /**
     * @param piece
     * @param from
     * @return long bit board representing squares to which given piece can go from given square on empty chessboard.
     */
    static long generateCanGoBitboard(Piece piece, Square from) {
        switch (piece) {
            case WHITE_PAWN:
            case BLACK_PAWN:
                return 0L; //TODO - implement pawn bitboards
            case WHITE_KNIGHT:
            case BLACK_KNIGHT:
                return toLong(toBitBoardString(KNIGHT_MOVEMENT_PATTERN, from));
            case WHITE_BISHOP:
            case BLACK_BISHOP:
                return toLong(toBitBoardString(BISHOP_MOVEMENT_PATTERN, from));
            case WHITE_ROOK:
            case BLACK_ROOK:
                return toLong(toBitBoardString(ROOK_MOVEMENT_PATTERN, from));
            case WHITE_QUEEN:
            case BLACK_QUEEN:
                return toLong(toBitBoardString(QUEEN_MOVEMENT_PATTERN, from));
            case WHITE_KING:
            case BLACK_KING:
                return toLong(toBitBoardString(KING_MOVEMENT_PATTERN, from));
            default:
                throw new IllegalStateException("Unexpected piece or square : piece =" + piece + ", square = " + from);
        }
    }

    /* Movement patterns. x means from square, 1 means valid to squares, - means invalid to squares*/
    private static final String[] KNIGHT_MOVEMENT_PATTERN = {
        "---------------",
        "---------------",
        "---------------",
        "---------------",
        "---------------",
        "------1-1------",
        "-----1---1-----",
        "-------x-------",
        "-----1---1-----",
        "------1-1------",
        "---------------",
        "---------------",
        "---------------",
        "---------------",
        "---------------"
    };

    private static final String[] BISHOP_MOVEMENT_PATTERN = {
        "1-------------1",
        "-1-----------1-",
        "--1---------1--",
        "---1-------1---",
        "----1-----1----",
        "-----1---1-----",
        "------1-1------",
        "-------x-------",
        "------1-1------",
        "-----1---1-----",
        "----1-----1----",
        "---1-------1---",
        "--1---------1--",
        "-1-----------1-",
        "1-------------1"
    };

    private static final String[] ROOK_MOVEMENT_PATTERN = {
        "-------1-------",
        "-------1-------",
        "-------1-------",
        "-------1-------",
        "-------1-------",
        "-------1-------",
        "-------1-------",
        "1111111x1111111",
        "-------1-------",
        "-------1-------",
        "-------1-------",
        "-------1-------",
        "-------1-------",
        "-------1-------",
        "-------1-------"
    };

    private static final String[] QUEEN_MOVEMENT_PATTERN = {
        "1------1------1",
        "-1-----1-----1-",
        "--1----1----1--",
        "---1---1---1---",
        "----1--1--1----",
        "-----1-1-1-----",
        "------111------",
        "1111111x1111111",
        "------111------",
        "-----1-1-1-----",
        "----1--1--1----",
        "---1---1---1---",
        "--1----1----1--",
        "-1-----1-----1-",
        "1------1------1"
    };

    private static final String[] KING_MOVEMENT_PATTERN = {
        "---------------",
        "---------------",
        "---------------",
        "---------------",
        "---------------",
        "---------------",
        "------111------",
        "------1x1------",
        "------111------",
        "---------------",
        "---------------",
        "---------------",
        "---------------",
        "---------------",
        "---------------"
    };

    private static String toBitBoardString(String[] movementPattern, Square from) {
        StringBuilder result = new StringBuilder(64);
        int initialRank = 7 - from.getRankIndex();
        int initialFile = 7 - from.getFileIndex();
        for (int rank = initialRank; rank < initialRank + 8; rank++) {
            result.append(movementPattern[rank].substring(initialFile, initialFile + 8));
        }
        return result.toString();
    }

    static long toLong(String str) {
        assert64Chars(str);
        long result = 0L;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '1') {
                result |= 1L << (63 - i);
            }
        }
        return result;
    }

    private static String toString(long bitboard) {
        return String.format("%064d", new BigInteger(Long.toBinaryString(bitboard)));
    }

    public static void print(long bitboard) {
        print(toString(bitboard));
    }

    // Print 64 character long string as 8x8 
    private static void print(String str) {
        assert64Chars(str);
        System.out.println("bitboard string : " + str);
        for (int i = 0; i < 8; i++) {
            System.out.println(str.substring(8 * i, 8 * i + 8));
        }
    }

    private static void assert64Chars(String str) {
        if (str.length() != 64) {
            throw new IllegalArgumentException("Must have 64 characters, but had " + str.length() + " : " + str);
        }
    }
}
