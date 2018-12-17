<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>School Sessions</title>
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
        .button {
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            padding: 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
        }
        .button1 {border-radius: 2px;width: 120px;}
        .button2 {border-radius: 4px;width: 220px;}
        .button3 {border-radius: 8px;}
        .button4 {border-radius: 12px;}
        .button5 {border-radius: 50%;}
    </style>
    <style>
        #sessionlist {
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #sessionlist td, #customers th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #sessionlist tr:nth-child(even){background-color: #f2f2f2;}

        #sessionlist tr:hover {background-color: #ddd;}

        #sessionlist th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #4CAF50;
            color: white;
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
    <a class="active" href="<%=request.getContextPath()%>/SchoolSession">School Session List</a>
    <a href="<%=request.getContextPath()%>/Program">Programs</a>
    <a href="<%=request.getContextPath()%>/Client">Clients</a>
    <a href="<%=request.getContextPath()%>/Instructor">Instructors</a>
    <a href="<%=request.getContextPath()%>/ClassRoom">Class Rooms</a>
    <a href="<%=request.getContextPath()%>/About.jsp">About</a>
</div>
    <div align="center">
        <table border="1" cellpadding="5" id="sessionlist">
            <caption><h2>List of Session</h2></caption>
            <tr>
                <th>Session ID</th>
                <th>Class room</th>
                <th>Session date</th>
                <th>Start time</th>
                <th>End time</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="schoolsession" items="${listSchoolSession}">
                <tr align="center">
                    <td><c:out value="${schoolsession.sessionid}"/></td>
                    <% String X;%>
                    <td>
                    <c:forEach items="${classroomlist}" var="classroom">
                        <c:if test="${classroom.roomid==schoolsession.roomid}">
                            Room: ${classroom.roomnumber} <br/>
                            at: ${classroom.address}
                        </c:if>
                    </c:forEach>
                    </td>
                    <td><fmt:formatDate value="${schoolsession.sessiondate}" pattern="yyyy-MM-dd" /></td>
                   <td><c:out value="${schoolsession.starttime}" /></td>
                   <td><c:out value="${schoolsession.endtime}" /></td>
                    <td><c:out value="${schoolsession.status}" /></td>
                    <td>
                        <a href="<%=request.getContextPath()%>/SchoolSession/edit?sessionid=<c:out value='${schoolsession.sessionid}' />">Update</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="delete?id=<c:out value='${schoolsession.sessionid}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <%--<button id="submit1" type="submit" class="button button1" formaction="<%=request.getContextPath()%>/SchoolSession/new"/>Save</button>--%>
        <%--<button class="button button1" formaction="<%=request.getContextPath()%>/SchoolSession/new">Add New Session</button>--%>
        <form method="post">
        <%--<a href="<%=request.getContextPath()%>/SchoolSession/new">Add New Session</a>--%>
            <input type="submit" class="button button2" value="New Session" formaction="<%=request.getContextPath()%>/SchoolSession/new"/>
        </form >
    </div>

        <%--&nbsp;&nbsp;&nbsp;--%>
        <%--<a href="list">List All Session</a>--%>
</body>
</html>