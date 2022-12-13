package src.day9;

import java.util.StringTokenizer;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class Rope {

    private List<Knot> knots;
    private Knot head;
    private Knot tail;
    
    private Set<Knot> tailVisited;

    public Rope(int length) {
        knots = new ArrayList<>(length);
        for (int i = 0; i < length; ++i) {
            knots.add(new Knot(0,0));
        }
        head = knots.get(0);
        tail = knots.get(length-1);
        tailVisited = new HashSet<>();
        updateTailHistory();
    }

    public Rope() {
        this(2);
    }

    public int squaresTailVisited() {
        return tailVisited.size();
    }

    public void moveHead(String move) throws Exception {
        StringTokenizer st = new StringTokenizer(move);
        if (st.countTokens() != 2) {
            throw new InvalidMoveException("Error: move string has more than two parts");
        }
        String direction = st.nextToken();
        int numSteps = Integer.parseInt(st.nextToken());
        
        int step = 1;
        int index;
        switch (direction) {
            case "L":
                step = -1;
                // [[fallthrough]]
            case "R":
                index = 0;
                break;
            case "D":
                step = -1;
                // [[fallthrough]]
            case "U":
                index = 1;
                break;
            default:
                throw new InvalidMoveException("Error: invalid direction");
        }
        for (int i = 0; i < numSteps; ++i) {
            head.set(index, step);
            for (int j = 1; j < knots.size(); ++j) {
                moveKnot(j);
            }
        }
    }

    private void moveKnot(int index) {
        Knot pred = knots.get(index-1);
        Knot curr = knots.get(index);

        int hDiff = pred.get(0) - curr.get(0);
        int hDir = hDiff > 0 ? 1 : -1;

        int vDiff = pred.get(1) - curr.get(1);
        int vDir = vDiff > 0 ? 1 : -1;

        int hDelta = Math.abs(hDiff);
        int vDelta = Math.abs(vDiff);

        if (hDelta <= 1 && vDelta <= 1) {
            return;
        } else if (hDelta == 2) {
            curr.set(0, hDir);
            if (vDelta > 0) {
                curr.set(1, vDir);
            }
        } else if (vDelta == 2) {
            curr.set(1, vDir);
            if (hDelta > 0) {
                curr.set(0, hDir);
            }
        } else {
            int hX = pred.get(0);
            int hY = pred.get(1);
            int tX = curr.get(0);
            int tY = curr.get(1);
            throw new IllegalStateException("Head: " + hX + "," + hY + " Tail: " + tX + "," + tY);
        }
        updateTailHistory();
    }

    public Knot getHead() {
        return head;
    }

    public Knot getTail() {
        return tail;
    }

    private void updateTailHistory() {
        tailVisited.add(getTail());
    }
    
}
