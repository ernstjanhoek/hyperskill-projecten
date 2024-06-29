package sorting.algorithms;

import sorting.inherit.SortingAlgorithm;

import java.lang.reflect.Array;
import java.util.List;

public class NaturalMergeSortTool<T extends Comparable<T>> implements SortingAlgorithm<T> {
    private final Class<T> clazz;

    T[] list;

    public NaturalMergeSortTool(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void prepare(List<?> inputList) {
        // assign new array
        list = makeArrayT(inputList.size());
        for (int i = 0; i < list.length; i++) {
            list[i] = (T) inputList.get(i);
        }
    }

    @Override
    public void sort() {
        mergeSort(list, list.length);
    }

    @Override
    public List<T> returnSortedList() {
        return List.of(list);
    }

    private void mergeSort(T[] arr, int n) {
        if (n < 2) {
            return; // (sub)Array is klein genoeg. Tijd om te mergen?
        }

        // DIVIDE
        int mid = n / 2; // Middelpunt van array uitrekenen om te delen

        T[] left = makeArrayT(arr[0], mid);
        T[] right = makeArrayT(arr[0], n - mid);

        // Populate arrays with values (links)
        for (int i = 0; i < mid; i++) {
            left[i] = arr[i];
        }

        // Populate arrays with values (rechts)
        for (int i = mid; i < n; i++) { // i = mid zodat het eerste uitgelezen element mid uit arr is.
            right[i - mid] = arr[n - i]; // Gebruik i - mid zodat eerste index 0 nul is, etc.
        }

        mergeSort(left, mid); // recursive call voor links en rechts
        mergeSort(right, n - mid);

        // CONQUER?
        merge(arr, left, right, mid, n - mid);
    }

    private void merge(
            T[] array, T[] l, T[] r, int left, int right
    ) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) { // Zorgt dat er door zowel ??
            if (l[i].compareTo(r[j]) <= 0) {
                array[k++] = l[i++];
            } else {
                array[k++] = r[j++];
            }
        }
        while (i < left) {
            array[k++] = l[i++];
        }
        while (j < right) {
            array[k++] = r[j++];
        }
    }

    @SuppressWarnings("unchecked")
    private T[] makeArrayT(int length) {
        return (T[]) Array.newInstance(this.clazz, length);
    }
    @SuppressWarnings("unchecked")
    private T[] makeArrayT(T type, int length) {
        return (T[]) Array.newInstance(type.getClass(), length);
    }
}
