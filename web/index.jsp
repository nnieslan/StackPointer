<%-- 
    Document   : index
    Created on : Sep 2, 2012, 3:36:10 PM
    Modified on: Sep 24, 2012, 12:55:10AM : Joe
    Author     : Phil
--%>

<%@page import="stackpointer.stackexchange.Question"%>
<%@page import="stackpointer.stackexchange.StackExchangeInterface"%>
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
          src="http://maps.googleapis.com/maps/api/js?key=AIzaSyA2DwdJgZeUssqn561w5tgt7b56oGcYf_o&sensor=false">
        </script>
        <script type="text/javascript">
          function initMap() {
            var mapOptions = {
              center: new google.maps.LatLng(39.739, -104.984),
              zoom: 8,
              mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            var map = new google.maps.Map(document.getElementById("map_canvas"),
                mapOptions);
          }
        </script>
        <script type='text/javascript' src='https://api.stackexchange.com/js/2.0/all.js'></script>
        <script type='text/javascript' src='/StackPointer/script/jquery/jquery-1.8.1.js'></script>
        <script type='text/javascript' src='/StackPointer/script/stackexchange/spStackExchange.js'></script>
        <script type="text/javascript" src="http://platform.linkedin.com/in.js">api_key:v6xty7mvo61a</script>
    </head>
    <body>
        <span id="welcome">
            <center>
            <h1>Welcome to StackPointer!</h1><br>
            <script type='text/javascript'>
              $(function() {
                initMap();
                stackInit();
              });
            </script>
        </span>
        <span id="map">
            <h2><i>Map</i></h2><br>
            <div id="map_canvas" style="width:800px; height:600px"></div>
            <span id="vers">api vers</span>
            <h3>These are the last 100 questions asked on <a href="http://stackoverflow.com">StackOverflow</a>
                shown on <a href="http://maps.google.com">Google Maps</a>.</h3><br>
            </center>
                <%  
                    for(Question q : StackExchangeInterface.getQuestionsFromServer())
                    {
                        out.println("<br>*************<br>");
                        out.println(q);
                    }
                %>
        </span>
        <center>
            <span id="jobs">
                <h2><i>Jobs</i></h2>
                <h3>These are job listings in your area related to these questions, powered by <a href="http://linkedin.com">LinkedIn</a></h3>
            </span>
            <script type="in/Login">Hello, <?js= firstName ?> <?js= lastName ?>.</script>
        </center>
    </body>
</html>
