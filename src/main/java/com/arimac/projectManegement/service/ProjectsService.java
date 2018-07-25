package com.arimac.projectManegement.service;

import com.arimac.projectManegement.dao.ProjectsDao;
import com.arimac.projectManegement.model.Project;

import java.sql.SQLException;
import java.util.List;

public class ProjectsService {

    ProjectsDao projectsDao = new ProjectsDao();

    public List<Project> getProjects() throws SQLException, ClassNotFoundException {
        return projectsDao.getAllProjects();
    }

    public Project getOneProject(String name) throws SQLException, ClassNotFoundException {
        return projectsDao.getOneProject(name);
    }

    public int saveProject(Project project) throws SQLException, ClassNotFoundException {
        return projectsDao.saveProject(project);
    }

    public int updateProject(String name , Project project) throws SQLException, ClassNotFoundException {
        return projectsDao.UpdateProject(name,project);
    }

}
