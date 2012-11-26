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
 
        <script type="text/javascript">
            function toggle(elementID)
            {
                var element = document.getElementById(elementID);
                element.className=(element.className=='hidden')?'unhidden':'hidden';
            } 
        </script>
      <link href="styles/menu.css" type="text/css" rel="stylesheet" />
      <link href="styles/jobButtons.css" type="text/css" rel="stylesheet" />
    </head>
    <body style="text-align: center">
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
                <a href="stackexchange.jsp">Stack Exchange</a>
            </div>
            <div class="currentbutton">
                <a href="jobs.jsp">Job Opportunities</a>
            </div>
            <div class="button">
                <a href="login.jsp">Login</a>
            </div>
        </div>           
        <div id="map">
            <br />
            <center>
                <h3><i>Geographic representation of the latest <a href="http://linkedin.com">LinkedIn</a> Job Postingsshown on <a href="http://maps.google.com">Google Maps</a>.</i></h3>
                <div id="map_canvas" style="width:800px; height:600px"></div>
            </center>
        </div>
        <div class="jobButtons" style="width:800px; margin: auto;">
            <br />
            <% LinkedInInterface newFacade = new LinkedInInterface(); %>
            <% ArrayList<JobPosting> jobsnewList = newFacade.getJobPostings(); %>
            <% int odx = 1; %>
            <% for(JobPosting job : jobsnewList) { %>
                <div class="button">
                    <a href ="http://www.linkedin.com/jobs?viewJob=&jobId=<%out.println(job.getLinkedInId());%>"><b><%out.println(job.getHeadline());%> - <%out.println(job.getCompany());%></b><br />
                    <% if (job.hasLocation()) { out.println(job.getLoc()); %> <br /> <% } %>
                    <% out.println(job.getDatePosted());%><br /></a>
                    <% odx++; %>
                </div>
            <% } %>
         </div>
         <div class="jobButtons" style="width:800px; margin: auto;">
            <br />
            <% JobsDatabaseFacade databaseFacade = new JobsDatabaseFacade(); %>
            <% List<JobPosting> jobsList = databaseFacade.retrieveAllJobPostings(); %>
            <% int idx = 1; %>
            <% for(JobPosting job : jobsList) { %>
                <div class="button">
                    <a id="linkedinButton<% out.print(idx); %>" href="javascript:toggle('linkedinText<% out.print(idx); %>');"><b><%out.println(job.getHeadline());%> - <%out.println(job.getCompany());%></b><br />
                    <% if (job.hasLocation()) { out.println(job.getLoc()); %> <br /> <% } %>
                    <% out.println(job.getDescription());%><br /></a>
                </div>
                <div id="linkedinText<% out.print(idx); %>" class = "hidden">
                    <p>
                        <center><a href ="http://www.linkedin.com/jobs?viewJob=&jobId=<%out.println(job.getLinkedInId());%>">http://www.linkedin.com/jobs?viewJob=&jobId=<%out.println(job.getLinkedInId());%></a></center>
                    </p>
                    <p>
                        <% out.println(String.format("%s <br>", job.getDescription())); %>
                    </p>
                </div>
                <% idx++; %>
            <% } %>
        </div>
    </body>
</html>
