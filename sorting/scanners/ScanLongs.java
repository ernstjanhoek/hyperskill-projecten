package sorting.scanners;

import sorting.inherit.BaseScanner;
import sorting.inherit.ReadValues;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScanLongs extends BaseScanner implements ReadValues {

    public ScanLongs(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public List<Long> scan() {
        List<Long> list = new ArrayList<>();
        Scanner scanner = new Scanner(super.inputStream);
        while (scanner.hasNext()) {
            list.add(scanner.nextLong());
        }
        return list;
    }
}
