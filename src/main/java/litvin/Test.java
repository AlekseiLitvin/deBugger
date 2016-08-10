package litvin;

import litvin.constants.Constants;
import litvin.dao.GenericHibernateDao;
import litvin.dao.config.HibernateUtil;
import litvin.dao.issue.IssueDao;
import litvin.dao.issue.IssueDaoHibernate;
import litvin.dao.issue.attributes.AttrDao;
import litvin.dao.issue.attributes.AttrDaoFile;
import litvin.dao.project.ProjectDao;
import litvin.dao.project.ProjectDaoHibernate;
import litvin.dao.user.UserDao;
import litvin.dao.user.UserDaoHibernate;
import litvin.model.project.Issue;
import litvin.model.project.Project;
import litvin.model.user.Role;
import litvin.model.user.User;
import litvin.service.MD5Encoder;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Test {

    public static void main(String[] args) throws IOException, URISyntaxException {
        new Test().test();
    }

    public void test() throws IOException, URISyntaxException {
        System.out.println(MD5Encoder.getEncodedString("22222"));

    }


}
