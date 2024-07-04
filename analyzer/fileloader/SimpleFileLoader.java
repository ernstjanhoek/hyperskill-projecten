package analyzer.fileloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class SimpleFileLoader implements FileLoader {
    String path;

    public SimpleFileLoader(String path) {
        this.path = path;
    }

    @Override
    public InputStream loadFromPath() throws FileNotFoundException {
        return new FileInputStream(path);
    }

    @Override
    public String getFileName() {
        return new File(path).getName();
    }
}
