package funkyds.immutable;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

public class RandomAccessLinkedList<E> implements List<E> {

	public final RandomAccessLinkedList<E>[] links;
	private final E head;
	private final int index;
	private final int maxIndex;

	protected RandomAccessLinkedList() {
		this.head = null;
		this.index = -1;
		this.maxIndex = 0;
		this.links = null;
	}

	@SuppressWarnings("unchecked")
	public RandomAccessLinkedList(E head, RandomAccessLinkedList<E> tail) {
		this.head = head;
		this.index = tail.length();
		maxIndex = calcMaxIndex(index + 1);
		links = new RandomAccessLinkedList[maxIndex + 1];
		links[0] = tail;
		for (int i = 1; i <= maxIndex; i++) {
			links[i] = links[i - 1].links[i - 1];
		}
	}

	public static <T> RandomAccessLinkedList<T> emptyList() {
		return new RandomAccessLinkedList<T>() {
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
				if (obj instanceof RandomAccessLinkedList) {
					return ((RandomAccessLinkedList) obj).isEmpty();
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
	public List<E> add(E item) {

		return new RandomAccessLinkedList<E>(item, this);
	}

	@Override
	public Optional<E> get(int target) {
		if (this.isEmpty()) {
			return Optional.empty();
		}
		int distance = index - target;
		if (distance == 0) {
			return Optional.of(head);
		} else if (distance < 0) {
			return Optional.empty();
		} else {
			int logDistance = calcLog2(distance);
			int jumpIndex = min(maxIndex, logDistance);
			return links[jumpIndex].get(target);

		}
	}

	/**
	 * The items are iterated in the reverse order of addition
	 */
	@Override
	public Iterator<E> iterator() {
		Iterator<E> iterator = new Iterator<E>() {
			List<E> list = RandomAccessLinkedList.this;

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
		List<E> l = RandomAccessLinkedList.emptyList();
		for (E item : this) {
			l = l.add(item);
		}
		return l;

	}

	@Override
	public <F> List<F> map(Function<E, F> mapper) {
		List<E> reversed = this.reverse();
		List<F> target = RandomAccessLinkedList.emptyList();
		for (E item : reversed) {
			target = target.add(mapper.apply(item));
		}
		return target;
	}

	@Override
	public <F> List<F> flatMap(Function<E, List<F>> flatMapper) {
		List<E> reversed = this.reverse();
		List<F> target = RandomAccessLinkedList.emptyList();
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
		List<E> target = RandomAccessLinkedList.emptyList();
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
		return links[0];
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public int length() {
		return index + 1;
	}

	protected static int calcLog2(int x) {
		int i;
		for (i = 0; x > 0; i++) {
			x = x >>> 1;
		}
		return i - 1;
	}

	protected static int calcMaxIndex(int index) {
		int i;
		for (i = 0; (index & 0x0001) == 0; i++) {
			index = index >>> 1;
		}
		return i;
	}

	protected static int min(int x, int y) {
		return x > y ? y : x;
	}

	@Override
	public String toString() {
		return reverse().toString(true);
	}

}
