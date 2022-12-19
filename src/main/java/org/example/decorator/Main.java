package org.example.decorator;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        System.out.println(new CachedDocument("gs://oop-lab12/oles.jpeg").parse());
        new TimedDocument("gs://oop-lab12/oles.jpeg").parse();
    }
}