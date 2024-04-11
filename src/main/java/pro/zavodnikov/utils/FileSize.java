/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2009-2024 Dmitry Zavodnikov
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
package pro.zavodnikov.utils;

/**
 * Print file zie in human-readable format.
 */
public class FileSize {

    private final String[] POSTFIX = new String[] { "Bytes", "KiB", "MiB", "GiB", "TiB", "PiB" };

    public long value;

    public FileSize(final long size) {
        this.value = size;
    }

    public FileSize() {
        this(0);
    }

    @Override
    public String toString() {
        int count = 0;
        long div = 1;
        while (div <= this.value && count < POSTFIX.length) {
            ++count;
            div = div << 10;
        }
        --count;
        if (count < POSTFIX.length) {
            div = div >> 10;
        }

        final double val = div > 1 ? (double) this.value / div : this.value;
        String format = "%.1f %s";
        if (div == 1) {
            format = "%.0f %s";
        }
        return String.format(format, val, POSTFIX[count]);
    }
}
