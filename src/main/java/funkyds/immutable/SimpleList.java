package funkyds.immutable;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

public class SimpleList<E> implements List<E> {
	private final E head;
	private final List<E> tail;
	private final int index;

	protected SimpleList() {
		head = null;
		tail = null;
		index = -1;
	}

	@Override
	public List<E> add(E item) {
		return new SimpleList<E>(item, this);
	}

	public SimpleList(E head, List<E> tail) {
		this.head = head;
		this.tail = tail;
		this.index = tail.length();
	}

	@Override
	public Optional<E> get(int index) {
		List<E> cur = this;
		while (!cur.isEmpty()) {
			if (cur.tail().length() == index) {
				return cur.head();
			}
			cur = cur.tail();
		}
		return Optional.empty();
	}

	/**
	 * The items are iterated in the reverse order of addition
	 */
	@Override
	public Iterator<E> iterator() {
		Iterator<E> iterator = new Iterator<E>() {
			List<E> list = SimpleList.this;

			@Override
			public E next() {
				if (list.isEmpty()) {
					throw new NoSuchElementException(
							"next() called on empty iterator");
				}
				E value = list.head().get();
				list = list.tail();
				return value;
			}

			@Override
			public boolean hasNext() {
				return !list.isEmpty();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException(
						"remove invoked on an iterator backed by a readonly object");
			}
		};
		return iterator;
	}

	@Override
	public List<E> reverse() {
		List<E> l = SimpleList.emptyList();
		for (E item : this) {
			l = l.add(item);
		}
		return l;

	}

	@Override
	public <F> List<F> map(Function<E, F> mapper) {
		List<E> reversed = this.reverse();
		List<F> target = SimpleList.emptyList();
		for (E item : reversed) {
			target = target.add(mapper.apply(item));
		}
		return target;
	}

	@Override
	public <F> List<F> flatMap(Function<E, List<F>> flatMapper) {
		List<E> reversed = this.reverse();
		List<F> target = SimpleList.emptyList();
		for (E item : reversed) {
			for (F member : flatMapper.apply(item).reverse()) {
				target = target.add(member);
			}
		}
		return target;
	}

	@Override
	public List<E> filter(Function<E, Boolean> selector) {
		List<E> reversed = this.reverse();
		List<E> target = SimpleList.emptyList();
		for (E item : reversed) {
			if (selector.apply(item)) {
				target = target.add(item);
			}
		}
		return target;
	}

	@Override
	public Optional<E> head() {
		return Optional.of(head);
	}

	@Override
	public List<E> tail() {
		return tail;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public int length() {
		return index + 1;
	}

	public static <T> SimpleList<T> emptyList() {
		return new SimpleList<T>() {
			@Override
			public boolean isEmpty() {
				return true;
			}

			@Override
			public int length() {
				return 0;
			}

			@Override
			public int hashCode() {
				return -1;
			}

			@SuppressWarnings("rawtypes")
			@Override
			public boolean equals(Object obj) {
				if (obj instanceof SimpleList) {
					return ((SimpleList) obj).isEmpty();
				} else {
					return false;
				}
			}

			@Override
			public String toString() {
				return "[]";
			}

		};
	}

	@Override
	public String toString() {
		return reverse().toString(true);
	}
}
