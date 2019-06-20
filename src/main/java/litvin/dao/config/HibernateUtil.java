package litvin.dao.config;

import litvin.constants.ConstError;
import litvin.exceptions.DaoException;
import litvin.model.project.Attachment;
import litvin.model.project.Comment;
import litvin.model.project.Issue;
import litvin.model.project.Project;
import litvin.model.user.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration conf = new Configuration()
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Project.class)
                    .addAnnotatedClass(Issue.class)
                    .addAnnotatedClass(Comment.class)
                    .addAnnotatedClass(Attachment.class)
                    .configure();

            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
                    .applySettings(conf.getProperties()).buildServiceRegistry();
            sessionFactory = conf.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            System.err.println(ConstError.SESSION_FACTORY_ERROR + e);
            throw new DaoException(e);
        }
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
    }
}

