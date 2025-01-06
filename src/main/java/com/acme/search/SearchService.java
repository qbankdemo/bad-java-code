package com.acme.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.logging.Logger;

@Service
final class SearchService {

    @Autowired
    private FederalConnection fedConnection;

    String searchFederal(final String query) {
        log.info("Searching federal for query: " + '"' + query + '"');
        String fedQuery = "FEDSEARCH:" + query.toUpperCase().trim();

        // stop sqli that was reported by the security team
        if(fedQuery.contains("DROP") || fedQuery.contains("UNION") || fedQuery.contains("DELETE") || fedQuery.contains("1=1")) {
            throw new IllegalArgumentException("Commands detected");
        }
        try {
            return fedConnection.doSearch(fedQuery);
        } catch (SQLException e) {
            log.info("Error searching federal for query: " + '"' + query + '"');
            return "error";
        }
    }

    private static final Logger log = Logger.getAnonymousLogger();
}
