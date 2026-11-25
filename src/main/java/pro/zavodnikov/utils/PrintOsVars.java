package pro.zavodnikov.utils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PrintOsVars {

    public static void main(final String[] args) {
        final List<String> keys = System.getProperties().keySet().stream()
                .map(Object::toString)
                .collect(Collectors.toList());
        Collections.sort(keys);
        keys.forEach(key -> System.out.println(key + " = " + System.getProperty(key)));
    }
}
