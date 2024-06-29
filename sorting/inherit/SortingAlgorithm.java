package sorting.inherit;

import java.util.List;

public interface SortingAlgorithm<T> {
    void prepare(List<?> list);

    void sort();

    List<?> returnSortedList();
}
