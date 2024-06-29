package sorting.algorithms;

import sorting.inherit.SortingAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NaturalSort<T extends Comparable<T>> implements SortingAlgorithm<T> {
    List<T> unsortedList = new ArrayList<>();
    List<T> sortedList = new ArrayList<>();

    @Override
    public void prepare(List<?> list) {
        this.unsortedList = (List<T>) list;
        this.sortedList = (List<T>) new ArrayList<>(list);
    }

    @Override
    public void sort() {
        Collections.sort(sortedList);
    }

    @Override
    public List<?> returnSortedList() {
        return sortedList;
    }
}
