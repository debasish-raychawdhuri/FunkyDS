package funkyds.impl.immutable.util;

import java.util.Optional;
import java.util.function.Function;

import funkyds.api.immutable.List;

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
		return null;
	}

	@Override
	public Optional<F> get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <G> List<G> map(Function<F, G> mapper) {
		return null;
	}

	@Override
	public <G> List<G> flatMap(Function<F, List<G>> flatMapper) {
		// TODO Auto-generated method stub
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
