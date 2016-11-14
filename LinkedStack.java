package seminar1.collections;

import java.util.Iterator;

public class LinkedStack<Item> implements IStack<Item> {

    private Node<Item> head;
    private int size;

    @Override
    public void push(Item item) {
        Node<Item> tmp=head;
        head=new Node<Item>(item,tmp);
        size++;
    }

    @Override
    public Item pop() {
        Item res;
        if(head!=null){
            res= head.item;
            head=head.next;
            return res;

        }
        else return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedStackIterator();
    }

    private class LinkedStackIterator implements Iterator<Item> {
        Node<Item> lol;
        LinkedStackIterator() {

            lol=head;
        }
        @Override
        public boolean hasNext() {
            if(lol.next!=null)
                return  true;
            else
                return false;
        }

        @Override
        public Item next() {

            lol=lol.next;
            return lol.item;
        }

    }

    private static class Node<Item> {
        Item item;
        Node<Item> next;

        public Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }
}
