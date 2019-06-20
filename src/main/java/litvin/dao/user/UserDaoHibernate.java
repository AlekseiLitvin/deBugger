package litvin.dao.user;

import litvin.constants.Constants;
import litvin.dao.GenericHibernateDao;
import litvin.dao.config.HibernateUtil;
import litvin.exceptions.DaoException;
import litvin.model.user.User;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UserDaoHibernate extends GenericHibernateDao<User> implements UserDao {

    @Override
    public User getUser(String email) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq(Constants.EMAIL, email));
            return (User) criteria.uniqueResult();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean saveUser(String firstName, String lastName, String email, String role, String password) {
        final String UPDATE_PASSWORD_QUERY = "UPDATE users SET password = ? WHERE email = ?";
        final int PASSWORD_INDEX = 0, EMAIL_INDEX = 1;
        Session session = HibernateUtil.getSession();
        Transaction tr = null;
        try {
            synchronized (UserDaoHibernate.class) {
                if (getUser(email) == null) {
                    tr = session.beginTransaction();
                    session.save(new User(firstName, lastName, email, role));
                    SQLQuery query = session.createSQLQuery(UPDATE_PASSWORD_QUERY);
                    query.setString(PASSWORD_INDEX, password);
                    query.setString(EMAIL_INDEX, email);
                    query.executeUpdate();
                    tr.commit();
                    return true;
                } else {
                    return false;
                }
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

    @Override
    public boolean checkPassword(String email, String password) {
        final String SELECT_PASSWORD_BY_LOGIN_QUERY = "SELECT password FROM users WHERE email = ?";
        final int EMAIL_INDEX = 0;
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            SQLQuery query = session.createSQLQuery(SELECT_PASSWORD_BY_LOGIN_QUERY);
            query.setString(EMAIL_INDEX, email);
            String userPassword = (String) query.uniqueResult();
            return password.equals(userPassword);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void updateUser(User user) {
        updateEntity(user);
    }

    @Override
    public List<User> getAllUsers() {
        final String USERS_TABLE = "users";
        return getAllEntities(USERS_TABLE);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findByName(String firstName, String lastName) {
        int FIRST_NAME_INDEX = 0, LAST_NAME_INDEX = 1;
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            Query query = session.createSQLQuery("SELECT * FROM users WHERE firstName = ? AND lastName = ?").
                    addEntity(User.class);
            query.setString(FIRST_NAME_INDEX, firstName);
            query.setString(LAST_NAME_INDEX, lastName);
            return query.list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void updatePassword(User user, String newPassword) {
        final String UPDATE_PASSWORD_QUERY = "UPDATE users SET password = ? WHERE email = ?";
        final int PASSWORD_INDEX = 0, EMAIL_INDEX = 1;
        Session session = HibernateUtil.getSession();
        Transaction tr = null;
        try {
            synchronized (UserDaoHibernate.class) {
                tr = session.beginTransaction();
                SQLQuery query = session.createSQLQuery(UPDATE_PASSWORD_QUERY);
                query.setString(PASSWORD_INDEX, newPassword);
                query.setString(EMAIL_INDEX, user.getEmail());
                query.executeUpdate();
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

}
