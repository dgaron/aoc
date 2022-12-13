package src.day10;

import java.util.List;
import java.util.ArrayList;

public class CPU {

    private int x;
    private List<Integer> clock;

    public CPU() {
        x = 1;
        clock = new ArrayList<>();
        clock.add(x);
    }

    public void noop() {
        clock.add(x);
    }

    public void addx(int n) {
        clock.add(x);
        clock.add(x);
        x += n;
    }

    public int getxAt(int n) {
        return clock.get(n);
    }

    public int getSignalStrength(int n) {
        return n * clock.get(n);
    }
}
