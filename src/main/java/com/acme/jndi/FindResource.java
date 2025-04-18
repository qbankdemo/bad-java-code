package com.acme.jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/** JNDI resource finder. */
public final class FindResource {

    private FindResource() { }

    public static String findResource(final String resource) throws NamingException {
        return lookupResource(resource);
    }

    private static String lookupResource(final String resource) throws NamingException {
        Context ctx = new InitialContext();
        return String.valueOf(ctx.lookup(resource));
    }
}
