package concurrency;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TextAnalyzer {
    private final CompletionService<Map<Character, Long>> completionService;
    private final Map<Character, Long> result;
    private final String filePath;
    private int taskCount;

    private final static int LINE_BATCH_SIZE = 4;

    public TextAnalyzer(String filePath) {
        this.filePath = filePath;
        this.completionService = new ExecutorCompletionService<>(
                Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1));
        this.result = new HashMap<>();
    }

    public void analyze() {
        runAnalyzeTasks();
        for (int i = 0; i < taskCount; i++) {
            try {
                Future<Map<Character, Long>> completedTask = completionService.take();
                Map<Character, Long> analyzed = completedTask.get();
                addToResult(analyzed);
            } catch (Exception e) {
                System.out.println("Error analyzing text");
                e.printStackTrace();
            }
        }
    }

    public Map<Character, Long> getResult() {
        return Collections.unmodifiableMap(result);
    }

    public void printResult() {
        result.entrySet().iterator().forEachRemaining(entry ->
                System.out.println(entry.getKey() + ": " + entry.getValue()));
    }

    private void addToResult(Map<Character, Long> analyzed) {
        for (Map.Entry<Character, Long> entry : analyzed.entrySet()) {
            result.compute(entry.getKey(), (key, value) -> value == null ? entry.getValue() : value + entry.getValue());
        }
    }

    private void runAnalyzeTasks() {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            List<String> textToAnalyze = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                textToAnalyze.add(line);
                if (textToAnalyze.size() == LINE_BATCH_SIZE) {
                    submitTask(textToAnalyze);
                    textToAnalyze.clear();
                }
            }
            if (!textToAnalyze.isEmpty()) submitTask(textToAnalyze);
        } catch (IOException e) {
            System.out.println("Error reading file" + filePath);
            e.printStackTrace();
        }
    }

    private void submitTask(List<String> textToAnalyze) {
        completionService.submit(new AnalyzeTask(new ArrayList<>(textToAnalyze)));
        taskCount++;

    }
}
