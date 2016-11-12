package seminar1.collections;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayStack<Item> implements IStack<Item> {

    private static final int DEFAULT_CAPACITY = 10;

    private Item[] elementData;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        this.elementData = (Item[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void push(Item item) {
        if(size==(elementData.length)) grow();
        elementData[size++]=item;

        /* TODO: implement it */
    }

    public Item show(){
if(size!=0)
        return elementData[size-1];
        else  return null;
    }
    @Override
    public Item pop() {
        if(isEmpty()) {
            System.out.println("stack is empty");
            return  null;
        }
        return elementData[--size];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void grow() {
        {
            int oldcap=elementData.length;
            int newcap=(oldcap<<1);
            changeCapacity(newcap);

        }
    }

    private void shrink() {
        int oldcap=elementData.length;
        if((size==oldcap>>2)&&(DEFAULT_CAPACITY < oldcap>>1)){

            changeCapacity(oldcap>>1);
        }
        /**
         * TODO: implement it
         * Если количество элементов в четыре раза меньше,
         * то уменьшить его размер в два раза
         */
    }

    private void changeCapacity(int newCapacity) {
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayStackIterator();
    }

    private class ArrayStackIterator implements Iterator<Item> {

        private int currentPosition = size;

        @Override
        public boolean hasNext() {
            return currentPosition != 0;
        }

        @Override
        public Item next() {
            return elementData[--currentPosition];
        }

    }

}
