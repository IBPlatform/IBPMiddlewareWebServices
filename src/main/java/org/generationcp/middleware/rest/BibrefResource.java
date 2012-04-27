package org.generationcp.middleware.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.generationcp.middleware.manager.GermplasmDataManagerImpl;
import org.generationcp.middleware.pojos.Bibref;
import org.generationcp.middleware.util.HibernateUtil;

public class BibrefResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	String refId;
	HibernateUtil hibUtil;
	
	public BibrefResource(UriInfo uriInfo, Request request, String refId, HibernateUtil hibUtil) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.refId = refId;
		this.hibUtil = hibUtil;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Bibref getBibref() {
		GermplasmDataManagerImpl germDataManager = new GermplasmDataManagerImpl(hibUtil.getCurrentSession());
		Bibref bibref = germDataManager.getBibliographicReferenceByID(new Integer(refId));
		hibUtil.closeCurrentSession();
		return bibref;
	}

}
