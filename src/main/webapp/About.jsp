<%--
  Created by IntelliJ IDEA.
  User: tranthituongvi
  Date: 2018-12-01
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>About</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
        }

        .topnav {
            overflow: hidden;
            background-color: #333;
        }

        .topnav a {
            float: left;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }

        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }

        .topnav a.active {
            background-color: #4CAF50;
            color: white;
        }
    </style>
    <style>
        .collapsible {
            background-color: #777;
            color: white;
            cursor: pointer;
            padding: 18px;
            width: 100%;
            border: none;
            text-align: left;
            outline: none;
            font-size: 15px;
        }

        .active, .collapsible:hover {
            background-color: #555;
        }

        .content {
            padding: 0 18px;
            display: none;
            overflow: hidden;
            background-color: #f1f1f1;
        }
    </style>
    <style>
        body {
            background-image: url(images/background.jpg);
        }
    </style>
</head>
<body>
<div class="topnav">
    <a href="<%=request.getContextPath()%>/SchoolSession">School Session List</a>
    <a href="<%=request.getContextPath()%>/Program">Programs</a>
    <a href="<%=request.getContextPath()%>/Client">Clients</a>
    <a href="<%=request.getContextPath()%>/Instructor">Instructors</a>
    <a href="<%=request.getContextPath()%>/ClassRoom">Class Rooms</a>
    <a class="active" href="<%=request.getContextPath()%>/About.jsp">About</a>
</div>
<center><h1>About</h1></center>
<%--<font face="arial" size="12">--%>
The application is aimed to present the knowledge the student gained in the COMIT Java course. <br/><br/>
Beside that, the application is the very first step for an accomplishment system to manage the clients, facilities <br/>
and other resources of the Immigrant Centre Manitoba, where the student is volunteering to teach its clients basic computer knowledge.. <br/><br/>
The student tried to apply the knowledge gained during the classes in the application which are: <br/><br/>
    <li>Structuring apps in different parts based on functionality, such as domain, repository, service, and view</li>
    <li>JDBC</li>
    <li>Java class structure: supper class, interface, and base class</li>
    <li>Different types of variables, such as string (with String and StringBuffer), numbering (Integer, Double, Float), Datetime, and enum </li>
    <li>Collection types: ArrayList, List, LinkedList and Map</li>
    <li>Lambda</li>
    <li>Exception handling</li>
    <li>Servlet</li>
    <li>HTML, CSS, Javascript</li><br/>
The student also tried to add some knowledge from the student's research from the internet to the application<br/><br/>
It is a web based application which is designed to be scalable, upgradeable and integrateable with other systems in the future.
</font>
</body>
</html>
