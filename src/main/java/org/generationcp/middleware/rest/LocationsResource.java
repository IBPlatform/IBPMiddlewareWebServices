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

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.generationcp.middleware.exceptions.ConfigException;
import org.generationcp.middleware.util.HibernateUtil;
import org.hibernate.HibernateException;

@Path("/location")
public class LocationsResource{

    private static final String CONFIG_FILE = "ibpmidwareweb_hib.cfg.xml";

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    HibernateUtil hibUtil;

    public LocationsResource() throws HibernateException, ConfigException {
        this.hibUtil = new HibernateUtil(CONFIG_FILE);
    }

    @Path("{locationId}")
    public LocationResource getLocation(@PathParam("locationId") String locationId) {
        return new LocationResource(uriInfo, request, locationId, hibUtil);
    }
}
