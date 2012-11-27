<%-- 
    Document   : index
    Created on : Sep 2, 2012, 3:36:10 PM
    Modified on: Sep 24, 2012, 12:55:10AM : Joe
    Author     : Phil
--%>

<%@page import="stackpointer.database.SXDatabaseFacade"%>
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

function toggle(elementID) {
    var element = document.getElementById(elementID);

    element.className=(element.className=='hidden')?'unhidden':'hidden';
} 


</script>
      <link href="styles/menu.css" type="text/css" rel="stylesheet" />
      <link href="styles/jobButtons.css" type="text/css" rel="stylesheet" />
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
            <div class="currentbutton">
                <a href="stackexchange.jsp">Stack Exchange</a>
            </div>
            <div class="button">
                <a href="jobs.jsp">Job Opportunities</a>
            </div>
            <div class="button">
                <a href="login.jsp">Login</a>
            </div>
        </div>            
        <span id="map">
            <br />
            <center>
            <h3><i>Geographic representation of the last 100 <a href="http://stackoverflow.com">StackOverflow</a> Questions shown on <a href="http://maps.google.com">Google Maps</a>.</i></h3>
            <div id="map_canvas" style="width:800px; height:600px"></div>
            <br>
            </center>
        </span>
        <div class="jobButtons" style="width:800px; margin: auto;">
            <center><h2>The following is the list using the API calls (matches the map)</h2></center>
            <% StackExchangeInterface sxInterface = new StackExchangeInterface(); %>
            <% List<Question> newquestionList = sxInterface.getQuestionsFromServer(); %>
            <% int idx = 1; %>
            <% for (Question question : newquestionList) { %>
                <div class="<% if (question.isAnswered()) { %>answeredbutton<%}else{%>unansweredbutton<%}%>">
                    <a id="questionButton<% out.print(idx); %>" href="javascript:toggle('questionText<% out.print(idx); %>');"><b><%out.println(question.getqTitle());%></b><br />
                    <% if (question.hasLocation()) { out.println(question.getAskedBy().getLoc()); %> <br /> <% } %>
                    <b><% if (question.isAnswered()) { %> <font color="green">Answered</font> <% } else { %> <font color="red">Not Answered</font> <% } %></b> <br />
                    Asked By: <% out.println(question.getAskedBy().getSXname()); %><br /></a>
                    
                </div>
                <div id="questionText<% out.print(idx); %>" class="hidden" style = "width: 800px; align: left;;">
                    <p>
                        <center><a href="<% out.println(question.getUrl()); %>"><% out.println(question.getUrl()); %></a></center>
                    </p>
                    <p>
                        <% out.println(String.format("%s <br>", question.getqText())); %>
                    </p>
                </div>
                <% idx++; %>
            <% } %>
            <br />
            <br />
        </div>
        <div class="jobButtons" style="width:800px; margin: auto;">
            <center><h2>The following is the list using the Facade calls (does not match the map)</h2></center>
            <% SXDatabaseFacade sxDatabaseFacade = new SXDatabaseFacade(); %>
            <% List<Question> questionList = sxDatabaseFacade.retrieveTop100Questions(); %>
            <% for (Question question : questionList) { %>
                <div class="<% if (question.isAnswered()) { %>answeredbutton<%}else{%>unansweredbutton<%}%>">
                    <a id="questionButton<% out.print(idx); %>" href="javascript:toggle('questionText<% out.print(idx); %>');"><b><%out.println(question.getqTitle());%></b><br />
                    <% if (question.hasLocation()) { out.println(question.getAskedBy().getLoc()); %> <br /> <% } %>
                    <b><% if (question.isAnswered()) { %> <font color="green">Answered</font> <% } else { %> <font color="red">Not Answered</font> <% } %></b> <br />
                    Asked By: <% out.println(question.getAskedBy().getSXname()); %><br /></a>
                    
                </div>
                <div id="questionText<% out.print(idx); %>" class="hidden" style = "width: 800px; align: left;;">
                    <p>
                        <center><a href="<% out.println(question.getUrl()); %>"><% out.println(question.getUrl()); %></a></center>
                    </p>
                    <p>
                        <% out.println(String.format("%s <br>", question.getqText())); %>
                    </p>
                </div>
                <% idx++; %>
            <% } %>
            <br />
            <br />
        </div>
    </body>
</html>
