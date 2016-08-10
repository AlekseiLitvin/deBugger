package litvin.dao.issue.attachment;

import litvin.dao.GenericHibernateDao;
import litvin.model.project.Attachment;

public class AttachmentDaoHibernate extends GenericHibernateDao<Attachment> implements AttachmentDao{
    @Override
    public void saveAttachment(Attachment attachment) {
        saveEntity(attachment);
    }

    @Override
    public Attachment findAttachmentById(int id) {
        return findById(id);
    }
}
