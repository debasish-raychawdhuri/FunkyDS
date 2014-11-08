package funkyds.ds.immutable;

import org.junit.Test;
import static org.junit.Assert.*;

public class SimpleListTest {
	@Test
	public void testAdd() {
		List<Integer> list = ListFactory.getEmptySimpleList();
		list = list.add(1).add(3).add(5).add(7);
		assertEquals(5, list.get(2).get().intValue());
	}

	@Test
	public void testMap() {
		List<Integer> list = ListFactory.getEmptySimpleList();
		list = list.add(1).add(3).add(5).add(7);
		list = list.map((x) -> x * 2);
		assertEquals(10, list.get(2).get().intValue());
	}

	@Test
	public void testFlatMap() {
		List<Integer> list = ListFactory.getEmptySimpleList();
		list = list.add(1).add(3).add(5).add(7);
		list = list.flatMap((x) -> {
			List<Integer> l = ListFactory.getEmptySimpleList();
			for (int i = 1; i <= x; i++) {
				l = l.add(i);
			}

			System.out.println(l);
			return l;
		});
		assertEquals(5, list.get(8).get().intValue());
		assertEquals(2, list.get(10).get().intValue());
	}

	@Test
	public void testContains() {
		List<Integer> list = ListFactory.getEmptySimpleList();
		list = list.add(1).add(3).add(5).add(7);

		assertTrue(list.contains(3));
		assertFalse(list.contains(4));

		assertTrue(list.contains((x) -> x == 3));
		assertFalse(list.contains((x) -> x == 4));
	}

	@Test
	public void testFilter() {
		List<Integer> list = ListFactory.getEmptySimpleList();
		list = list.add(1).add(3).add(5).add(7);
		list = list.filter((x) -> x > 2);
		assertTrue(list.contains(3));
		assertFalse(list.contains(1));
	}
}
