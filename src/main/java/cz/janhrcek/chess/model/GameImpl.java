package cz.janhrcek.chess.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jhrcek
 */
public class GameImpl implements Game {

    private final PositionFactory positionFactory;
    private final GameNavigatorImpl navigator = new GameNavigatorImpl();

    public GameImpl(PositionFactory positionFactory) {
        this.positionFactory = positionFactory;
        Position initialPos = positionFactory.createInitialPosition();
        navigator.addToHistory(null, initialPos);
    }

    @Override
    public Position getCurrentPosition() {
        return navigator.currentNode.getPosition();
    }

    @Override
    public int makeMove(Move move) throws IllegalMoveException {
        Position curPos = getCurrentPosition();
        Position newPos = positionFactory.createPositionFrom(curPos, move);
        return navigator.addToHistory(move, newPos);
    }

    @Override
    public GameNavigator navigateTo() {
        return navigator;
    }

    private static class GameNavigatorImpl implements GameNavigator {

        private final Map<Integer, HistoryNode> history = new HashMap<>();
        private HistoryNode currentNode;
        private int nodeIdGenerator = 0;

        private int addToHistory(Move moveLeadingToNewPosition, Position newPosition) {
            // When adding initial position, currentNode & moveLeadingToThisNode will be null (intended)
            currentNode = new HistoryNode(currentNode, moveLeadingToNewPosition, newPosition);
            history.put(nodeIdGenerator, currentNode);
            return nodeIdGenerator++;
        }

        @Override
        public void initialPosition() {
            currentNode = history.get(0);
        }

        @Override
        public void previousPosition() {
            if (currentNode.getParent() != null) {
                currentNode = currentNode.getParent();
            }
        }

        @Override
        public void positionAfter(Move m) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void positionWithId(int id) {
            currentNode = history.get(id);
        }
    }

    private static class HistoryNode {

        private final HistoryNode parent;
        private final Map<Move, HistoryNode> children = new HashMap<>();
        private final Position position;
        private final Move moveLeadingToThis;

        public HistoryNode(HistoryNode parent, Move moveLeadingToThis, Position position) {
            this.parent = parent;
            this.position = position;
            this.moveLeadingToThis = moveLeadingToThis;
        }

        public HistoryNode getParent() {
            return parent;
        }

        public Position getPosition() {
            return position;
        }

        public Move getMoveLeadingToThis() {
            return moveLeadingToThis;
        }
    }
}
