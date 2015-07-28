package se.gokopen.service;

import java.util.Comparator;

public class BeanComparator implements Comparator<Object> {

    private String getter;

    public BeanComparator(String field) {
        this.getter = "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
    }

    @SuppressWarnings("unchecked")
    public int compare(Object o1, Object o2) {
        try {
            if (o1 != null && o2 != null) {
                o1 = o1.getClass().getMethod(getter, new Class[0]).invoke(o1, new Object[0]);
                o2 = o2.getClass().getMethod(getter, new Class[0]).invoke(o2, new Object[0]);
            }
        } catch (Exception e) {
            // If this exception occurs, then it is usually a fault of the developer.
            throw new RuntimeException("Cannot compare " + o1 + " with " + o2 + " on " + getter, e);
        }

        return (o1 == null) ? -1 : ((o2 == null) ? 1 : ((Comparable<Object>) o1).compareTo(o2));
    }

}