package funkyds.impl.immutable;

import java.util.Optional;
import java.util.function.Function;

import funkyds.api.immutable.List;

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
		maxIndex = calcLog2(index + 1);
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
			int logDistance = calcMaxIndex(distance);
			int jumpIndex = min(index, logDistance);
			System.out.println("jump " + jumpIndex);
			return links[jumpIndex].get(target);

		}
	}

	@Override
	public <F> List<F> map(Function<E, F> mapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <F> List<F> flatMap(Function<E, List<F>> flatMapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> filter(Function<E, Boolean> selector) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(E item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Function<E, Boolean> selector) {
		// TODO Auto-generated method stub
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

	public static void main(String[] args) {
		List<Integer> list = RandomAccessLinkedList.emptyList();
		for (int i = 0; i < 2000000; i++) {
			list = list.add(i + 2);
		}

		System.out.println(list.get(1021));
		System.out.println(calcMaxIndex(6));
	}

	@Override
	public String toString() {
		return toString(true);
	}

	public String toString(boolean isOuter) {
		String headString;
		String tailString;
		if (isOuter) {
			headString = "[";
			tailString = "]";
		} else {
			headString = "";
			tailString = "";
		}
		return headString
				+ ((links[0].isEmpty()) ? head.toString() : head.toString()
						+ ", " + links[0].toString(false)) + tailString;
	}
}