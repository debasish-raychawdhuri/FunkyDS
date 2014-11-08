package funkyds.api.immutable;

import funkyds.impl.immutable.RandomAccessLinkedList;
import funkyds.impl.immutable.SimpleList;

public class ListFactory {
	public static <E> List<E> getEmptySimpleList() {
		return SimpleList.emptyList();
	}

	public static <E> List<E> getEmptyRandomAccessList() {
		return RandomAccessLinkedList.emptyList();
	}
}
