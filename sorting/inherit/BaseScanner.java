package sorting.inherit;

import java.io.InputStream;

abstract public class BaseScanner {
    protected InputStream inputStream;
    protected BaseScanner(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
