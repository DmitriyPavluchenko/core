package spring.bpp;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProfiledBeanExample implements ProfiledBean {

    @Profiled
    @Override
    public void sort(List<Integer> list) {
        list.sort(Integer::compareTo);
    }

    @Profiled
    @Override
    public boolean search(List<Integer> list, Integer search) {
        return list.contains(search);
    }

}
