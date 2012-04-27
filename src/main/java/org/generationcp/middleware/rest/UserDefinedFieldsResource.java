package org.generationcp.middleware.rest;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.generationcp.middleware.exceptions.ConfigException;
import org.generationcp.middleware.util.HibernateUtil;
import org.hibernate.HibernateException;

@Path("/udfield")
public class UserDefinedFieldsResource {
	
	private static final String CONFIG_FILE = "ibpmidwareweb_hib.cfg.xml";
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	HibernateUtil hibUtil;
	
	public UserDefinedFieldsResource() throws HibernateException, ConfigException {
		this.hibUtil = new HibernateUtil(CONFIG_FILE);
	}
	
	@Path("{udfieldId}")
	public UserDefinedFieldResource getUserDefinedField(
			@PathParam("udfieldId") String udfieldId){
		return new UserDefinedFieldResource(uriInfo, request, udfieldId, hibUtil);
	}
}
