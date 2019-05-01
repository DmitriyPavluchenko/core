package concurrency;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class AnalyzeTask implements Callable<Map<Character, Long>> {

    private final List<String> text;

    public AnalyzeTask(List<String> text) {
        this.text = text;
    }

    @Override
    public Map<Character, Long> call() {
        return String.join("", text).chars()
                .filter(Character::isAlphabetic).mapToObj(value -> (char) value)
                .collect(Collectors.groupingBy(ch -> ch, Collectors.counting()));
    }
}
