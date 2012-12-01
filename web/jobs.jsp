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
        ArrayList<JobPosting> jobs = LinkedInInterface.getJobPostings();
        String queryStr = request.getParameter("q");
        if(queryStr!=null)
        {
            queryStr = queryStr.replaceAll("-", " ");
        }
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
                <% if(!LinkedInInterface.hasCredentials()) {%>
                <form action="jobs.jsp" method="POST">
                    LinkedIn Username: <input type="text" name="username">
                    <br />
                    LinkedIn Password: <input type="password" name="password" />
                    <br />
                    <input type="submit" value="Submit" />
                </form>
                <% } 
                   else
                   {
                %>
                    <a href="jobs.jsp?logout=true">Log Out</a>
                    <h3><i>Geographic representation of the latest <a href="http://linkedin.com">LinkedIn</a> Job Postings <% if(queryStr!=null){out.print("for "+queryStr+' ');
                    } %>shown on <a href="http://maps.google.com">Google Maps</a>.</i></h3>
                    <div id="map_canvas" style="width:800px; height:600px"></div>
                </center>
            </div>
            <div class="jobButtons" style="width:800px; margin: auto;">
                 <center><h2>The following is the list of database jobs.</h2></center>
                <%
                int idx = 1;
                   List<JobPosting> jobsList;
                 JobsDatabaseFacade databaseFacade = new JobsDatabaseFacade();
                if (queryStr != null && !queryStr.isEmpty()) {
                    jobsList = databaseFacade.retrieveByKeyword(queryStr);
                } else {
                     jobsList = databaseFacade.retrieveAllJobPostings();
                }
                %>
                <% for(JobPosting job : jobsList) { %>
                    <div class="button">
                        <a id="linkedinButton<% out.print(idx); %>" href="javascript:toggle('linkedinText<% out.print(idx); %>');"><b><%out.println(job.getHeadline());%> - <%out.println(job.getCompany());%></b><br />
                        <% if (job.hasLocation()) { out.println(job.getLoc()); %> <br /> <% } %>
                        <% out.println(job.getDatePosted());%><br /></a>
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
            <div class="jobButtons" style="width:800px; margin: auto;">
                <center><h2>The following is the list using the API calls.</h2></center>
                <% //LinkedInInterface newFacade = new LinkedInInterface(); %>
                <% //ArrayList<JobPosting> jobsnewList = newFacade.getJobPostings(); %>
                <% for(JobPosting job : jobs) { %>
                    <div class="button">
                        <a id="linkedinButton<% out.print(idx); %>" href="javascript:toggle('linkedinText<% out.print(idx); %>');"><b><%out.println(job.getHeadline());%> - <%out.println(job.getCompany());%></b><br />
                        <% if (job.hasLocation()) { out.println(job.getLoc()); %> <br /> <% } %>
                        <% out.println(job.getDatePosted());%><br /></a>
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
            <%
            } //closing... else if(!LinkedInInterface.hasCredentials())
            %>
         </div>
    </body>
</html>
