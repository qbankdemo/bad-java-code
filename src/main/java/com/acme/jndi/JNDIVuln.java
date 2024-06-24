package com.acme.jndi;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Path("/unsafe-jndi-lookup")
public class JNDIVuln {

    @GET
    public String lookupResource(@QueryParam("resource") final String resource) throws NamingException {
        Context ctx = new InitialContext();
        Object obj = ctx.lookup(resource);
        return String.valueOf(obj);
    }
}
