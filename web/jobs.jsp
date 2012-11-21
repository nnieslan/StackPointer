<%-- 
    Document   : Jobs
    Created on : Nov 9, 2012, 11:16:10 PM
    Author     : Joe
--%>

<%@page import="stackpointer.database.JobsDatabaseFacade"%>
<%@page import="org.apache.bcel.generic.AALOAD"%>
<%@page import="java.util.ArrayList"%>
<%@page import="stackpointer.googlemaps.GoogleMapsInterface"%>
<%@page import="stackpointer.jobs.JobPosting"%>
<%@page import="stackpointer.jobs.LinkedInInterface"%>
<%@page import="stackpointer.stackexchange.Question"%>
<%@page import="java.util.List"%>
<%@page import="stackpointer.jobs.JobPosting"%>
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
 
      <link href="styles/menu.css" type="text/css" rel="stylesheet" />
    </head>
    <body>
        <% System.setProperty("java.awt.headless", "false");%>
        <% //set up data here!
        ArrayList<JobPosting> jobs = LinkedInInterface.getJobPostings();
        %>
        <span id="welcome">
            <center>
            <img src ="images/banner.jpg" width="800" />
            <script type='text/javascript'>
              $(function() {
                <%=GoogleMapsInterface.setupMap("map_canvas")%>
                <%=GoogleMapsInterface.generateJobMarkers(jobs)%>
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
            <h2><i>Geographic representation of the latest Job Postings</i></h2>
            <div id="map_canvas" style="width:800px; height:600px"></div>
            <h3>These are recent jobs posted on <a href="http://linkedin.com">LinkedIn</a>
                shown on <a href="http://maps.google.com">Google Maps</a>.</h3><br>
            </center>
                <%  
                    for(JobPosting j : jobs)
                    {
                        out.println("<br>*************<br>");
                        out.println(j);
                    }
                %>
        </span>
        <center>
            <span id="jobs">
                <%
                    JobsDatabaseFacade databaseFacade = new JobsDatabaseFacade();
                    List<JobPosting> jobsList = databaseFacade.retrieveAllJobPostings();
                    int idx = 1;
                    for (JobPosting job : jobsList) {
                        out.println(String.format("%d.  %s <br>", idx, job.getHeadline()));
                        idx++;
                    }
                %>
            </span>
        </center>
    </body>
</html>
