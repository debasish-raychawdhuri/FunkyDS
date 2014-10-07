package funkyds.api.immutable;

import java.util.Optional;
import java.util.function.Function;

public interface List<E> {
	public List<E> add(E item);

	public Optional<E> get(int index);

	public <F> List<F> map(Function<E, F> mapper);

	public <F> List<F> flatMap(Function<E, List<F>> flatMapper);

	public List<E> filter(Function<E, Boolean> selector);

	public boolean contains(E item);

	public boolean contains(Function<E, Boolean> selector);

	public Optional<E> head();

	public List<E> tail();

	public boolean isEmpty();

	public int length();
}
