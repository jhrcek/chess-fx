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
        for (int i = 0; i <= matcher.groupCount(); i++) {
            System.out.println(i + ":" + matcher.group(i));

        }
        return new PositionImpl(null, true, true, true, true, true);
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
