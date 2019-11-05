package com.msw.ventas.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class GenericServiceImp<T, ID extends Serializable> implements GenericServiceApi<T, ID> {

	@Override
	public T save(T entity) {
		return getDao().save(entity);
	}

	@Override
	public void delete(ID id) {
		getDao().deleteById(id);
	}

	@Override
	public T get(ID id) {
		Optional<T> optional = getDao().findById(id);
		if(optional.isPresent())
			return optional.get();
		return null;
	}

	@Override
	public List<T> getAll() {
		List<T> list = new ArrayList<>();
		getDao().findAll().forEach(obj -> list.add(obj));
		return list;
	}

	public abstract CrudRepository<T, ID> getDao();
}
