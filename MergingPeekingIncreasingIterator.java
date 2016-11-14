package seminar1.iterators;

import seminar1.collections.ArrayPriorityQueue;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Итератор возвращающий последовательность из N возрастающих итераторов в порядке возрастания
 * first = 1,3,4,5,7
 * second = 0,2,4,6,8
 * result = 0,1,2,3,4,4,5,6,7,8
 *
 * Time = O(n + k * log n),
 *  n — количество итераторов
 *  k — суммарное количество элементов
 */
public class MergingPeekingIncreasingIterator implements Iterator<Integer> {


    ArrayPriorityQueue<Integer> queue;
    private Comparator<PeekingIncreasingIterator> comparator = (p1, p2) -> p1.peek().compareTo(p2.peek());

    public MergingPeekingIncreasingIterator(IPeekingIterator... peekingIterator) {
        /* TODO: implement it */
        for (int i = 0; i < peekingIterator.length; i++) {
            while ( peekingIterator[i].hasNext())
                queue.add((Integer)peekingIterator[i].next());

        }
    }

    @Override
    public boolean hasNext() {
        return (queue.size()!=0);
    }

    @Override
    public Integer next() {
        return queue.extractMin();
    }
}
