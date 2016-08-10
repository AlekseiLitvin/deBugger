package litvin.dao.issue;

import litvin.model.project.Issue;
import litvin.model.user.User;

import java.util.List;

public interface IssueDao {
    void saveIssue(Issue issue);
    void updateIssue(Issue issue);
    Issue findIssueById(int id);
    List<Issue> getAllIssues();
    List<Issue> getGroupOfIssues(int first, int max);
    Long getEntitiesNumber();
    List<Issue> findBySummary(String summary);
    List<Issue> getIssuesBuUser(User user);
}
