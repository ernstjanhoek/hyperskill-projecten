package sorting.inherit;

import sorting.CommandLineArgs;
import java.io.PrintStream;
import java.util.List;

public interface WriteValues {
    void writeOut();

    void prepare(CommandLineArgs.DataType dataType, List<?> originalList, List<?> sortedList);
}
