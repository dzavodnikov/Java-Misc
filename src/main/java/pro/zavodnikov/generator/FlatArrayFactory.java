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
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * Flat Array factory.
 */
public class FlatArrayFactory<T> extends FlatFactory<T> {

    protected static final String SIZE_FIELD = "size";
    protected static final String MY_VALUE_FIELD = "myValue";

    public FlatArrayFactory() {
        super("FlatArray");
    }

    public T createArray(final Class<T> cl, final int length) {
        try {
            return cl.cast(getImpl(cl).getDeclaredConstructor(int.class).newInstance(length));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException
                | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Class<?> createImpl(final ClassLoader classLoader, final Class<T> classDef) {
        try {
            final Class<?> dynamicType = new ByteBuddy()
                    // Interface for casting.
                    .subclass(Object.class)
                    .implement(classDef)
                    .name(createClassName(classDef))
                    // Internal field.
                    .defineField(SIZE_FIELD, int.class, Visibility.PRIVATE)
                    .defineField(MY_VALUE_FIELD, String.class, Visibility.PRIVATE)
                    // Constructor.
                    .defineConstructor(Visibility.PUBLIC)
                    .withParameter(int.class)
                    .intercept(MethodCall.invoke(Object.class.getConstructor())
                            .andThen(FieldAccessor.ofField(SIZE_FIELD).setsArgumentAt(0)))
                    // Getter for #size()
                    .defineMethod("size", int.class)
                    .intercept(FieldAccessor.ofField(SIZE_FIELD))
                    // Getter for myValue.
                    .defineMethod("getName", String.class)
                    .intercept(MethodDelegation.to(StringAccessor.class))
                    // Setter for myValue.
                    .defineMethod("setName", void.class)
                    .withParameter(String.class)
                    .intercept(MethodDelegation.to(StringAccessor.class))
                    // #toString method.
                    .method(ElementMatchers.named("toString"))
                    .intercept(FixedValue.value("Hello World!"))
                    // Create class.
                    .make()
                    .load(classLoader, ClassLoadingStrategy.Default.INJECTION)
                    .getLoaded();
            return dynamicType;
        } catch (NoSuchMethodException | IllegalArgumentException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }
}
