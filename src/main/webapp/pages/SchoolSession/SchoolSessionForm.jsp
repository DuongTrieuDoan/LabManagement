<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <style>
        body {
            background-image: url(../images/background.jpg);
        }
    </style>
    <title>School Session</title>
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
        #instructortable {
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #instructortable td, #customers th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #instructortable tr:nth-child(even){background-color: #f2f2f2;}

        #instructortable tr:hover {background-color: #ddd;}

        #instructortable th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #4CAF50;
            color: white;
        }
    </style>
    <style>
        #clienttable {
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #clienttable td, #customers th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #clienttable tr:nth-child(even){background-color: #f2f2f2;}

        #clienttable tr:hover {background-color: #ddd;}

        #clienttable th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #4CAF50;
            color: white;
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
        <a href="#about">About</a>
    </div>

    <div style="padding-left:16px">
        <h2>School Session</h2>
    </div>
    <div>
        <c:if test="${errormessage!=''}">
        <h5><font color="red">${errormessage}</font></h5>
        </c:if>
    </div>
<div align="center">
    <form method="post">
    <table border="1" cellpadding="5" id="formtable">
        <caption>
            <h2>
                <c:if test="${schoolsession != null}">
                    Edit Session
                </c:if>
                <c:if test="${schoolsession == null}">
                    Add New Session
                </c:if>
            </h2>
        </caption>
        <c:if test="${schoolsession != null}">
            <input type="hidden" name="id" value="<c:out value='${schoolsession.sessionid}' />" />
            <tr>
                <th>Session ID:</th>
                <td>${schoolsession.sessionid}</td>
            </tr>
        </c:if>
        <tr>
            <th>Date: </th>
            <td>
                <input type="date" name="sessiondate" size="45"
                       value="<fmt:formatDate value="${schoolsession.sessiondate}" pattern="yyyy-MM-dd" />"
                />
            </td>
        </tr>
        <tr>
            <th>Start at: </th>
            <td>
                <input type="time" name="starttime" size="4"
                       value="<c:out value='${schoolsession.starttime}' />"
                />
            </td>
        </tr>
        <tr>
            <th>End at: </th>
            <td>
                <input type="time" name="endtime" size="45"
                       value="<c:out value='${schoolsession.endtime}' />"
                />
            </td>
        </tr>
        <tr>
            <th>Status: </th>
            <td>
                <select name='status'>
                    <option value="${schoolsession.status}" selected>${schoolsession.status}</option>
                    <c:forEach items="${status}" var="status">
                        <c:if test="${status != schoolsession.status}">
                            <option value="${status}">${status}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th>Class room: </th>
            <td>
                <select name="roomid">
                    <%--<c:if test="${schoolsession == null}"><option value="" disabled selected>-- select room --</option> </c:if>--%>
                    <c:forEach items="${classroomlist}" var="classroom">
                        <c:if test="${schoolsession != null}">
                            <c:if test="${classroom.roomid == schoolsession.roomid}">
                                <option value="${classroom.roomid}" selected>Room ${classroom.roomnumber} at ${classroom.address}</option>
                            </c:if>
                            <c:if test="${classroom.roomid  != schoolsession.roomid}">
                                <option value="${classroom.roomid}">Room ${classroom.roomnumber} at ${classroom.address}</option>
                            </c:if>
                        </c:if>
                        <c:if test="${schoolsession == null}">
                            <option value="${classroom.roomid}">Room ${classroom.roomnumber} at ${classroom.address}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th>Remark: </th>
            <td>
                <input type="text" name="remark" size="45"
                       value="<c:out value='${schoolsession.remark}' />"
                />
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <c:if test="${schoolsession != null}">
                    <button id="submit1" class="button button1"  type="submit" value="Save" formaction="<%=request.getContextPath()%>/SchoolSession/update?sessionid=${schoolsession.sessionid}"/>Save</button>
                </c:if>
                <c:if test="${schoolsession == null}">
                    <button id="submit1" class="button button1" type="submit" value="Save" formaction="<%=request.getContextPath()%>/SchoolSession/insert"/>Save</button>
                </c:if>
                <%--<botton input type="submit" value="Save" />--%>
            </td>
        </tr>
    </table>
    <br><br>
    <div align="center">
        <caption><h2>List of Instructors</h2></caption>
        <table border="1" cellpadding="5" id="instructortable">
            <input type="hidden" name="instructorlist" value="<c:out value='${instructorlist}' />" />
            <tr>
                <th>Instructor ID</th>
                <th>First name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Address</th>
                <th>Start date</th>
                <th>Action</th>
            </tr>
            <c:forEach var="instructor" items="${listInstructor}">
                <tr align="center">
                    <td><c:out value="${instructor.instructorid}" /></td>
                    <td><c:out value="${instructor.firstname}" /></td>
                    <td><c:out value="${instructor.lastname}" /></td>
                    <td><c:out value="${instructor.emailaddress}" /></td>
                    <td><c:out value="${instructor.phone}" /></td>
                    <td><c:out value="${instructor.address}" /></td>
                    <td><c:out value="${instructor.startdate}" /></td>
                    <td>
                        <%--<a href="<%=request.getContextPath()%>/SchoolSession/deleteinstructor?id=<c:out value='${instructor.instructorid}'/>&session=<c:out value='${schoolsession.sessionid}'/>">Delete</a>--%>
                        <a href="<%=request.getContextPath()%>/SchoolSession/deleteinstructor?sessionid=<c:out value='${schoolsession.sessionid}'/>&instructorid=<c:out value='${instructor.instructorid}'/>">Delete</a>
                    </td>
                </tr>
            </c:forEach>

        </table>
        <c:if test="${schoolsession!=null}">
            <tr>
                <select name="instructorid">
                    <c:forEach var="instructor" items="${allinstructor}">
                        <c:set var="count" scope="session" value="no"></c:set>
                        <c:forEach var="sesinstructor" items="${listInstructor}">
                            <c:if test="${instructor.instructorid==sesinstructor.instructorid}">
                                <c:set var="count" scope="session" value="yes"></c:set>
                            </c:if>
                        </c:forEach>
                        <c:if test="${count=='yes'}">
                            <option value="${instructor.instructorid}" disabled>${instructor.firstname},${instructor.lastname}</option>
                        </c:if>
                        <c:if test="${count=='no'}">
                            <option value="${instructor.instructorid}">${instructor.firstname},${instructor.lastname}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <br/>
                    <button id="submit2" type="submit" class="button button1" value="Addinstructor" formaction="<%=request.getContextPath()%>/SchoolSession/addinstructor?sessionid=<c:out value='${schoolsession.sessionid}'/>" onclick="return ValidateData(this)">Add instructor</button>

                </td>
            </tr>
        </c:if>
        <br>
    </div>
    <br><br>
    <div align="center">
        <caption><h2>List of Clients</h2></caption>
        <table border="1" cellpadding="5" id="clienttable">
            <input type="hidden" name="clientlist" value="<c:out value='${clientlist}' />" />
            <tr>
                <th>Client ID</th>
                <th>First name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Address</th>
                <th>Start date</th>
                <th>Action</th>
            </tr>
            <c:forEach var="client" items="${listClient}">
                <tr align="center">
                    <td><c:out value="${client.clientid}" /></td>
                    <td><c:out value="${client.firstname}" /></td>
                    <td><c:out value="${client.lastname}" /></td>
                    <td><c:out value="${client.emailaddress}" /></td>
                    <td><c:out value="${client.phone}" /></td>
                    <td><c:out value="${client.address}" /></td>
                    <td><c:out value="${client.registerdate}" /></td>
                    <td>
                        <%--<a href="<%=request.getContextPath()%>/ClientActivity/show?id=<c:out value='${client.clientid}'/>&session=<c:out value='${schoolsession.sessionid}'/>">Update Activities</a>--%>
                        <%--<a href="<%=request.getContextPath()%>/SchoolSession/deleteclient?id=<c:out value='${client.clientid}'/>&session=<c:out value='${schoolsession.sessionid}'/>">Delete</a>--%>
                        <a href="<%=request.getContextPath()%>/ClientActivity/show?clientid=<c:out value='${client.clientid}'/>&sessionid=<c:out value='${schoolsession.sessionid}'/>">Update Activities</a>
                        <a href="<%=request.getContextPath()%>/SchoolSession/deleteclient?sessionid=<c:out value='${schoolsession.sessionid}'/>&clientid=<c:out value='${client.clientid}'/>">Delete</a>

                    </td>
                </tr>
            </c:forEach>

        </table>
        <c:if test="${schoolsession!=null}">
            <tr>
                <select name="clientid">
                    <c:forEach var="client" items="${allclient}">
                        <c:set var="count" scope="session" value="no"></c:set>
                        <c:forEach var="sesclient" items="${listClient}">
                            <c:if test="${client.clientid==sesclient.clientid}">
                                <c:set var="count" scope="session" value="yes"></c:set>
                            </c:if>
                        </c:forEach>
                        <c:if test="${count=='yes'}">
                            <option value="${client.clientid}" disabled>${client.firstname},${client.lastname}</option>
                        </c:if>
                        <c:if test="${count=='no'}">
                            <option value="${client.clientid}">${client.firstname},${client.lastname}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <br/>
                    <button id="submit3" class="button button1"  type="submit" value="Addclient" formaction="<%=request.getContextPath()%>/SchoolSession/addclient?sessionid=<c:out value='${schoolsession.sessionid}'/>" onclick="return ValidateData(this);">Add client</button>

                </td>
            </tr>
        </c:if>
    </div>
    </form>
</div>
</body>
</html>