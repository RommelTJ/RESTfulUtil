package edu.sandiego.restfulUtil.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {

	@Override
	public Response toResponse(NotFoundException e){
		return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
	}

}
