package sorting.scanners;

import sorting.inherit.BaseScanner;
import sorting.inherit.ReadValues;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScanLines extends BaseScanner implements ReadValues {

    public ScanLines(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public List<String> scan() {
        List<String> lines = new ArrayList<>();
        Scanner scanner = new Scanner(super.inputStream);
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        return lines;
    }
}
