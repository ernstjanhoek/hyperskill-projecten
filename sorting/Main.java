package sorting;

import sorting.builders.SortingToolFactory;
import sorting.sorters.SortingTool;

public class Main {
    public static void main(String[] args) throws Exception {
        CommandLineArgs parsedArgs = sorting.CommandLineArgs.parseArgs(args);
        SortingTool<?> sortingTool = SortingToolFactory.createSortingTool(parsedArgs);
        sortingTool.run();
    }
}