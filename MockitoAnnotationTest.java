package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MockitoAnnotationTest {

    @Mock
    MyList<Integer> myList;

    @Spy
    MyList<Integer> myList1;

    @Test
    public void test_mock() {
        myList.add(22);
        myList.add(44);
        int current = myList.size();
        int expected = 0;

        Assertions.assertEquals(expected, current);
    }

    @Test
    public void test_spy() {
        myList1.add(22);
        myList1.add(44);
        int current = myList1.size();
        int expected = 2;

        Assertions.assertEquals(expected, current);
    }

    @Test
    public void test_doReturn() {
        Mockito.doReturn(999).when(myList).size();

        Assertions.assertEquals(999, myList.size());
    }

    @Test
    public void test_doReturn_with_params() {
        Mockito.doReturn(9000).when(myList).get(9);

        Assertions.assertEquals(9000, myList.get(9));
    }

    @Test
    public void test_verify() {
        int size = myList.size();

        Mockito.verify(myList).size();
    }

    @Test
    public void test_inOrder() {
        myList.add(100);
        myList.size();
        myList.remove(0);

        InOrder inOrder = Mockito.inOrder(myList);

        inOrder.verify(myList).add(100);
        inOrder.verify(myList).size();
        inOrder.verify(myList).remove(0);
    }

    @Test
    public void test_thenThrow() {
        Mockito.when(myList.size()).thenThrow(IllegalStateException.class);

        Assertions.assertThrows(IllegalStateException.class, () -> myList.size());
    }
}
