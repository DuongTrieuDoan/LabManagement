<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import = "java.io.*,java.util.*" %>
<html>
<head>
    <title>Client Activities</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
            padding: 10px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 12px;
            margin: 4px 2px;
            cursor: pointer;
        }
        .button1 {border-radius: 2px;width: 120px;}
        .button2 {border-radius: 4px;}
        .button3 {border-radius: 8px;}
        .button4 {border-radius: 12px;}
        .button5 {border-radius: 50%;}
    </style>
    <style>
        #formtable {
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 50%;
        }

        #formtable td, #customers th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #formtable tr:nth-child(even){background-color: #f2f2f2;}

        #formtable tr:hover {background-color: #ddd;}

        #formtable th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #4CAF50;
            color: white;
        }
    </style>
    <style>
        #table1 {
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #table1 td, #customers th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #table1 tr:nth-child(even){background-color: #f2f2f2; }

        #table1 tr:hover {background-color: #ddd;}

        #table1 th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #4CAF50;
            color: white;
        }
    </style>
    <style>
        body {
            background-image: url(../images/background.jpg);
        }
    </style>
</head>
<body>
<div class="topnav">
    <a class="active"  href="<%=request.getContextPath()%>/SchoolSession">School Session List</a>
    <a href="<%=request.getContextPath()%>/Program">Programs</a>
    <a href="<%=request.getContextPath()%>/Client">Clients</a>
    <a href="<%=request.getContextPath()%>/Instructor">Instructors</a>
    <a href="<%=request.getContextPath()%>/ClassRoom">Class Rooms</a>
    <a href="<%=request.getContextPath()%>/About.jsp">About</a>
</div>
<center>
    <h2>Client Activities <br/>
    </h2>
</center>

