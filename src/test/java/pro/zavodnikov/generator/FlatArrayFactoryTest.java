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
package pro.zavodnikov.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link FlatArrayFactory}.
 */
public class FlatArrayFactoryTest {

    @Test
    void testClassName() throws InstantiationException, IllegalAccessException {
        final FlatArrayFactory<PersonArray> factory = new FlatArrayFactory<>();
        final PersonArray arr = factory.createArray(PersonArray.class, 1);
        assertEquals("FlatArray_of_PersonArray", arr.getClass().getSimpleName());
    }

    @Test
    void testSize() throws InstantiationException, IllegalAccessException {
        final FlatArrayFactory<PersonArray> factory = new FlatArrayFactory<>();
        final PersonArray arr = factory.createArray(PersonArray.class, 5);
        assertEquals(5, arr.size());
    }

    @Test
    void testGetSet() throws InstantiationException, IllegalAccessException {
        final FlatArrayFactory<PersonArray> factory = new FlatArrayFactory<>();
        final PersonArray arr = factory.createArray(PersonArray.class, 1);

        final String name = "John Doe";

        assertNull(arr.getName());

        arr.setName(name);
        assertEquals(name, arr.getName());
    }

    @Test
    void testToString() throws InstantiationException, IllegalAccessException {
        final FlatArrayFactory<PersonArray> factory = new FlatArrayFactory<>();
        final PersonArray obj = factory.createArray(PersonArray.class, 1);
        assertEquals("Hello World!", obj.toString());
    }
}
