package litvin.controllers.project.issues.attributes;

import litvin.controllers.ServletUtil;
import litvin.dao.issue.attachment.AttachmentDao;
import litvin.dao.issue.attachment.AttachmentDaoHibernate;
import litvin.model.project.Attachment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "DownloadAttachmentServlet", urlPatterns = "/downloadAttachment/*")
public class DownloadAttachmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathVariable = ServletUtil.getPathVariable(req);

        AttachmentDao attachmentDao = new AttachmentDaoHibernate();
        Attachment attachment = attachmentDao.findAttachmentById(Integer.parseInt(pathVariable));

        int bytesRead;
        OutputStream out = resp.getOutputStream();

        resp.setContentType("image/jsp");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        resp.setHeader("Content-disposition", "attachment; filename = img" + attachment.getId());

        try
        {
            bis = new BufferedInputStream(new ByteArrayInputStream(attachment.getFile()));
            bos = new BufferedOutputStream(out);

            while (-1 != (bytesRead = bis.read())) {
                bos.write(bytesRead);
                bos.flush();
            }
            resp.sendRedirect(req.getHeader("referer"));
        }
        finally
        {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
