package cz.janhrcek.chess.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FenParserImpl implements FenParser {

    @Override
    public Position parse(String fen) {
        if (!isValidFen(fen)) {
            throw new IllegalArgumentException("Invalid FEN : " + fen);
        }

        Matcher matcher = VALID_FEN.matcher(fen);
        matcher.find();

        // Pieces
        Piece[] board = new Piece[64];
        String[] ranks = matcher.group(1).split("/"); //We know it has 8 ranks, cause it passed isValidFen()
        for (int rankIdx = 0; rankIdx < 8; rankIdx++) {
            String rank = ranks[rankIdx];
            int colIdx = 0;
            for (int i = 0; i < rank.length(); i++) {
                char letter = rank.charAt(i);
                if (letter >= '1' && letter <= '8') {
                    colIdx += letter - '0';
                } else {
                    board[8 * rankIdx + colIdx] = Piece.fromFen(letter);
                    colIdx++;
                }
            }
        }

        // Player to move
        boolean isWhiteToMove = "w".equals(matcher.group(3));

        // Castling availabilities
        String castling = matcher.group(4);
        boolean canWK = castling.indexOf('K') != -1;
        boolean canWQ = castling.indexOf('Q') != -1;
        boolean canBK = castling.indexOf('k') != -1;
        boolean canBQ = castling.indexOf('q') != -1;

        // En passant square
        String enPassant = matcher.group(6);
        Square enPassantSquare = enPassant.length() == 1 ? null : Square.valueOf(enPassant.toUpperCase());

        // Halfmove clock
        int halfmove = Integer.parseInt(matcher.group(8));

        // Fullmove number
        int fullmove = Integer.parseInt(matcher.group(9));

        return new PositionImpl(board, isWhiteToMove, canWK, canWQ, canBK, canBQ, enPassantSquare, halfmove, fullmove);
    }

    @Override
    public String format(Position position) {
        return "";
    }

    private static final Pattern VALID_FEN = Pattern.compile("(([pnbrqkPNBRQK1-8]{1,8}/){7}[pnbrqkPNBRQK1-8]{1,8}) "
            + "(b|w) ((K?Q?k?q?)|\\-) (([a-h][36])|\\-) (\\d*) (\\d*)");

    @Override
    public boolean isValidFen(String fen) {
        Matcher matcher = VALID_FEN.matcher(fen);
        if (!matcher.matches()) {
            System.err.printf("Invalid FEN '%s' - doesn't match pattern : %s%n", fen, VALID_FEN.pattern());
            return false;
        }

        String board = matcher.group(1);
        for (String rank : board.split("/")) {
            if (!isValidRank(rank)) {
                System.err.printf("Invalid FEN '%s' - rank '%s' does not add up to 8%n", fen, rank);
                return false;
            }
        }

        if (board.indexOf('k') < 0 || board.indexOf('K') < 0) {
            System.err.printf("Invalid FEN '%s' - king is missing", fen);
            return false;
        }

        return true;
    }

    /**
     * @param rank string representing 1 rank from the FEN string
     * @return true, if rank "adds up to 8", i.e. if number of pieces in the rank + number of empty squares add up to 8
     */
    private boolean isValidRank(String rank) {
        int count = 0;
        char letter;
        for (int i = 0; i < rank.length(); i++) {
            letter = rank.charAt(i);
            if (letter >= '1' && letter <= '8') {
                count += letter - '0';
            } else {
                count++;
            }
        }
        return count == 8;
    }
}
