package sorting.sorters;

import sorting.CommandLineArgs;
import sorting.inherit.ReadValues;
import sorting.inherit.SortingAlgorithm;
import sorting.inherit.WriteValues;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SortingTool<T extends Comparable<T>> {
    CommandLineArgs.DataType dataType;
    List<?> list;

    SortingAlgorithm<?> sortingAlgorithmImpl;

    WriteValues writeValuesImpl;

    ReadValues valueReaderImpl;

    public SortingTool(
            SortingAlgorithm<?> sortingAlgorithmImpl,
            WriteValues writeValuesImpl,
            ReadValues valueReaderImpl,
            CommandLineArgs.DataType dataType
    ) {
        this.list = new ArrayList<T>();
        this.sortingAlgorithmImpl = sortingAlgorithmImpl;
        this.writeValuesImpl = writeValuesImpl;
        this.valueReaderImpl = valueReaderImpl;
        this.dataType = dataType;
    }

    public void run() throws IOException {
        this.read();
        this.prepare();
        this.sort();
        this.write();
    }

    private void prepare() {
        sortingAlgorithmImpl.prepare(this.list);
    }

    private void read() {
        this.list = valueReaderImpl.scan();
    }

    private void sort() {
        sortingAlgorithmImpl.sort();
    }

    private List<?> returnSortedList() {
        return sortingAlgorithmImpl.returnSortedList();
    }

    private void write() throws IOException {
        File file = new File("out.txt");
        file.createNewFile();
        writeValuesImpl.prepare(this.dataType, this.list, returnSortedList());
        writeValuesImpl.writeOut();
    }
}