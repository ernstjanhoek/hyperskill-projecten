package analyzer.cli;

import analyzer.patternloader.PatternWithPriorities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PriorityParser {
    final String path;
    final List<PatternWithPriorities> patternList = new ArrayList<>();

    public PriorityParser(String[] args) throws FileNotFoundException {
        this.path = args[0];
        File patternsFile = new File(args[1]);
        try (Scanner scanner = new Scanner(patternsFile)) {
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(";");

                PatternWithPriorities pattern = new PatternWithPriorities(
                        Integer.parseInt(line[0]),
                        line[1].replaceAll("^\"|\"$", ""),
                        line[2].replaceAll("^\"|\"$", "")
                );

                patternList.add(pattern);
            }
        }
        Collections.reverse(patternList);
    }

    public String getPath() {
        return path;
    }

    public List<PatternWithPriorities> getPatternList() {
        return patternList;
    }
}
