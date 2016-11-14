package seminar1.iterators;

import java.util.Iterator;

/**
 * Итератор возвращающий последовательность из двух возрастающих итераторов в порядке возрастания
 * first = 1,3,4,5,7
 * second = 0,2,4,6,8
 * result = 0,1,2,3,4,4,5,6,7,8
 *
 * Time = O(k),
 *  k — суммарное количество элементов
 */
public class MergingIncreasingIterator implements Iterator<Integer> {

    private IncreasingIterator first;
    private IncreasingIterator second;
    private Integer one;
    private Integer two;
    public MergingIncreasingIterator(IncreasingIterator first, IncreasingIterator second) {
        this.first = first;
        this.second = second;

        if(first.hasNext()) one=first.next();
        if(second.hasNext())two=second.next();
    }

    @Override
    public boolean hasNext() {
        if(first.hasNext()||second.hasNext()) return  true;
        return false;
    }

    @Override
    public Integer next() {
        int tmp1;
        if(one==null){tmp1= second.next();}
        if(two==null){tmp1=first.next();}
        if(one>two) {
            tmp1=two;
            if(second.hasNext())
            two=second.next();
            else two=null;
        }
        else{
            tmp1=one;
            if(first.hasNext())
            one=first.next();
            else one=null;
        }

        return tmp1;
        //if(second.hasNext()&&second)
        //return null;
    }
}
