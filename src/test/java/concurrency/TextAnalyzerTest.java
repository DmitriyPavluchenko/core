package concurrency;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TextAnalyzerTest {

    private String filePath;


    @BeforeEach
    public void init() {
        filePath = TextAnalyzerTest.class.getClassLoader().getResource("text_to_analyze").getFile();
        String text = "AAAAAAA\n" +
                      "BBBBBB\n" +
                      "CCCCC\n" +
                      "DDDD\n" +
                      "EEE\n" +
                      "FF\n" +
                      "G";
        try {
            Files.write(Paths.get(filePath), Collections.singleton(text));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void analyze() {
        TextAnalyzer textAnalyzer = new TextAnalyzer(filePath);
        textAnalyzer.analyze();
        Map<Character, Long> expected = new HashMap<>();
        expected.put('A', 7L);
        expected.put('B', 6L);
        expected.put('C', 5L);
        expected.put('D', 4L);
        expected.put('E', 3L);
        expected.put('F', 2L);
        expected.put('G', 1L);

        Assertions.assertEquals(expected, textAnalyzer.getResult());
    }
}