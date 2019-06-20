package litvin.dao.issue;

import litvin.dao.GenericHibernateDao;
import litvin.dao.config.HibernateUtil;
import litvin.model.project.Issue;
import litvin.model.user.User;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class IssueDaoHibernate extends GenericHibernateDao<Issue> implements IssueDao {

    private static final String ISSUES_TABLE = "issues";

    @Override
    public void saveIssue(Issue issue) {
        saveEntity(issue);
    }

    @Override
    public void updateIssue(Issue issue) {
        updateEntity(issue);
    }

    @Override
    public Issue findIssueById(int id) {
        return findById(id);
    }

    @Override
    public List<Issue> getAllIssues() {
        return getAllEntities(ISSUES_TABLE);
    }

    @Override
    public List<Issue> getGroupOfIssues(int first, int max) {
        return getGroupOfEntities(ISSUES_TABLE, first, max);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Issue> findBySummary(String summary) {
        final int SUMMARY_INDEX = 0;
        Session session = HibernateUtil.getSession();
        Query query = session.createSQLQuery("SELECT * FROM issues WHERE summary = ?").addEntity(Issue.class);
        query.setString(SUMMARY_INDEX, summary);
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Issue> getIssuesBuUser(User user) {
        final int ASSIGNEE_ID_INDEX = 0;
        Session session = HibernateUtil.getSession();
        Query query = session.createSQLQuery("SELECT * FROM issues WHERE assignee_id = ?").addEntity(Issue.class);
        query.setInteger(ASSIGNEE_ID_INDEX, user.getId());
        return query.list();
    }
}
