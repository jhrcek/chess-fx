package cz.janhrcek.chess.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jhrcek
 */
public class GameImpl implements Game {

    private final PositionFactory positionFactory;
    private final Map<Integer, HistoryNode> history = new HashMap<>();
    private int nodeIdGenerator = 0;
    private final HistoryNode initialNode;
    private HistoryNode currentNode;

    public GameImpl(PositionFactory positionFactory) {
        this.positionFactory = positionFactory;
        Position initialPosition = positionFactory.createInitialPosition();
        initialNode = new HistoryNode(null, null, initialPosition);
        currentNode = initialNode;
        history.put(nodeIdGenerator, initialNode);
    }

    @Override
    public Position getInitialPosition() {
        return initialNode.getPosition();
    }

    @Override
    public Position getCurrentPosition() {
        return currentNode.getPosition();
    }

    @Override
    public void focusPreviousPosition() {
        currentNode = currentNode.getPrevious();
    }

    @Override
    public Position getPosition(int id) {
        return history.get(id).getPosition();
    }

    @Override
    public int makeMove(Move move) throws IllegalMoveException {
        Position curPos = getCurrentPosition();
        Position newPos = positionFactory.createPositionFrom(curPos, move);
        currentNode = new HistoryNode(currentNode, move, newPos);
        history.put(++nodeIdGenerator, currentNode);
        return nodeIdGenerator;
    }

    private static class HistoryNode {

        private final HistoryNode previous;
        private final Position position;
        private final Move moveLeadingToThis;

        public HistoryNode(HistoryNode previous, Move moveLeadingToThis, Position position) {
            this.previous = previous;
            this.position = position;
            this.moveLeadingToThis = moveLeadingToThis;
        }

        public HistoryNode getPrevious() {
            return previous;
        }

        public Position getPosition() {
            return position;
        }

        public Move getMoveLeadingToThis() {
            return moveLeadingToThis;
        }
    }
}
