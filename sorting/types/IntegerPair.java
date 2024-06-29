package sorting.types;

import sorting.inherit.BasePair;

public class IntegerPair<Integer extends Comparable<Integer>, T extends Comparable<T>> extends BasePair<Integer, T> {
    public IntegerPair(Integer i, T comparable) {
        super(i, comparable);
    }

    @Override
    public String toString() {
        return super.u.toString() + ": " + super.t.toString() + " time(s)";
    }

    public Integer getInteger() {
        return super.getT();
    }
}