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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@link FileSize}.
 */
public class FileSizeTest {

    @Test
    public void testToStringBytes() {
        final FileSize fz1 = new FileSize(1L);
        System.out.println(fz1);
        assertEquals("1 Bytes", fz1.toString());
    }

    @Test
    public void testToStringKib() {
        final FileSize fz2k = new FileSize(2_000L);
        System.out.println(fz2k);
        assertEquals("2.0 KiB", fz2k.toString());
    }

    @Test
    public void testToStringMib() {
        final FileSize fz3m = new FileSize(3_000_000L);
        System.out.println(fz3m);
        assertEquals("2.9 MiB", fz3m.toString());
    }

    @Test
    public void testToStringGib() {
        final FileSize fz4g = new FileSize(4_000_000_000L);
        System.out.println(fz4g);
        assertEquals("3.7 GiB", fz4g.toString());
    }

    @Test
    public void testToStringTib() {
        final FileSize fz5t = new FileSize(5_000_000_000_000L);
        System.out.println(fz5t);
        assertEquals("4.5 TiB", fz5t.toString());
    }
}
