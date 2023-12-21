package com.gautam;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/Search")
public class Search extends HttpServlet {
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException {
        String Keyword=request.getParameter("Keyword");
        Connection con=DataBaseConnect.getConnection();
        try {
            PreparedStatement pre=con.prepareStatement("insert into history values(?,?)");
            pre.setString(1,Keyword);
            pre.setString(2,"https://localhost:8080/SearchEngine/Search?Keyword="+Keyword);
            pre.executeUpdate();
            ResultSet result = con.createStatement().executeQuery("select Title,Link, (length(lower(Text))-length(replace(lower(Text),'" + Keyword.toLowerCase() + "','')))/length('" + Keyword.toLowerCase() + "') as count from page order by count desc limit 30");
            ArrayList<SearchResult> res = new ArrayList<>();
            while (result.next()) {
                SearchResult sr = new SearchResult();
                sr.setText(result.getString("Title"));
                sr.setLinks(result.getString("Link"));
                res.add(sr);
            }
            for(SearchResult ele : res){
                System.out.println(ele.getText()+"\n"+ele.getLinks()+"\n");
            }
            request.setAttribute("res" ,res);
            request.getRequestDispatcher("search.jsp").forward(request,response);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            //out.println("<h3>Entered keyword id "+Keyword+"</h3>");
        }catch(SQLException s){
            s.printStackTrace();

        }catch(ServletException e){
            e.printStackTrace();
        }
    }
}
