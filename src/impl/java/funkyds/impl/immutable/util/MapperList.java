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
		return new MapperList<F,G>(this, mapper);
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Function<F, Boolean> selector) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<F> head() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<F> tail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int length() {
		// TODO Auto-generated method stub
		return 0;
	}

}
