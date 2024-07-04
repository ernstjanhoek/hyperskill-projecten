package analyzer.fileloader;

import java.io.FileNotFoundException;
import java.io.InputStream;

public interface FileLoader {
    public InputStream loadFromPath() throws FileNotFoundException;

    public String getFileName();
}
