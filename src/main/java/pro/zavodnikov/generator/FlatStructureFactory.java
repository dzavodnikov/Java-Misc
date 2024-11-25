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

import java.lang.reflect.InvocationTargetException;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FieldAccessor;

/**
 * Flat structure factory.
 */
public class FlatStructureFactory<T> extends FlatFactory<T> {

    private static final String NAME_FIELD = "name"; // FIXME

    public FlatStructureFactory() {
        super("FlatStructure");
    }

    @Override
    protected Class<?> createImpl(final ClassLoader classLoader, final Class<T> classDef) {
        verifyClassLoader(classLoader);
        verifyClassDefinition(classDef);

        try {
            final Class<?> dynamicType = new ByteBuddy()
                    // Interface for casting.
                    .subclass(Object.class)
                    .implement(classDef)
                    .name(createClassName(classDef))
                    // Internal field.
                    .defineField(NAME_FIELD, String.class, Visibility.PRIVATE)
                    // Getter for myValue.
                    .defineMethod("getName", String.class)
                    .intercept(FieldAccessor.ofField(NAME_FIELD))
                    // Setter for myValue.
                    .defineMethod("setName", void.class)
                    .withParameter(String.class)
                    .intercept(FieldAccessor.ofField(NAME_FIELD))
                    // Create class.
                    .make()
                    .load(classLoader, ClassLoadingStrategy.Default.INJECTION)
                    .getLoaded();
            return dynamicType;
        } catch (IllegalArgumentException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param classDef describes how to data should be located into the memory;
     * @return structure instance that was generated from class definition.
     */
    public T create(final Class<T> classDef) {
        verifyClassDefinition(classDef);

        try {
            return classDef.cast(getImpl(classDef).getDeclaredConstructor().newInstance());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException
                | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
