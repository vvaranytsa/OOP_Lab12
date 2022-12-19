package org.example.decorator;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.*;
import java.util.Collections;


@AllArgsConstructor
public class CachedDocument implements Document {
    String gcsPath;
    @Override
    public String parse() throws SQLException, IOException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:cache.sqlite");
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS documents (id INTEGER PRIMARY KEY, gcsPath TEXT, text TEXT)");

        PreparedStatement pstmt = conn.prepareStatement("SELECT text FROM documents WHERE gcsPath = ?");
        pstmt.setString(1, gcsPath);

        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            String text = rs.getString("text");
            conn.close();
            return text;
        }

        String text = new SmartDocument(gcsPath).parse();
        pstmt = conn.prepareStatement("INSERT INTO documents (gcsPath, text) VALUES (?, ?)");
        pstmt.setString(1, gcsPath);
        pstmt.setString(2, text);
        pstmt.executeUpdate();

        conn.close();
        return text;
    }
}
