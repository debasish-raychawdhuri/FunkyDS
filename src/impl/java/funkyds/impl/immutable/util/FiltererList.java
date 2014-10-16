package funkyds.impl.immutable.util;

import java.util.Optional;
import java.util.function.Function;

import funkyds.api.immutable.List;
import funkyds.impl.immutable.SimpleList;

public class FiltererList<E> implements List<E> {

	private final List<E> containedList;

	private final Function<E, Boolean> condition;

	public FiltererList(List<E> containedList, Function<E, Boolean> condition) {
		super();
		this.containedList = containedList;
		this.condition = condition;
	}

	@Override
	public List<E> add(E item) {
		return new SimpleList<E>(item, this);
	}

	@Override
	public Optional<E> get(int index) {
		if (index == 0 && containedList.head().map(condition).get()) {
			return containedList.head();
		} else if (containedList.isEmpty()) {
			return Optional.empty();
		} else if (containedList.head().map(condition).get()) {
			return tail().get(index - 1);
		} else {
			return tail().get(index);
		}
	}

	@Override
	public <F> List<F> map(Function<E, F> mapper) {
		return new MapperList<E, F>(this, mapper);
	}

	@Override
	public <F> List<F> flatMap(Function<E, List<F>> flatMapper) {
		return new FlatMapperList<E, F>(this, flatMapper);
	}

	@Override
	public List<E> filter(Function<E, Boolean> selector) {
		return new FiltererList<E>(this, selector);
	}

	@Override
	public boolean contains(E item) {
		if (condition.apply(item)) {
			return containedList.contains(item);
		} else {
			return false;
		}
	}

	@Override
	public boolean contains(Function<E, Boolean> selector) {
		return containedList.contains(new Function<E, Boolean>() {
			@Override
			public Boolean apply(E t) {
				return selector.apply(t) && condition.apply(t);
			}

		});
	}

	@Override
	public Optional<E> head() {
		if (containedList.head().map(condition).get()) {
			return containedList.head();
		} else {
			return tail().head();
		}
	}

	@Override
	public List<E> tail() {
		return new FiltererList<E>(containedList.tail(), condition);
	}

	@Override
	public boolean isEmpty() {
		if (containedList.isEmpty()) {
			return true;
		} else if (containedList.head().map(condition).get()) {
			return false;
		} else {
			return tail().isEmpty();
		}
	}

	@Override
	public int length() {
		if (containedList.isEmpty()) {
			return 0;
		} else if (containedList.head().map(condition).get()) {
			return 1 + tail().length();
		} else {
			return tail().length();
		}
	}

}
