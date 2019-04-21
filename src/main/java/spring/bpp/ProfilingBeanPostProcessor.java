package spring.bpp;

import javafx.util.Pair;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.logging.Logger;

@Component
public class ProfilingBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, Pair<Class, List<Method>>> beanClasses = new HashMap<>();
    private final Logger log = Logger.getLogger(this.getClass().getName());

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        for (Method method : bean.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Profiled.class)) {
                Method interfaceMethod = ClassUtils.getInterfaceMethodIfPossible(method);
                Pair<Class, List<Method>> present = beanClasses.computeIfPresent(beanName, (key, pair) -> {
                    pair.getValue().add(interfaceMethod);
                    return pair;
                });
                if (present == null)
                    beanClasses.putIfAbsent(beanName, new Pair<>(bean.getClass(), new ArrayList<>(Collections.singletonList(interfaceMethod))));
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Pair<Class, List<Method>> pair = beanClasses.get(beanName);
        if (pair != null) {
            Class beanClass = pair.getKey();
            List<Method> methods = pair.getValue();
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(),
                    (proxy, method, args) -> {
                        if (methods.contains(method)) {
                            long start = System.nanoTime();
                            Object result = method.invoke(bean, args);
                            long end = System.nanoTime();
                            log.info(String.format("Method %s in bean %s worked %d nanoseconds", method.getName(), beanClass, end - start));
                            return result;
                        } else return method.invoke(bean, args);
                    });
        }
        return bean;
    }
}
