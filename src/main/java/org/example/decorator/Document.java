package org.example.decorator;

import java.io.IOException;
import java.sql.SQLException;

public interface Document {
    String parse() throws SQLException, IOException;
}
