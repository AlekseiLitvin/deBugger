package litvin.dao.project;

import litvin.dao.GenericHibernateDao;
import litvin.model.project.Project;

import java.util.List;

public class ProjectDaoHibernate extends GenericHibernateDao<Project> implements ProjectDao {

    private static final String PROJECTS_TABLE = "projects";

    @Override
    public void saveProject(Project project) {
        saveEntity(project);
    }

    @Override
    public Project findProjectById(int id) {
        return findById(id);
    }

    @Override
    public void updateProject(Project project) {
        updateEntity(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return getAllEntities(PROJECTS_TABLE);
    }

    @Override
    public List<Project> getGroupOfProjects(int first, int max) {
        return getGroupOfEntities(PROJECTS_TABLE, first, max);
    }
}
