package com.acme.search;

import java.sql.Connection;

public interface FederalConnectionLoader {
    Connection getConnection();
}
