package algorithm.search;

import java.util.*;

public class SearchInCollection {

    public static <E extends Comparable> List<E> findMinValues(Collection<E> elements, int count) {
        Queue<E> queue = new PriorityQueue<>(count, Collections.reverseOrder());
        for (E element : elements) {
            if (queue.size() < count) {
                queue.add(element);
            } else if (queue.peek().compareTo(element) > 0) {
                queue.poll();
                queue.add(element);
            }
        }
        return new ArrayList<>(queue);
    }

}
