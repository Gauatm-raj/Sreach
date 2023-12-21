package com.gautam;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/History")
public class History extends HttpServlet {
    protected void doGet(HttpServletRequest request , HttpServletResponse response){
        Connection con = DataBaseConnect.getConnection();
        try {
              ResultSet res =con.createStatement().executeQuery("select * from history");
              ArrayList<HistoryResult> result=new ArrayList<>();
              while(res.next()) {
                  HistoryResult hr = new HistoryResult();
                  hr.setKeyword(res.getString("Keyword"));
                  hr.setLink(res.getString("Link"));
                  result.add(hr);
              }
                  request.setAttribute("result" ,result);
                  request.getRequestDispatcher("history.jsp").forward(request,response);
                  response.setContentType("text/html");
                  PrintWriter out = response.getWriter();

        } catch (SQLException e) {
            e.printStackTrace();
        }catch(ServletException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    }

