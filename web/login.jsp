<%-- 
    Document   : index
    Created on : Sep 2, 2012, 3:36:10 PM
    Modified on: Sep 24, 2012, 12:55:10AM : Joe
    Author     : Phil
--%>

<%@page import="stackpointer.jobs.LinkedInInterface"%>
<%@page import="java.util.ArrayList"%>
<%@page import="stackpointer.googlemaps.GoogleMapsInterface"%>
<%@page import="stackpointer.stackexchange.Question"%>
<%@page import="stackpointer.stackexchange.StackExchangeInterface"%>
<%@page import="java.util.List"%>
<%@page import="stackpointer.stackexchange.Question"%>
<%@page import="stackpointer.database.DatabaseConnectionInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>StackPointer</title>
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
        <style type="text/css">
          #map_canvas { height: 100% }
        </style>
        <script type="text/javascript"
          src="<%=GoogleMapsInterface.headerJavascript()%>">
        </script>
        <script type='text/javascript' src='/StackPointer/script/jquery/jquery-1.8.1.js'></script>
        <script type="text/javascript" src="http://platform.linkedin.com/in.js">api_key:v6xty7mvo61a</script>
 
<script type="text/javascript">
 
function loadData() {
  IN.API.PeopleSearch()
         .fields("firstName", "lastName", "distance", "publicProfileUrl","pictureUrl")
         .params({"keywords": "python", "count": 10, "sort": "distance"})
         .result(function(result) {
            profHTML = "<h4>People search results for keyword 'python':</h4>";
            for (var index in result.people.values) {
                profile = result.people.values[index]
                if (profile.pictureUrl) {
                    profHTML += "<p><a href=\"" + profile.publicProfileUrl + "\">";
                    profHTML += "<img class=img_border height=30 align=\"left\" src=\"" + profile.pictureUrl + "\"></a>";
                    profHTML += "<p>" + profile.firstName + " " + profile.lastName + " (" + profile.distance + ")</p>"; 
                }
            }  
      $("#search").html(profHTML);
      });
}
 
</script>
      <link href="styles/menu.css" type="text/css" rel="stylesheet" />
    </head>
    <body>
        <% System.setProperty("java.awt.headless", "false");%>
        <% //set up data here!
        if(request.getParameter("username")!=null && request.getParameter("password")!=null)
        {
            LinkedInInterface.setLoginCredentials(request.getParameter("username"), request.getParameter("password"));
        }
        else if(request.getParameter("logout")!=null && request.getParameter("logout").equals("true"))
        {
            LinkedInInterface.setLoginCredentials(null, null);
        }
        
        String queryStr = request.getParameter("q");
        if(queryStr!=null)
        {
            queryStr = queryStr.replaceAll("-", " ");
        }

        ArrayList<Question> questions = StackExchangeInterface.getQuestionsFromServer();
        %>
        <span id="welcome">
            <center>
            <img src ="images/banner.jpg" width="800" />
            </center>
        </span>
        <div class="menu_content">
            <div class="button">
                <a href="index.jsp">Home</a>
            </div>
            <div class="button">
                <a href="stackexchange.jsp">Stack Exchange</a>
            </div>
            <div class="button">
                <a href="jobs.jsp">Job Opportunities</a>
            </div>
            <div class="currentbutton">
                <% if (LinkedInInterface.hasCredentials()) { %>
                  <a href="login.jsp?logout=true">Log Out</a>
                <% } else { %>
                  <a href="login.jsp?logout=false">Log In</a>
                <% } %>
            </div>
        </div>   
        <span id="login">
            <br />
            <center>
                <%if(!LinkedInInterface.hasCredentials()) {%>
                    <br /><br />
                    Please enter your LinkedIn credentials:<br />
                    <form action="jobs.jsp" method="POST">
                        <input type="hidden" name="q" value="<%=queryStr%>"/>
                        Username: <input type="text" name="username"><br />
                        Password: <input type="password" name="password" /><br />
                        <input type="submit" value="Submit" />
                    </form>
                    <% } else { %>
                    <br /><br />
                    <strong><% out.println(request.getParameter("username")); %></strong> successfully logged In!!
                <% } %>
            </center>
        </span>
    </body>
</html>
