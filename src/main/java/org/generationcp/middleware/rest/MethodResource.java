package org.generationcp.middleware.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.generationcp.middleware.manager.GermplasmDataManagerImpl;
import org.generationcp.middleware.pojos.Method;
import org.generationcp.middleware.util.HibernateUtil;

public class MethodResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	String methodId;
	HibernateUtil hibUtil;
	
	public MethodResource(UriInfo uriInfo, Request request, String methodId, HibernateUtil hibUtil) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.methodId = methodId;
		this.hibUtil = hibUtil;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Method getMethod() {
		GermplasmDataManagerImpl germDataManager = new GermplasmDataManagerImpl(hibUtil.getCurrentSession());
		Method method = germDataManager.getMethodByID(new Integer(methodId));
		hibUtil.closeCurrentSession();
		return method;
	}

}
