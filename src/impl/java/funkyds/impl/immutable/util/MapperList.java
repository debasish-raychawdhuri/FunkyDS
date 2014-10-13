package funkyds.impl.immutable.util;

import java.util.Optional;
import java.util.function.Function;

import funkyds.api.immutable.List;
import funkyds.impl.immutable.SimpleList;

public class MapperList<E, F> implements List<F> {

	private final List<E> containedList;
	private final Function<E, F> mapper;

	public MapperList(List<E> containedList, Function<E, F> mapper) {
		super();
		this.containedList = containedList;
		this.mapper = mapper;
	}

	@Override
	public List<F> add(F item) {
		return new SimpleList<F>(item, this);
	}

	@Override
	public Optional<F> get(int index) {
		return containedList.get(index).map(mapper);
	}

	@Override
	public <G> List<G> map(Function<F, G> mapper) {
		return new MapperList<F, G>(this, mapper);
	}

	@Override
	public <G> List<G> flatMap(Function<F, List<G>> flatMapper) {
		return null;
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
		} else if (containedList.head().map(mapper)
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
		} else if (containedList.head().map(mapper).map(selector).get()) {
			return true;
		} else {
			return tail().contains(selector);
		}
	}

	@Override
	public Optional<F> head() {
		return containedList.head().map(mapper);
	}

	@Override
	public List<F> tail() {
		return containedList.tail().map(mapper);
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
