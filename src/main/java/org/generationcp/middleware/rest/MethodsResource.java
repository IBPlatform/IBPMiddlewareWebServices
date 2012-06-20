/*******************************************************************************
 * Copyright (c) 2012, All Rights Reserved.
 * 
 * Generation Challenge Programme (GCP)
 * 
 * 
 * This software is licensed for use under the terms of the GNU General Public
 * License (http://bit.ly/8Ztv8M) and the provisions of Part F of the Generation
 * Challenge Programme Amended Consortium Agreement (http://bit.ly/KQX1nL)
 * 
 *******************************************************************************/

package org.generationcp.middleware.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.generationcp.middleware.exceptions.ConfigException;
import org.generationcp.middleware.exceptions.QueryException;
import org.generationcp.middleware.manager.GermplasmDataManagerImpl;
import org.generationcp.middleware.pojos.Method;
import org.generationcp.middleware.util.HibernateUtil;
import org.hibernate.HibernateException;

@Path("/methods")
public class MethodsResource{

    private static final String CONFIG_FILE = "ibpmidwareweb_hib.cfg.xml";

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    HibernateUtil hibUtil;

    public MethodsResource() throws HibernateException, ConfigException {
        this.hibUtil = new HibernateUtil(CONFIG_FILE);
    }

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public List<Method> getAllMethods() throws QueryException {
        GermplasmDataManagerImpl germDataManager = new GermplasmDataManagerImpl(hibUtil.getCurrentSession());
        List<Method> methods = germDataManager.getAllMethods();
        hibUtil.closeCurrentSession();
        return methods;
    }

    @Path("{methodId}")
    public MethodResource getMethod(@PathParam("methodId") String methodId) {
        return new MethodResource(uriInfo, request, methodId, hibUtil);
    }
}
