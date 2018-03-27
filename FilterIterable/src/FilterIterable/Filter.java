package FilterIterable;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.*;

public class Filter implements Iterable<Integer> {
    private Iterable<Integer> it;
    private FilterCondition<Integer> cond;

    public Filter(Iterable<Integer> it, FilterCondition<Integer> cond) {
        this.it = it;
        this.cond = cond;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new FilterIterator();
    }

    private class FilterIterator implements Iterator<Integer> {
        private Iterator<Integer> iterator;
        private Integer current;
        private boolean hasNext;

        public FilterIterator() {
            iterator = it.iterator();
            hasNext = true;
            updateCurrentAndHasNext();
        }

        @Override
        public boolean hasNext() {
            return hasNext;
        }

        @Override
        public Integer next() {
            if (!hasNext) {
                throw new NoSuchElementException();
            }
            int returnValue = current;
            updateCurrentAndHasNext();
            return returnValue;
        }

        private void updateCurrentAndHasNext() {
            try {
                updateCurrent();
            } catch (NoSuchElementException e) {
                hasNext = false;
            }
        }

        private void updateCurrent() throws NoSuchElementException {
            boolean atTrue = false;
            while (!atTrue) {
                current = iterator.next();
                atTrue = cond.eval(current);
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(new Integer[]{0, 11, 20, 13, 14, 50, 66});
        for (int i : new Filter(arr, new EvenCondition())) {
            System.out.print(i + " ");
        }
    }
}
