package src.day9;

import java.util.StringTokenizer;
import java.util.Set;
import java.util.HashSet;

public class Rope {

    private Coordinate head;
    private Coordinate tail;
    
    private Set<Coordinate> tailVisited;

    public Rope() {
        head = new Coordinate(0,0);
        tail = new Coordinate(0,0);
        tailVisited = new HashSet<>();
        updateTailHistory();
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
            moveTail();
            // System.out.println(move);
            // System.out.printf("HEAD: %d, %d%n", head.get(0),head.get(1));
            // System.out.printf("Tail: %d, %d%n", tail.get(0),tail.get(1));
        }
    }

    private void moveTail() {
        int hDiff = head.get(0) - tail.get(0);
        int hDir = hDiff > 0 ? 1 : -1;

        int vDiff = head.get(1) - tail.get(1);
        int vDir = vDiff > 0 ? 1 : -1;

        int hDelta = Math.abs(hDiff);
        int vDelta = Math.abs(vDiff);

        if (hDelta <= 1 && vDelta <= 1) {
            return;
        } else if (hDelta == 2) {
            tail.set(0, hDir);
            if (vDelta > 0) {
                tail.set(1, vDir);
            }
        } else if (vDelta == 2) {
            tail.set(1, vDir);
            if (hDelta > 0) {
                tail.set(0, hDir);
            }
        } else {
            int hX = head.get(0);
            int hY = head.get(1);
            int tX = tail.get(0);
            int tY = tail.get(1);
            throw new IllegalStateException("Head: " + hX + "," + hY + " Tail: " + tX + "," + tY);
        }
        updateTailHistory();
    }

    public Coordinate getHead() {
        return head;
    }

    public Coordinate getTail() {
        return tail;
    }

    private void updateTailHistory() {
        tailVisited.add(getTail());
    }
    
}
