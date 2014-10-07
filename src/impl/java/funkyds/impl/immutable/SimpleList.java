package funkyds.impl.immutable;

import java.util.Optional;
import java.util.function.Function;

import funkyds.api.immutable.List;

public class SimpleList<E> implements List<E> {
	private final E head;
	private final List<E> tail;
	private final int index;

	@Override
	public List<E> add(E item) {
		return new SimpleList<E>(item, tail);
	}

	protected SimpleList(E head, List<E> tail) {
		this.head = head;
		this.tail = tail;
		this.index = tail.length();
	}

	@Override
	public Optional<E> get(int index) {
		if (this.isEmpty()) {
			return Optional.empty();
		} else if (index == this.index) {
			return Optional.of(head);
		} else {
			return tail.get(index);
		}
	}

	@Override
	public <F> List<F> map(Function<E, F> mapper) {
		return null;
	}

	@Override
	public <F> List<F> flatMap(Function<E, List<F>> flatMapper) {
		return null;
	}

	@Override
	public List<E> filter(Function<E, Boolean> selector) {
		return null;
	}

	@Override
	public boolean contains(E item) {
		return false;
	}

	@Override
	public boolean contains(Function<E, Boolean> selector) {
		return false;
	}

	@Override
	public Optional<E> head() {
		return null;
	}

	@Override
	public List<E> tail() {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public int length() {
		return index + 1;
	}

}
