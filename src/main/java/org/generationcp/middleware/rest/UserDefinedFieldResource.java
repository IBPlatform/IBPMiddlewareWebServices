package org.generationcp.middleware.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.generationcp.middleware.manager.GermplasmDataManagerImpl;
import org.generationcp.middleware.pojos.UserDefinedField;
import org.generationcp.middleware.util.HibernateUtil;

public class UserDefinedFieldResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	String udfieldId;
	HibernateUtil hibUtil;
	
	public UserDefinedFieldResource(UriInfo uriInfo, Request request, String udfieldId, HibernateUtil hibUtil) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.udfieldId = udfieldId;
		this.hibUtil = hibUtil;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public UserDefinedField getUserDefinedField() {
		GermplasmDataManagerImpl germDataManager = new GermplasmDataManagerImpl(hibUtil.getCurrentSession());
		UserDefinedField udfield = germDataManager.getUserDefinedFieldByID(new Integer(udfieldId));
		hibUtil.closeCurrentSession();
		return udfield;
	}

}
