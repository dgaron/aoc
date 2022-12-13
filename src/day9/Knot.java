package src.day9;

// I could have just used an ArrayList with two Integers ...
public class Knot {
    private int x;
    private int y;

    public Knot(int x, int y) {
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
        Knot other = (Knot) obj;
        return this.x == other.x && this.y == other.y;
    }

    // For the HashSet to work
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 43;
        result = PRIME * result + Integer.valueOf(x).hashCode();
        result = PRIME * result + Integer.valueOf(y).hashCode();
    
        long temp = Double.doubleToLongBits(x);
        result = PRIME * result + ((int) (temp ^ (temp >> 32)));
        result = PRIME * result + (Integer.valueOf(y).hashCode());

        return result;
    }

}