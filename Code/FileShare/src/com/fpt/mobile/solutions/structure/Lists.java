package com.fpt.mobile.solutions.structure;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Provides static methods for creating {@code List} instances easily, and other
 * utility methods for working with lists.
 */
public class Lists {

    /**
     * Creates an empty {@code ArrayList} instance.
     * <p/>
     * <p><b>Note:</b> if you only need an <i>immutable</i> empty List, use
     * {@link Collections#emptyList} instead.
     *
     * @return a newly-created, initially-empty {@code ArrayList}
     */
    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<E>();
    }

    /**
     * Creates a resizable {@code ArrayList} instance containing the given
     * elements.
     * <p/>
     * <p><b>Note:</b> due to a bug in javac 1.5.0_06, we cannot support the
     * following:
     * <p/>
     * <p>{@code List<Base> list = Lists.newArrayList(sub1, sub2);}
     * <p/>
     * <p>where {@code sub1} and {@code sub2} are references to subtypes of
     * {@code Base}, not of {@code Base} itself. To get around this, you must
     * use:
     * <p/>
     * <p>{@code List<Base> list = Lists.<Base>newArrayList(sub1, sub2);}
     *
     * @param elements the elements that the list should contain, in order
     * @return a newly-created {@code ArrayList} containing those elements
     */
    public static <E> ArrayList<E> newArrayList(E... elements) {
        int capacity = (elements.length * 110) / 100 + 5;
        ArrayList<E> list = new ArrayList<E>(capacity);
        Collections.addAll(list, elements);
        return list;
    }

    /**
     * A hack to get currently capacity of ArrayList
     * using Java Reflection APIUtils
     */
    public static int getCapacity(ArrayList<?> l) throws Exception {
        Field dataField = ArrayList.class.getDeclaredField("elementData");
        dataField.setAccessible(true);
        return ((Object[]) dataField.get(l)).length;
    }
}
