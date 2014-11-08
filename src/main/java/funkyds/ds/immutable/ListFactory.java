package funkyds.ds.immutable;

public class ListFactory {
	public static <E> List<E> getEmptySimpleList() {
		return SimpleList.emptyList();
	}

	public static <E> List<E> getEmptyRandomAccessList() {
		return RandomAccessLinkedList.emptyList();
	}
}
