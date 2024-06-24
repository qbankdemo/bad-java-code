package com.acme.headerinjection;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

import javax.servlet.http.HttpServletResponse;

@Path("/unsafe-header-injection")
public class HeaderInjectionVuln {

    @GET
    public String lookupResource(HttpServletResponse response, @QueryParam("q") final String q) {
        response.setHeader("X-Last-Search", q);
        return "ok";
    }
}
