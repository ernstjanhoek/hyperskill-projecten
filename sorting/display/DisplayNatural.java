package sorting.display;

import sorting.CommandLineArgs;
import sorting.inherit.BaseWriter;
import sorting.inherit.WriteValues;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class DisplayNatural extends BaseWriter implements WriteValues {
    List<?> orderedList = new ArrayList<>();
    private CommandLineArgs.DataType dataType;

    public DisplayNatural(PrintStream output) {
        super(output);
    }


    @Override
    public void writeOut() {
        String type = switch (this.dataType) {
            case LONG -> "numbers";
            case LINE -> "lines";
            case WORD -> "words";
        };
        outputStream.println("Total " + type + ": " + orderedList.size());
        outputStream.print("Sorted data: ");
        if (dataType == CommandLineArgs.DataType.LINE) {
            outputStream.println();
            orderedList.forEach(outputStream::println);
        } else {
            orderedList.forEach(e -> outputStream.print(e + " "));
        }
    }

    @Override
    public void prepare(CommandLineArgs.DataType dataType, List<?> originalList, List<?> sortedList) {
        this.orderedList = sortedList;
        this.dataType = dataType;
    }
}
