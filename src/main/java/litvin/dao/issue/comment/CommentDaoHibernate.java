package litvin.dao.issue.comment;

import litvin.dao.GenericHibernateDao;
import litvin.model.project.Comment;

public class CommentDaoHibernate extends GenericHibernateDao<Comment> implements CommentDao {
    @Override
    public void saveComment(Comment comment) {
        saveEntity(comment);
    }
}
