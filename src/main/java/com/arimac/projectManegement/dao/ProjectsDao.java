package com.arimac.projectManegement.dao;

import com.arimac.projectManegement.dbConnection.DBConnection;
import com.arimac.projectManegement.model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectsDao {


    public List<Project> getAllProjects () throws SQLException, ClassNotFoundException {

        Connection con = DBConnection.getDBConnection().getConnection();
        String sql = "Select * From project";
        Statement stm = con.createStatement();
        ResultSet rst = stm.executeQuery(sql);
        ArrayList<Project> cust = new ArrayList<Project>();

        while(rst.next()){
            Project project = new Project(rst.getString("name"),rst.getString("start_date"),rst.getString("end_date"),rst.getInt("status"),rst.getString("description"));
            cust.add(project);
        }
        return cust;
    }

    public Project getOneProject(String name) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getDBConnection().getConnection();
        Statement stm = con.createStatement();
        String sql = "Select * From project where name = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setObject(1,name);
        ResultSet rst = preparedStatement.executeQuery();

        if(rst.next()){
            Project project = new Project(rst.getString("name"),rst.getString("start_date"),rst.getString("end_date"),rst.getInt("status"),rst.getString("description"));
            return project;
        }else{
            return null;
        }

    }

    public int saveProject(Project project) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getDBConnection().getConnection();
        String sql = "Insert into project (name,start_date,end_date,status,description)"
                + " values (?,?,?,?,?)";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setObject(1, project.getName());
        preparedStatement.setObject(2, project.getStart_date());
        preparedStatement.setObject(3, project.getEnd_date());
        preparedStatement.setObject(4, project.getStatus());
        preparedStatement.setObject(5, project.getDescription());
        return preparedStatement.executeUpdate();
    }

    public int UpdateProject(String name , Project project) throws SQLException, ClassNotFoundException {

        int id = getId(name);

        Connection conn=DBConnection.getDBConnection().getConnection();
        String sql = "Update project set name = ?,start_date=?,end_date=?,status=?,description=? where project_id= ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setObject(1, project.getName());
        preparedStatement.setObject(2, project.getStart_date());
        preparedStatement.setObject(3, project.getEnd_date());
        preparedStatement.setObject(4, project.getStatus());
        preparedStatement.setObject(5, project.getDescription());
        preparedStatement.setObject(6, id);
        return preparedStatement.executeUpdate();
    }

    public int getId(String name) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getDBConnection().getConnection();
        Statement stm = con.createStatement();
        String sql = "Select * From project where name = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setObject(1,name);
        ResultSet rst = preparedStatement.executeQuery();

        if(rst.next()){
           int id = rst.getInt("project_id");
            return id;
        }else{
            return 0;
        }
    }
}
