package org.generationcp.middleware.rest;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.generationcp.middleware.exceptions.ConfigException;
import org.generationcp.middleware.util.HibernateUtil;
import org.hibernate.HibernateException;

@Path("/germplasm")
public class GermplasmsResource {
	
	private static final String CONFIG_FILE = "ibpmidwareweb_hib.cfg.xml";
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	HibernateUtil hibUtil;
	
	public GermplasmsResource() throws HibernateException, ConfigException {
		this.hibUtil = new HibernateUtil(CONFIG_FILE);
	}
	
	@Path("{GID}")
	public GermplasmResource getGermplasm(
			@PathParam("GID") String GID){
		return new GermplasmResource(uriInfo, request, GID, hibUtil);
	}
}
