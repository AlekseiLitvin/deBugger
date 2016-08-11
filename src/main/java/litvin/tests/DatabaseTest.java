package litvin.tests;

import litvin.dao.issue.IssueDao;
import litvin.dao.issue.IssueDaoHibernate;
import litvin.dao.user.UserDao;
import litvin.dao.user.UserDaoHibernate;
import litvin.model.project.Issue;
import litvin.model.user.User;
import org.junit.Assert;
import org.junit.Test;

public class DatabaseTest {

    @Test
    public void getUserTest(){
        final String EMAIL = "litvin@mail.ru";
        UserDao userDao = new UserDaoHibernate();
        User actualUser = userDao.getUser(EMAIL);
        if (actualUser != null){
            Assert.assertEquals(EMAIL, actualUser.getEmail());
        }
    }

    @Test
    public void entitiesNumberTest(){
        IssueDao issueDao = new IssueDaoHibernate();
        int size = issueDao.getAllIssues().size();
        int entitiesNumber = issueDao.getEntitiesNumber().intValue();
        Assert.assertEquals(size, entitiesNumber);
    }

    @Test
    public void getIssueByIdTest(){
        final int ID = 2;
        IssueDao issueDao = new IssueDaoHibernate();
        Issue issue = issueDao.findIssueById(ID);
        if (issue != null) {
            Assert.assertEquals(ID, issue.getId());
        }
    }

}
