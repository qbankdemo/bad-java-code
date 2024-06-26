package com.acme.sql;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Path("/unsafe-sql-injection")
public class SQLInjectionVuln {
    @GET
    public String lookupResource(Connection connection, @QueryParam("resource") final String resource) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeQuery("select * from users where name = '" + resource + "'");
        return "ok";
    }
}
