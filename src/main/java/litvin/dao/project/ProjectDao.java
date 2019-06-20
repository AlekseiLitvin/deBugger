package litvin.dao.project;

import litvin.model.project.Project;

import java.util.List;

public interface ProjectDao {
    void saveProject(Project project);

    void updateProject(Project project);

    Project findProjectById(int id);

    List<Project> getAllProjects();

    List<Project> getGroupOfProjects(int first, int max);

    Long getEntitiesNumber();
}
