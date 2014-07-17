package org.moon.test.mybatis.repository;

import org.moon.test.mybatis.persistence.BaseEntity;

public interface BaseRepository<T extends BaseEntity> {

	public void save(T obj);

	public void delete(Long id);

	public void delete(T obj);

	public void update(T obj);

	public T findById(Long id);
	
	public long countAll();
	
	public void deleteAll();
}
