package spring.aspect;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LoggedBeanExample implements LoggedBean {

    private final Map<Integer, Integer> map = new HashMap<>();

    @Logged(level = "WARNING")
    @Override
    public void rememberHash(int number, int hash) {
        map.putIfAbsent(number, hash);
    }

    @Logged
    @Override
    public int calculateHash(int number) {
        return Integer.hashCode(number);
    }
}
