package litvin.dao.issue.attachment;

import litvin.model.project.Attachment;

public interface AttachmentDao {
    void saveAttachment(Attachment attachment);
    Attachment findAttachmentById(int id);
}
