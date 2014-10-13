package funkyds.impl.immutable.util;

import java.util.Optional;
import java.util.function.Function;

import funkyds.api.immutable.List;
import funkyds.impl.immutable.SimpleList;

public class FlatMapperList<E, F> implements List<F> {
	private final List<E> containedList;
	private final Function<E, List<F>> flatMapper;

	public FlatMapperList(List<E> containedList, Function<E, List<F>> flatMapper) {
		super();
		this.containedList = containedList;
		this.flatMapper = flatMapper;
	}

	@Override
	public List<F> add(F item) {
		return new SimpleList<F>(item, this);
	}

	@Override
	public Optional<F> get(int index) {
		return containedList.flatMap(flatMapper).get(index);
	}

	@Override
	public <G> List<G> map(Function<F, G> mapper) {
		return new MapperList<F, G>(this, mapper);
	}

	@Override
	public <G> List<G> flatMap(Function<F, List<G>> flatMapper) {
		return new FlatMapperList<F, G>(this, flatMapper);
	}

	@Override
	public List<F> filter(Function<F, Boolean> selector) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(F item) {
		if (containedList.isEmpty()) {
			return false;
		} else if (containedList.flatMap(flatMapper).head()
				.equals(Optional.ofNullable(item))) {
			return true;
		} else {
			return tail().contains(item);
		}
	}

	@Override
	public boolean contains(Function<F, Boolean> selector) {
		if (containedList.isEmpty()) {
			return false;
		} else if (containedList.flatMap(flatMapper).head().map(selector).get()) {
			return true;
		} else {
			return tail().contains(selector);
		}
	}

	@Override
	public Optional<F> head() {
		return containedList.flatMap(flatMapper).head();

	}

	@Override
	public List<F> tail() {
		return containedList.flatMap(flatMapper).tail();
	}

	@Override
	public boolean isEmpty() {
		return containedList.isEmpty();
	}

	@Override
	public int length() {
		return containedList.length();
	}
}
