package sac.millennium.util;

import java.util.List;

public interface IGenericCRUD<T, K> {

	List<T> findAll();

	int create(T obj);

	int update(T obj);

	int delete(K key);

	T findById(K key);

	String generarId();
}
