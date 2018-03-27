package FilterIterable;

public class EvenCondition implements FilterCondition<Integer> {
    public boolean eval(Integer i) {
        return i % 2 == 0;
    }
}
