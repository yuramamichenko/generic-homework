package org.example;

import java.util.Iterator;

public class MyList<T extends Number> implements Iterable<Object> {

    private Number[] numbers;

    public void add(Number o) {
        if (numbers == null) {
            numbers = new Number[]{o};
        } else {
            Number[] copy = new Number[numbers.length + 1];
            for (int i = 0; i < copy.length; i++) {
                if (i == copy.length - 1) {
                    copy[i] = o;
                } else {
                    copy[i] = numbers[i];
                }
            }
            numbers = copy;
        }
    }

    public Object get(int index) {
        return numbers[index];
    }

    public Object remove(int index) {
        Object removed = get(index);
        if (numbers.length == 1) {
            numbers = null;
        } else {
            Number[] copy = new Number[numbers.length - 1];
            for (int i = 0, j = 0; i < numbers.length; i++) {
                if (i == index) {
                    continue;
                } else {
                    copy[j++] = numbers[i];
                }
            }
            numbers = copy;
        }
        return removed;
    }

    public int size() {
        if (numbers == null) {
            return 0;
        } else {
            return numbers.length;
        }
    }

    public static class InnerClass implements Iterator {
        private MyList myList;

        public InnerClass(MyList myList) {
            this.myList = myList;
        }

        @Override
        public boolean hasNext() {
            return myList.size() > 0;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                return myList.remove(0);
            } else {
                return null;
            }
        }
    }

    @Override
    public Iterator<Object> iterator() {
        return new InnerClass(this);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("MyList: ");
        if (numbers == null) {
            stringBuilder.append("is empty.");
        } else {
            for (int i = 0; i < numbers.length; i++) {
                if (i == numbers.length - 1) {
                    stringBuilder.append(numbers[i]).append(".");
                } else {
                    stringBuilder.append(numbers[i]).append(", ");
                }
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MyList)) {
            return false;
        }

        MyList<?> myList = (MyList<?>) o;

        if (hashCode() != myList.hashCode()) {
            return false;
        }

        if (this.size() != myList.size()) {
            return false;
        }

//        for (int i = 0; i < numbers.length; i++) {
//            if (!Objects.equals(this.get(i).toString(), myList.get(i).toString())) {
//                return false;
//            }
//        }
        return this.toString().equals(myList.toString());
    }

    @Override
    public int hashCode() {
        if (numbers == null) {
            return 0;
        } else {
            int hash = 0;
            for (Number number : numbers) {
                int d = number.toString().charAt(0);
                hash += d;
            }
            return hash;
        }
    }

//    private void resize() {
//        throw new RuntimeException("Not implemented");
//    }
//
//    public MyList<? extends Number> map(Function f) {
//        MyList<? extends Number> myList = new MyList<>();
//        for (int i = 0; i < this.size(); i++) {
//            myList.add((Number) f.apply(numbers[i]));
//        }
//        return myList;
//    }
}