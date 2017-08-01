package com.auto.test.common.dao;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.auto.test.common.constant.Order;
import com.google.common.base.Preconditions;

@SuppressWarnings("unchecked")
public abstract class AbstractHibernateDao <T extends java.io.Serializable> implements IBaseDao<T> {

	private Class<T> clazz;
	
	@Resource(name="sessionFactory")
    private SessionFactory sessionFactory;
	
	protected final void setClazz(final Class<T> clazzToSet) {
        this.clazz = Preconditions.checkNotNull(clazzToSet);
    }
	
	protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

	@Override
	public final Integer findAllCount() {
		return ((Long) getCurrentSession().createQuery("select count(*) from " + clazz.getName()).uniqueResult()).intValue();
	}
	
	@Override
	public Integer findMaxCount() {
		return ((Integer) getCurrentSession().createQuery("select max(id) from " + clazz.getName()).uniqueResult()).intValue();
	}

	@Override
	public Integer findMinCount() {
		return ((Integer) getCurrentSession().createQuery("select min(id) from " + clazz.getName()).uniqueResult()).intValue();
	}

	@Override
	public final T findById(final Integer id) {
		return (T)getCurrentSession().get(clazz, id);
	}

	@Override
	public final List<T> findAll() {
		return getCurrentSession().createQuery("from " + clazz.getName()).list();
	}

	@Override
	public final List<T> findAllOrder() {
		return getCurrentSession().createQuery("from " + clazz.getName() + " order by id desc").list();
	}

	@Override
	public final List<T> findAllOrder(final String field, final Order order) {
		return getCurrentSession().createQuery("from " + clazz.getName() + " order by " + field + " " + order.name()).list();
	}

	@Override
	public final T create(final T entity) {
		Preconditions.checkNotNull(entity);
        getCurrentSession().save(entity);
        return entity;
	}

	@Override
	public final T update(final T entity) {
		Preconditions.checkNotNull(entity);
        getCurrentSession().update(entity);
        return entity;
	}

	@Override
	public final T saveOrUpdate(final T entity) {
		Preconditions.checkNotNull(entity);
        getCurrentSession().saveOrUpdate(entity);
        return entity;
	}

	@Override
	public final void delete(final T entity) {
		Preconditions.checkNotNull(entity);
        getCurrentSession().delete(entity);
	}

	@Override
	public final void deleteById(final Integer entityId) {
		final T entity = findById(entityId);
        Preconditions.checkState(entity != null);
        delete(entity);
	}

	@Override
	public final void evict(final T entity) {
		getCurrentSession().evict(entity);
	}
	
}
