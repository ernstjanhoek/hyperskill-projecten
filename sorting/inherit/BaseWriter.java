package sorting.inherit;

import java.io.PrintStream;

abstract public class BaseWriter {
    protected PrintStream outputStream;
    protected BaseWriter(PrintStream outputStream) {
        this.outputStream = outputStream;
    }
}
