package sorting.builders;

import sorting.CommandLineArgs;
import sorting.algorithms.ByCountTool;
import sorting.algorithms.NaturalSort;
import sorting.display.DisplayFrequency;
import sorting.display.DisplayNatural;
import sorting.inherit.ReadValues;
import sorting.inherit.SortingAlgorithm;
import sorting.inherit.WriteValues;
import sorting.scanners.ScanLines;
import sorting.scanners.ScanLongs;
import sorting.scanners.ScanWords;
import sorting.sorters.SortingTool;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class SortingToolFactory {
    public static SortingTool<?> createSortingTool(CommandLineArgs args) throws Exception {
        // type impl for generic type <T> / wildcard<?>
        InputStream inputStream = switch(args.inputSettings.stream) {
            case FILE -> new FileInputStream(args.inputSettings.file);
            case CLI -> System.in;
        };

        ReadValues readValuesImpl = switch (args.dataType) {
            case WORD ->  new ScanWords(inputStream);
            case LONG ->  new ScanLongs(inputStream);
            case LINE ->  new ScanLines(inputStream);
        };

        PrintStream outputStream = switch (args.outputSettings.stream) {
            case FILE -> new PrintStream(args.outputSettings.file);
            case CLI -> System.out;
        };

        SortingAlgorithm<?> sortingAlgorithm = null;
        WriteValues displayImpl = null;

        switch (args.sortingType) {
            case NATURAL -> {
                sortingAlgorithm = new NaturalSort<>();
                displayImpl = new DisplayNatural(outputStream);
            }
            case BYCOUNT -> {
                displayImpl = new DisplayFrequency(outputStream);
                sortingAlgorithm = new ByCountTool<>();
            }
        }

        return new SortingTool<>(
                sortingAlgorithm,
                displayImpl,
                readValuesImpl,
                args.dataType
        );
    }
}
