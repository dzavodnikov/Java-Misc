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

import java.util.HashMap;
import java.util.Map;

/**
 * Base class for all factories.
 */
public abstract class FlatFactory<T> {

    protected final String structName;

    protected FlatFactory(final String structName) {
        this.structName = structName;
    }

    protected String getStructName() {
        return this.structName;
    }

    /**
     * Cache of class implementations.
     */
    private static final Map<Class<?>, Class<?>> CLASS_IMPL_CACHE = new HashMap<>();

    /**
     * @param classDef describes how to data should be located into the memory;
     * @return class name of new structure.
     */
    protected String createClassName(final Class<T> classDef) {
        return String.format("%s_of_%s", getStructName(), classDef.getSimpleName());
    }

    protected void verifyClassLoader(final ClassLoader classLoader) {
        if (classLoader == null) {
            throw new IllegalArgumentException("ClassLoader parameter should not be null");
        }
    }

    protected void verifyClassDefinition(final Class<T> classDef) {
        if (classDef == null) {
            throw new IllegalArgumentException("Class definition parameter should not be null");
        }
        if (!classDef.isInterface()) {
            throw new IllegalArgumentException("Class definition parameter should be an interface");
        }
    }

    /**
     * @param classLoader manage created class;
     * @param classDef    describes how to data should be located into the memory;
     * @return created class implementation based on class definition.
     */
    abstract Class<?> createImpl(ClassLoader classLoader, Class<T> classDef);

    /**
     * Create new implementation of data structure based on it's definition.
     *
     * @param classLoader manage created class;
     * @param classDef    class definition of data structure;
     * @return class implementation based on class definition.
     */
    @SuppressWarnings("unchecked")
    Class<T> getImpl(final ClassLoader classLoader, final Class<T> classDef) {
        Class<?> classImpl = CLASS_IMPL_CACHE.get(classDef);
        if (classImpl == null) {
            classImpl = createImpl(classLoader, classDef);
            CLASS_IMPL_CACHE.put(classDef, classImpl);
        }
        return (Class<T>) classImpl;
    }

    /**
     * Create new implementation of data structure based on it's definition. Use
     * current classloader from current class.
     *
     * @param classDef class definition of data structure;
     * @return class implementation based on class definition.
     */
    Class<T> getImpl(final Class<T> classDef) {
        return getImpl(getClass().getClassLoader(), classDef);
    }
}
