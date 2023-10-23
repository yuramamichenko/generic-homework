package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

public class MyListTest {

    @Test
    public void test() {
        Assertions.assertTrue(true);
    }

    @Test
    public void test_add() {
        MyList<Integer> list = new MyList<>();
        int i = list.size();
        list.add(33);
        Assertions.assertEquals(i + 1, list.size());
    }

    @Test
    public void test_get() {
        MyList<Integer> list = new MyList<>();
        list.add(33);
        list.add(55);
        Assertions.assertEquals(55, list.get(1));
    }

    @Test
    public void test_remove() {
        MyList<Integer> list = new MyList<>();
        list.add(33);
        list.add(55);
        int i = list.size();
        Assertions.assertNotNull(list.remove(0));

        Assertions.assertEquals(list.size(), i - 1);
    }

    @Test
    public void test_size() {
        MyList<Integer> list = new MyList<>();
        Assertions.assertEquals(0, list.size());

        list.add(33);
        list.add(55);
        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void test_toString() {
        MyList<Integer> list = new MyList<>();
        Assertions.assertEquals("MyList: is empty.", list.toString());

        list.add(33);
        Assertions.assertEquals("MyList: 33.", list.toString());
    }

    @Test
    public void test_hashCode() {
        MyList<Integer> list1 = new MyList<>();
        list1.add(33);
        list1.add(55);

        MyList<Integer> list2 = new MyList<>();
        list2.add(3);
        list2.add(5);

        Assertions.assertEquals(list1.hashCode(), list2.hashCode());
    }

    @Test
    public void test_equals() {
        MyList<Integer> list1 = new MyList<>();
        list1.add(33);
        list1.add(55);

        MyList<Integer> list2 = new MyList<>();
        list2.add(33);

        Assertions.assertNotEquals(list1.hashCode(), list2.hashCode());

        Assertions.assertNotEquals(list1.size(), list2.size());

        list2.add(55);
        Assertions.assertEquals(list1.toString(), list2.toString());
    }

    @Test
    public void test_iterator() {
        MyList<Integer> list = new MyList<>();
        MyList.InnerClass innerClass = new MyList.InnerClass(list);
        Assertions.assertNotNull(innerClass);

        list.add(33);

        Iterator<Object> iterator = list.iterator();
        Assertions.assertNotNull(iterator);

        Assertions.assertTrue(iterator.hasNext());

        Assertions.assertEquals(33, iterator.next());
    }
}