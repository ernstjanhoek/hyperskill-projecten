package sorting.inherit;

public abstract class BasePair<T extends Comparable<T>, U extends Comparable<U>> implements Comparable<BasePair<T, U>> {
    protected T t;
    protected U u;

    public BasePair(T t, U u) {
        this.t = t;
        this.u = u;
    }

    public T getT() {
        return t;
    }

    public U getU() {
        return u;
    }

    @Override
    public int compareTo(BasePair<T, U> tuPair) {
        if (this.t.compareTo(tuPair.t) != 0) {
            return this.t.compareTo(tuPair.t);
        } else {
            return this.u.compareTo(tuPair.u);
        }
    }

}