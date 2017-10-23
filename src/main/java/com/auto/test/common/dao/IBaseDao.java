package com.auto.test.common.dao;

import java.util.List;
import com.auto.test.common.constant.Order;

public interface IBaseDao <T extends java.io.Serializable> {

	Integer findAllCount();
	
	Integer findMaxCountBy(String field, String text);

	Integer findMaxCount();

	Integer findMinCount();
	
	T findById(final Integer id);

    List<T> findAll();
    
    List<T> findAllOrder();

    List<T> findAllOrder(final String field, final Order order);
    
    T create(final T entity);

    T update(final T entity);
    
    T saveOrUpdate(final T entity);

    void delete(final T entity);

    void deleteById(final Integer entityId);
    
    void evict(final T entity);

}
