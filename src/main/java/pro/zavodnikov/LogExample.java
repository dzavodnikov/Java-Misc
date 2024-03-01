package pro.zavodnikov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is example of SLF4J logging. <a href="https://www.slf4j.org/">SLF4J</a>
 * have multiple implementations. So, for test purposes that class can be build
 * with different backends.
 */
public class LogExample {

    private static final Logger LOG = LoggerFactory.getLogger(LogExample.class);

    public static void main(final String[] args) {
        System.out.println("STDOUT message.");
        LOG.error("Error message.");
        LOG.warn("Warn message.");
        LOG.info("Info message.");
        LOG.debug("Debug message.");
        LOG.trace("Trace message.");
    }
}