<div align="center">
        <form method="post">
        <table border="1" cellpadding="5" id="formtable">
                <caption>
                    <h2>
                        Client ID: ${client.clientid}  Client Name:  ${client.firstname} ${client.lastname}
                        <input type="hidden" name="clientid" size="45"
                               value="<c:out value='${client.clientid}' />"
                        />
                    </h2>
                    <br/>
                    <br/>
                </caption>
                <c:if test="${clientactivity != null}">
                    <input type="hidden" name="activityid" value="<c:out value='${clientactivity.activityid}' />" />
                </c:if>
                <tr>
                    <th>Session ID</th>
                    <th>Session date</th>
                    <th>Time frame </th>
                    <th>Class room </th>
                </tr>
                <tr align="center">
                    <td>
                        <input type="hidden" name="sessionid" size="45"
                               value="<c:out value="${schoolsession.sessionid}"/>"
                        />
                        ${schoolsession.sessionid}
                    </td>
                    <td>
                        <input type="hidden" name="sessiondate" size="45"
                               value="<fmt:formatDate value="${schoolsession.sessiondate}" pattern="yyyy-MM-dd"/>"
                        />
                        <fmt:formatDate value="${schoolsession.sessiondate}" pattern="yyyy-MM-dd"/>
                    </td>
                    <td>
                        <input type="hidden" name="starttime" size="4"
                               value="<c:out value='${schoolsession.starttime}' />"
                        />
                        <input type="hidden" name="endtime" size="45"
                               value="<c:out value='${schoolsession.endtime}' />"
                        />
                        ${schoolsession.starttime} - ${schoolsession.endtime}
                    </td>
                    <td>
                        <input type="hidden" name="roomid" size="45"
                               value="<c:out value='${classroom.roomid}' />"
                        />
                        ${classroom.roomnumber} <br/> ${classroom.address}
                    </td>
                </tr>
                <tr>
                    <th>Activity ID </th>
                    <th>Course </th>
                    <th>Instructors </th>
                    <th>Notes </th>
                </tr>
                <tr>
                    <td>
                        <input type="hidden" name="activityid" size="45"
                               value="<c:out value='${clientactivity.activityid}' />"
                        />
                        ${clientactivity.activityid}
                    </td>
                    <td>
                        <select name="courseid">
                            <c:if test="${clientactivity == null}"><option value="" disabled selected>-- select course --</option> </c:if>
                                <c:forEach items="${courselist}" var="course">
                                    <c:if test="${course.courseid == clientactivity.courseid}">
                                        <option value="${course.courseid}" selected>${course.coursename} </option>
                                    </c:if>
                                    <c:if test="${course.courseid != clientactivity.courseid}">
                                        <option value="${course.courseid}">${course.coursename}</option>
                                    </c:if>
                                </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select name = "instructorid" multiple>
                            <c:if test="${clientactivity == null}"><
                                option value="" disabled selected>-- select instructor --</option>
                                <c:forEach items="${instructorlist}" var="instructor">
                                    <option value="${instructor.instructorid}"> ${instructor.firstname}, ${instructor.lastname}</option>
                                </c:forEach>
                            </c:if>

                            <c:if test="${clientactivity != null}">
                                <c:forEach items="${instructorlist}" var="instructor">
                                    <c:if test="${fn:indexOf(instructorstring,instructor.instructorid)>=0}">
                                        <option value="${instructor.instructorid}" selected> ${instructor.firstname}, ${instructor.lastname}</option>
                                    </c:if>
                                    <c:if test="${fn:indexOf(instructorstring,instructor.instructorid)==-1}">
                                        <option value="${instructor.instructorid}" > ${instructor.firstname}, ${instructor.lastname}</option>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </select>

                    <td>
                        <input type="text" name="notes" size="45"
                               value="<c:out value='${clientactivity.notes}' />"
                        />
                    </td>
                </tr>
        </table>
                <tr>
                    <td colspan="2" align="center"  colspan="12">
                        <c:if test="${clientactivity!=null}">
                            <input type="submit" class="button button1" value="Save" onclick="return Validate();" formaction="<%=request.getContextPath()%>/ClientActivity/update?clientid=<c:out value='${client.clientid}'/>&sessionid=<c:out value='${schoolsession.sessionid}'/>&activityid=<c:out value='${clientactivity.activityid}'/>"/>
                        </c:if>
                        <c:if test="${clientactivity==null}">
                            <input id="button1" class="button button1" type="submit" value="Add" onclick="return Validate();" formaction="<%=request.getContextPath()%>/ClientActivity/add?clientid=<c:out value='${client.clientid}'/>&sessionid=<c:out value='${schoolsession.sessionid}'/>"/>
                        </c:if>
                        <input id="button2" class="button button1" type="submit" value="Reset to add new" onclick="return Validate();" formaction="<%=request.getContextPath()%>/ClientActivity/show?clientid=<c:out value='${client.clientid}'/>&sessionid=<c:out value='${schoolsession.sessionid}'/>"/>
                    </td>
                </tr>

                <br><br>
        <div align="center">
                    <table border="1" cellpadding="5" id="table1">
                        <caption><h2>List of Clients' Activities</h2></caption>
                        <input type="hidden" name="clientlist" value="<c:out value='${clientlist}' />" />
                        <tr>
                            <th>Activity ID</th>
                            <th>Course</th>
                            <th>Instructors</th>
                            <th>Notes</th>
                            <th>Actions</th>
                        </tr>
                        <c:forEach var="clientact" items="${clientactitivitylist}">
                            <tr align="center">
                                <td>${clientact.activityid}</td>
                                <td><c:forEach items="${courselist}" var="mycourse">
                                    <c:if test="${mycourse.courseid == clientact.courseid}">
                                        ${mycourse.coursename}
                                    </c:if>
                                </c:forEach></td>
                                <td><c:forEach items="${clientact.instructorlist}" var="myinstructor">
                                    ${myinstructor.firstname}, ${myinstructor.lastname}<br/>
                                </c:forEach>
                                </td>
                                <td>${clientact.notes}</td>
                                <td>
                                    <a href="<%=request.getContextPath()%>/ClientActivity/edit?clientid=<c:out value='${clientact.clientid}'/>&sessionid=<c:out value='${clientact.sessionid}'/>&activityid=<c:out value='${clientact.activityid}'/>">Edit Activity</a>
                                    <a href="<%=request.getContextPath()%>/ClientActivity/delete?clientid=<c:out value='${clientact.clientid}'/>&sessionid=<c:out value='${clientact.sessionid}'/>&activityid=<c:out value='${clientact.activityid}' />">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
        </form>
</div>

</body>
</html>