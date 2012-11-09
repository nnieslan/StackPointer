<%-- 
    Document   : index
    Created on : Sep 2, 2012, 3:36:10 PM
    Modified on: Sep 24, 2012, 12:55:10AM : Joe
    Author     : Phil
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="stackpointer.googlemaps.GoogleMapsInterface"%>
<%@page import="stackpointer.stackexchange.Question"%>
<%@page import="stackpointer.stackexchange.StackExchangeInterface"%>
<%@page import="java.util.List"%>
<%@page import="stackpointer.stackexchange.Question"%>
<%@page import="stackpointer.database.DatabaseConnectionInfo"%>
<%@page import="stackpointer.database.MySQLDatabaseFacade"%>
<%@page import="stackpointer.database.DatabaseFacade"%>
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
        <% //set up data here!
        ArrayList<Question> questions = StackExchangeInterface.getQuestionsFromServer();
        %>
        <span id="welcome">
            <center>
            <img src ="images/banner.jpg" width="800" />
            <script type='text/javascript'>
              $(function() {
                <%=GoogleMapsInterface.setupMap("map_canvas")%>
                <%=GoogleMapsInterface.generateMarkers(questions)%>
              });
            </script>
            </center>
        </span>
        <div class="menu_content">
            <div class="button">
                <a href="index.jsp">Home</a>
            </div>
            <div class="button">
                <a href="login.jsp">Login</a>
            </div>
            <div class="button">
                <a href="jobs.jsp">Job Opportunities</a>
            </div>
            <div class="button">
                <a href="stackexchange.jsp">Stack Exchange</a>
            </div>
            <div class="button">
                <a href="userinfo.jsp">User Information</a>
            </div>
        </div>          
        <span id="map">
            <br />
            <center>
            <h2><i>Geographic representation of the last 100 StackOverflow Questions</i></h2>
            <div id="map_canvas" style="width:800px; height:600px"></div>
            <h3>These are the last 100 questions asked on <a href="http://stackoverflow.com">StackOverflow</a>
                shown on <a href="http://maps.google.com">Google Maps</a>.</h3><br>
            </center>
                <%  
                    //for(Question q : questions)
                    //{
                    //    out.println("<br>*************<br>");
                    //    out.println(q);
                    //}
                %>
        </span>
        <center>
            <span id="questions">
                <%
                    DatabaseConnectionInfo connectionInfo = DatabaseConnectionInfo.createDefault();
                    System.out.println(connectionInfo);
                    DatabaseFacade databaseFacade = new MySQLDatabaseFacade(connectionInfo);
                    List<Question> questionList = databaseFacade.retrieveQuestions();
                    int idx = 1;
                    for (Question question : questionList) {
                        out.println(String.format("%d.  %s <br>", idx, question.getqText()));
                        idx++;
                    }
                %>
            </span>
        </center>
    </body>
</html>