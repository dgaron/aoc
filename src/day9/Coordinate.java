package src.day9;

// I could have just used an ArrayList with two Integers ...
public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int get(int index) {
        if (index == 0) {
            return x;
        } else if (index == 1) {
            return y;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void set(int index, int val) {
        if (index == 0) {
            x += val;
        } else if (index == 1) {
            y += val;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    // For the HashSet to work
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coordinate other = (Coordinate) obj;
        return this.x == other.x && this.y == other.y;
    }

    // For the HashSet to work
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

}