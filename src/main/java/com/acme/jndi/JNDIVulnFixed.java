package com.acme.jndi;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Set;

@Path("/unsafe-jndi-lookup")
public class JNDIVulnFixed {

    @GET
    public String lookupResource(@QueryParam("resource") final String resource) throws NamingException {
        Context ctx = new InitialContext();
        validateResourceName(resource);
        Object obj = ctx.lookup(resource);
        return String.valueOf(obj);
    }

    private static void validateResourceName(final String name) {
        if (name != null) {
            Set<String> illegalNames = Set.of("ldap://", "rmi://", "dns://");
            String canonicalName = name.toLowerCase().trim();
            if (illegalNames.stream().anyMatch(canonicalName::startsWith)) {
                throw new SecurityException("Illegal JNDI resource name: " + name);
            }
        }
    }
}
