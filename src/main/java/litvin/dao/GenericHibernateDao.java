package litvin.dao;

import litvin.constants.Constants;
import litvin.dao.config.HibernateUtil;
import litvin.exceptions.DaoException;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class GenericHibernateDao<T> implements GenericDao<T> {

    private Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public GenericHibernateDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    @Override
    public void saveEntity(T entity) {
        Session session = HibernateUtil.getSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.save(entity);
            tr.commit();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
            }
            throw new DaoException(e);
        } finally {
            session.close();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findById(int id) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            Criteria criteria = session.createCriteria(getPersistentClass());
            criteria.add(Restrictions.eq(Constants.ID, id));
            return (T) criteria.uniqueResult();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void updateEntity(T entity) {
        Session session = HibernateUtil.getSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            synchronized (GenericHibernateDao.class) {
                session.update(entity);
                tr.commit();
            }
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
            }
            throw new DaoException(e);
        } finally {
            session.close();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAllEntities(String source) {
        Session session = HibernateUtil.getSession();
        Query query = session.createSQLQuery("SELECT * FROM " + source).addEntity(getPersistentClass());
        return query.list();
    }


    @Override
    public Long getEntitiesNumber() {
        Session session = HibernateUtil.getSession();
        Criteria criteria = session.createCriteria(getPersistentClass());
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();
    }

    @Override
    public List<T> getGroupOfEntities(String source, int first, int max) {
        Session session = HibernateUtil.getSession();
        Query query = session.createSQLQuery("SELECT * FROM " + source).addEntity(getPersistentClass());

        query.setFirstResult(first);
        query.setMaxResults(max);

        return query.list();
    }
}
