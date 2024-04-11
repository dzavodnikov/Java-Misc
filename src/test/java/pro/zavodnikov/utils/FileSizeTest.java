/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2009-2026 Dmitry Zavodnikov
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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@link FileSize}.
 */
public class FileSizeTest {

    private void assertSize(final String expectedString, final long size) {
        final FileSize fs = new FileSize(size);
        System.out.println(fs);
        assertEquals(expectedString, fs.toString());
    }

    @Test
    public void testHumanReadableSize() {
        assertSize("0 Bytes", 0L);
        assertSize("1 Bytes", 1L);
        assertSize("2 KiB", 2_000L);
        assertSize("2.9 MiB", 3_000_000L);
        assertSize("3.7 GiB", 4_000_000_000L);
        assertSize("4.5 TiB", 5_000_000_000_000L);
        assertSize("5.3 PiB", 6_000_000_000_000_000L);
        assertSize("6 217.2 PiB", 7_000_000_000_000_000_000L);
    }
}
