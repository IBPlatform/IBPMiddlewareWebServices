/***************************************************************
 * Copyright (c) 2012, All Rights Reserved.
 * 
 * Generation Challenge Programme (GCP)
 * 
 * 
 * This software is licensed for use under the terms of the 
 * GNU General Public License (http://bit.ly/8Ztv8M) and the 
 * provisions of Part F of the Generation Challenge Programme 
 * Amended Consortium Agreement (http://bit.ly/KQX1nL)
 * 
 **************************************************************/
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
