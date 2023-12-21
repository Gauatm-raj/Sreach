package org.example;

import org.jsoup.nodes.Document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Indexer {
    static Connection connection=null;
    Indexer(Document doc,String url){
        String title=doc.title();
        String link=url;
        String text=doc.text();

        try {
            connection=DataBaseConnect.getConnection();
            PreparedStatement pre = connection.prepareStatement("insert into page values(?,?,?)");
            pre.setString(1,title);
            pre.setString(2,link);
            pre.setString(3,text);
            pre.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
