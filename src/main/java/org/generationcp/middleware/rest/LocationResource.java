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

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.generationcp.middleware.manager.GermplasmDataManagerImpl;
import org.generationcp.middleware.pojos.Location;
import org.generationcp.middleware.util.HibernateUtil;

public class LocationResource{

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    String locationId;
    HibernateUtil hibUtil;

    public LocationResource(UriInfo uriInfo, Request request, String locationId, HibernateUtil hibUtil) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.locationId = locationId;
        this.hibUtil = hibUtil;
    }

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Location getLocation() {
        GermplasmDataManagerImpl germDataManager = new GermplasmDataManagerImpl(hibUtil.getCurrentSession());
        Location location = germDataManager.getLocationByID(new Integer(locationId));
        hibUtil.closeCurrentSession();
        return location;
    }

}
