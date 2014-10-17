package funkyds.api.immutable;

import java.util.Optional;
import java.util.function.Function;

public interface List<E> extends Iterable<E> {
	public List<E> add(E item);

	public Optional<E> get(int index);

	public <F> List<F> map(Function<E, F> mapper);

	public <F> List<F> flatMap(Function<E, List<F>> flatMapper);

	public List<E> filter(Function<E, Boolean> selector);

	public default boolean contains(E item) {

		List<E> cur = this;
		while (!cur.isEmpty()) {
			if (cur.head().get().equals(item)) {
				return true;
			}
		}
		return false;

	}

	public default boolean contains(Function<E, Boolean> selector) {
		List<E> cur = this;
		while (!cur.isEmpty()) {
			if (cur.head().map(selector).get()) {
				return true;
			}
		}
		return false;

	}

	public Optional<E> head();

	public List<E> tail();

	public boolean isEmpty();

	public int length();

	List<E> reverse();

}
