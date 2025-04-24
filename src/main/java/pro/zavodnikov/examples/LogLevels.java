/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2009-2025 Dmitry Zavodnikov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package pro.zavodnikov.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is example of SLF4J logging. <a href="https://www.slf4j.org/">SLF4J</a>
 * have multiple implementations. So, for test purposes that class can be build
 * with different backends.
 */
public final class LogLevels {

    private static final Logger LOG = LoggerFactory.getLogger(LogLevels.class);

    private LogLevels() {}

    public static void main(final String[] args) {
        System.out.println("STDOUT message.");
        LOG.trace("Trace message.");
        LOG.debug("Debug message.");
        LOG.info("Info message.");
        LOG.warn("Warn message.");
        LOG.error("Error message.");
    }
}
