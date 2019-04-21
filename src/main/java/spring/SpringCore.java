package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import spring.aspect.LoggedBean;
import spring.bpp.ProfiledBean;

import java.util.ArrayList;
import java.util.Arrays;

@EnableAspectJAutoProxy
@SpringBootApplication
public class SpringCore {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringCore.class, args);
        LoggedBean loggedBean = context.getBean(LoggedBean.class);
        ProfiledBean profiledBean = context.getBean(ProfiledBean.class);
        int hash = loggedBean.calculateHash(1);
        loggedBean.rememberHash(1, hash);
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 543, 135, 1412345, 3));
        profiledBean.sort(list);
        profiledBean.search(list, 2);
    }
}
