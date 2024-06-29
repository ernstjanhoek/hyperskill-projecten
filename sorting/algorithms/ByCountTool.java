package sorting.algorithms;

import sorting.inherit.BasePair;
import sorting.inherit.SortingAlgorithm;
import sorting.types.IntegerPair;

import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class ByCountTool<T extends Comparable<T>> implements SortingAlgorithm<T> {
    /** ordered set of pairs with occurrence */
    TreeSet<BasePair<Integer, T>> treeSet = new TreeSet<>();

    /** holds the unordered elements with occurrence integer */
    HashMap<T, Integer> map = new HashMap<>();

    /** Convert the elements of the list to an unorderedList of Pairs<\Integer, T>*/
    @Override
    public void prepare(List<?> list) {
        list.stream().map(e -> (T) e ).forEach(e -> {
            if (!map.containsKey(e)) {
                map.put(e, 1);
            } else {
                map.put(e, map.get(e) + 1);
            }
        });
    }

    /** Add the elements of the prepared list to the treeSet*/
    @Override
    public void sort() {
        map.entrySet()
                .stream()
                .map(e -> new IntegerPair<>(e.getValue(), e.getKey()))
                .forEach(e -> treeSet.add(e));
    }

    @Override
    public List<BasePair<Integer, T>> returnSortedList() {
        return treeSet.stream().parallel().toList();
    }
}