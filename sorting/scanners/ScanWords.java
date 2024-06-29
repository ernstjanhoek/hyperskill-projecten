package sorting.scanners;

import sorting.inherit.BaseScanner;
import sorting.inherit.ReadValues;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** concrete implementation to scan input (by words) from either System.in or FileStream */
public class ScanWords extends BaseScanner implements ReadValues {

    public ScanWords(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public List<String> scan() {
        List<String> words = new ArrayList<>();
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext()) {
            words.add(scanner.next());
        }
        return words;
    }
}
