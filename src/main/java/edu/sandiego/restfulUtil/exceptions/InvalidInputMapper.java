package edu.sandiego.restfulUtil.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidInputMapper implements ExceptionMapper<InvalidInputException> {

	@Override
	public Response toResponse(InvalidInputException e){
		return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).type("text/plain").build();
	}
}
