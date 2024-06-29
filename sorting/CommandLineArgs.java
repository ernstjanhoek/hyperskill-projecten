package sorting;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static sorting.CommandLineArgs.IOStream.FILE;

public class CommandLineArgs {
    public SortingType sortingType;

    public DataType dataType;

    public IOSettings inputSettings;

    public IOSettings outputSettings;

    public static CommandLineArgs defaultArgs() {
        CommandLineArgs args = new CommandLineArgs();
        args.dataType = DataType.WORD;
        args.sortingType = SortingType.NATURAL;
        args.inputSettings = new IOSettings(IOStream.CLI);
        args.outputSettings = new IOSettings(IOStream.CLI);
        return args;
    }

    public static CommandLineArgs parseArgs(String[] args) throws IllegalArgumentException {
        CommandLineArgs parsedArgs = new CommandLineArgs();

        Iterator<String> iterator = Arrays.stream(args).iterator();

        while (iterator.hasNext()) {
            switch (iterator.next()) {
                case "-inputSettings":
                    if (iterator.hasNext()) {
                        parsedArgs.inputSettings = new IOSettings(FILE, iterator.next());
                    } else {
                        throw new IllegalArgumentException("No input file name defined!");
                    }
                    break;
                case "-outputSettings":
                    if (iterator.hasNext()) {
                        parsedArgs.outputSettings = new IOSettings(FILE, iterator.next());
                    } else {
                        throw new IllegalArgumentException("No output file name defined!");
                    }
                    break;
                case "-sortingType":
                    if (iterator.hasNext()) {
                        if (iterator.next().equals("byCount")) {
                            parsedArgs.sortingType = SortingType.BYCOUNT;
                        } else {
                            parsedArgs.sortingType = SortingType.NATURAL;
                        }
                    }
                    break;
                case "-dataType":
                    if (iterator.hasNext()) {
                        switch (iterator.next()) {
                            case "long":
                                parsedArgs.dataType = DataType.LONG;
                                break;
                            case "line":
                                parsedArgs.dataType = DataType.LINE;
                                break;
                            case "word":
                                parsedArgs.dataType = DataType.WORD;
                        }
                    }
            }
        }

        List<String> argsList = Arrays.stream(args).toList();

        // if (argsList.contains("-sortingType") && parsedArgs.sortingType == null) {
        //     throw new IllegalArgumentException("No sorting type defined!");
        // }

        if (argsList.contains("-dataType") && parsedArgs.dataType == null) {
            throw new IllegalArgumentException("No data type defined!");
        }

        argsList.stream().filter(e -> e.startsWith("-"))
                .filter(e -> !e.equals("-sortingType") && !e.equals("-dataType") && !e.equals("-inputFile") && !e.equals("-outputFile"))
                .forEach(a -> System.out.println("\"" + a +"\" is not a valid parameter. It will be skipped."));

        if (parsedArgs.sortingType == null) {
            parsedArgs.sortingType = SortingType.NATURAL;
        }

        if (parsedArgs.dataType == null) {
            parsedArgs.dataType = DataType.WORD;
        }

        if (parsedArgs.inputSettings == null) {
            parsedArgs.inputSettings = new IOSettings(IOStream.CLI);
        }

        if (parsedArgs.outputSettings == null) {
            parsedArgs.outputSettings = new IOSettings(IOStream.CLI, "");
        }

        return parsedArgs;
    }

    public enum SortingType {
        NATURAL, BYCOUNT
    }

    public enum DataType {
        WORD, LONG, LINE
    }

    public enum IOStream {
        FILE, CLI
    }

    public static class IOSettings {
        IOSettings(IOStream ioStream, String file) {
            stream = ioStream;
            this.file = file;
        }

        IOSettings(IOStream ioStream) {
            this.stream = ioStream;
            this.file = "";
        }

        public IOStream stream;
        public String file;
    }
}