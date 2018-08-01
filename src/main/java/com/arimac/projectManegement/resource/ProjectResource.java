package com.arimac.projectManegement.resource;

import com.arimac.projectManegement.Annotations.Secured;
import com.arimac.projectManegement.Filter.RoleDetails.Roles;
import com.arimac.projectManegement.model.Project;
import com.arimac.projectManegement.service.ProjectsService;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("projects")
@Consumes(value = {MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
@Produces(value = {MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public class ProjectResource {

    ProjectsService projectsService = new ProjectsService();

    @GET
    @Secured(Roles.ADMIN)
    public Response getAllProjects() throws SQLException, ClassNotFoundException {

        List<Project> listProj = projectsService.getProjects();
        return Response.ok(listProj).build();

        /*JsonObject jsonObject = Json.createObjectBuilder().add("message","display message").build();
        return Response.ok(jsonObject).build();*/
    }

    @POST
    @Secured
    public Response saveProject(Project project) throws SQLException, ClassNotFoundException {
        int result = projectsService.saveProject(project);
        if(result == 1){
            return Response.ok(project).build();
        }
        else {
            JsonObject jsonObject = Json.createObjectBuilder().add("Error","Project addition failed").build();
            return Response.ok(jsonObject).build();
        }
    }

    @GET
    @Path("/{name}")
    public Response getOneProject(@PathParam("name")String name) throws SQLException, ClassNotFoundException {
        Project project = projectsService.getOneProject(name);
        return  Response.ok(project).build();
    }

    @Path("/{name}")
    @PUT
    public Response updateProject(@PathParam("name")String name ,Project project) throws SQLException, ClassNotFoundException {
       int result = projectsService.updateProject(name,project);
        if(result == 1){
            return Response.ok(project).build();
        } else {
            JsonObject jsonObject = Json.createObjectBuilder().add("Error","Project updation failed").build();
            return Response.ok(jsonObject).build();
        }
       /* JsonObject jsonObject = Json.createObjectBuilder().add("message",name).build();
        return Response.ok(jsonObject).build();*/

    }
}
