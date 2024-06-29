package sorting.scanners;

import sorting.inherit.BaseScanner;
import sorting.inherit.ReadValues;

import java.io.InputStream;
import java.util.List;
import java.util.Random;
import java.util.stream.LongStream;

public class TestScannerLongs extends BaseScanner implements ReadValues {
    public TestScannerLongs(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public List<?> scan() {
        Random r = new Random();
        return LongStream.range(0, 25_000_000).parallel()
                .map(l -> l % r.nextLong(1, 25_000))
                .boxed().toList();
    }

}
