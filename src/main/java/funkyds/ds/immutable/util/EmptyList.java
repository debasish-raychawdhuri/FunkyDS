package funkyds.ds.immutable.util;

import funkyds.ds.immutable.List;

import java.util.Optional;
import java.util.function.Function;

public interface EmptyList<E> extends List<E> {

	@Override
	public default Optional<E> get(int index) {
		return Optional.empty();
	}

	@Override
	public default <F> List<F> map(Function<E, F> mapper) {
		return new MapperList<E, F>(this, mapper);
	}

	@Override
	public default <F> List<F> flatMap(Function<E, List<F>> flatMapper) {
		return new FlatMapperList<E, F>(this, flatMapper);
	}

	@Override
	public default List<E> filter(Function<E, Boolean> selector) {
		return this;
	}

	@Override
	public default boolean contains(E item) {
		return false;
	}

	@Override
	public default boolean contains(Function<E, Boolean> selector) {
		return false;
	}

	@Override
	public default Optional<E> head() {
		return Optional.empty();
	}

	@Override
	public default List<E> tail() {
		return this;
	}

	@Override
	public default boolean isEmpty() {
		return true;
	}

	@Override
	public default int length() {
		return 0;
	}
}
