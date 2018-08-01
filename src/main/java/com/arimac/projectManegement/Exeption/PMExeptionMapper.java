package com.arimac.projectManegement.Exeption;

import com.arimac.projectManegement.model.ErrorMessageModel;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class PMExeptionMapper implements ExceptionMapper<PMExpecption> {
    @Override
    public Response toResponse(PMExpecption e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessageModel(e.getMessage(), e.getStackTrace()[0].getClassName(), e.getStackTrace()[0].getLineNumber())).build();
    }
}