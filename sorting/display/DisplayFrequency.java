package sorting.display;

import sorting.CommandLineArgs;
import sorting.Utils;
import sorting.inherit.BaseWriter;
import sorting.inherit.WriteValues;
import sorting.types.IntegerPair;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class DisplayFrequency extends BaseWriter implements WriteValues {
    int size;
    CommandLineArgs.DataType dataType;
    List<IntegerPair<Integer, ?>> orderedList = new ArrayList<>();

    public DisplayFrequency(PrintStream output) {
        super(output);
    }

    @Override
    public void writeOut() {
        String type = switch (dataType) {
            case LONG -> "numbers";
            case LINE -> "lines";
            case WORD -> "words";
        };
        outputStream.println("total " + type + ": " + size);

        orderedList.forEach(e -> outputStream.println(e + ", " + Utils.calculatePct(e.getInteger(), size) + "%"));

    }

    @Override
    public void prepare(CommandLineArgs.DataType dataType, List<?> originalList, List<?> sortedList) {
        this.dataType = dataType;
        size = originalList.size();
        this.orderedList = (List<IntegerPair<Integer, ?>>) sortedList;
    }
}
