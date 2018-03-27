package FilterIterable;

public interface FilterCondition<T> {
    boolean eval(T item);
}
