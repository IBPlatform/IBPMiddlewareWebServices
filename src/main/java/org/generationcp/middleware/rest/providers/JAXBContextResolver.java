package org.generationcp.middleware.rest.providers;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;

import org.generationcp.middleware.pojos.Bibref;
import org.generationcp.middleware.pojos.Germplasm;
import org.generationcp.middleware.pojos.Location;
import org.generationcp.middleware.pojos.Method;
import org.generationcp.middleware.pojos.UserDefinedField;

import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JAXBContextResolver implements ContextResolver<JAXBContext> {

	private JAXBContext context;
	private Class[] types = {
				Bibref.class,
				Location.class,
				Method.class,
				UserDefinedField.class,
				Germplasm.class
			};
	
	public JAXBContextResolver() throws Exception {
        JSONConfiguration.MappedBuilder b = JSONConfiguration.mapped();
        //disable dropping of root element to JSON output
        b.rootUnwrapping(false);
        context = new JSONJAXBContext(b.build(), types);
	}
	
	public JAXBContext getContext(Class<?> objectType) {
		for (Class type : types) {
			if (type == objectType) {
				return context;
			}
		}
		return null;
	}
	
}
