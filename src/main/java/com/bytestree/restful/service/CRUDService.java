package com.bytestree.restful.service;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author bytesTree
 * @see <a href="http://www.bytestree.com/">BytesTree</a>
 * 
 */
public interface CRUDService<E> {

	E save(E entity);

	E getById(Serializable id);

	List<E> getAll();

	void delete(Serializable id);
}
