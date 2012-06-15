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
import org.generationcp.middleware.pojos.Germplasm;
import org.generationcp.middleware.util.HibernateUtil;

public class GermplasmResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	String GID;
	HibernateUtil hibUtil;
	
	public GermplasmResource(UriInfo uriInfo, Request request, String GID, HibernateUtil hibUtil) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.GID = GID;
		this.hibUtil = hibUtil;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Germplasm getGermplasm() {
		GermplasmDataManagerImpl germDataManager = new GermplasmDataManagerImpl(hibUtil.getCurrentSession());
		Germplasm germplasm = germDataManager.getGermplasmByGID(new Integer(GID));
		hibUtil.closeCurrentSession();
		return germplasm;
	}

}
