<%@ page import="java.util.ArrayList"%>
<%@ page import="com.gautam.SearchResult"%>
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
  </head>
<body>
  <h1> Search Engine</h1>
    <form action="Search">
      <input type="text" name ="Keyword"></input>
      <Button type ="submit">Search</Button>
    </form>

    <form action="History">
      <Button type ="submit">History</Button>
    </form>
<table border=2 class="res">

    <tr>
       <th>Title</th>
       <th>Link</th>
    </tr>
           <%
              ArrayList<SearchResult> results=(ArrayList<SearchResult>)request.getAttribute("res");
              for(SearchResult result:results){

           %>
           <tr>
               <td><%out.println(result.getText());%></td>
               <td><a href="<%out.println(result.getLinks());%>"><%out.println(result.getLinks());%></a></td>
           </tr>
             <%
               }
              %>

      </table>
   </body>
</html>