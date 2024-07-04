package analyzer;

import analyzer.cli.PriorityParser;
import analyzer.fileloader.SimpleFileLoader;
import analyzer.searchalgorithm.RabinKarp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        PriorityParser parsedArgs = new PriorityParser(args);

        List<File> files = new ArrayList<>();
        File dir = new File(parsedArgs.getPath());
        Arrays.stream(Objects.requireNonNull(dir.listFiles()))
                .filter(File::isFile)
                .forEach(files::add);

        List<Thread> threads = new ArrayList<>();

        for (File file : files) {
            threads.add(new Thread(() -> {
                        FileTypeAnalyzer analyzer = new FileTypeAnalyzer(
                                new SimpleFileLoader(file.getPath()),
                                parsedArgs.getPatternList(),
                                new RabinKarp()
                        );

                        try {
                            analyzer.analyze();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }));
        }

        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException ignore) {}
        }
    }
}
