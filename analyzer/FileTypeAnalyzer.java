package analyzer;

import analyzer.fileloader.FileLoader;
import analyzer.patternloader.PatternWithPriorities;
import analyzer.searchalgorithm.SearchAlgorithm;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class FileTypeAnalyzer {
    private final FileLoader fileLoaderImpl;

    private final List<PatternWithPriorities> patternList;

    private final SearchAlgorithm searchAlgorithmImpl;


    public FileTypeAnalyzer(FileLoader fileLoaderImpl,
                            List<PatternWithPriorities> patterns,
                            SearchAlgorithm searchAlgorithmImpl) {
        this.fileLoaderImpl = fileLoaderImpl;
        this.patternList = patterns;
        this.searchAlgorithmImpl = searchAlgorithmImpl;
    }

    public void analyze() throws IOException {
        String fileName = fileLoaderImpl.getFileName();

        try (FileInputStream inputStream = (FileInputStream) fileLoaderImpl.loadFromPath()) {
            byte[] buffer = inputStream.readAllBytes();
            for (PatternWithPriorities patternWithPriorities : patternList) {
                byte[] pattern = patternWithPriorities.loadPatternAsBytes();
                if (searchAlgorithmImpl.findPattern(buffer, pattern)) {
                    System.out.println(fileName + ": " + patternWithPriorities.getFileType());
                    return;
                }
            }
            System.out.println(fileName + ": " + "Unknown file type");
        }
    }
}
