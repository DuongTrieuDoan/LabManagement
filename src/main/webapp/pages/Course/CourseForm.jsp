<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Course</title>
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

        #table1 tr:nth-child(even){background-color: #f2f2f2;}

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
<body>
<div class="topnav">
    <a href="<%=request.getContextPath()%>/SchoolSession">School Session List</a>
    <a class="active" href="<%=request.getContextPath()%>/Program">Programs</a>
    <a href="<%=request.getContextPath()%>/Client">Clients</a>
    <a href="<%=request.getContextPath()%>/Instructor">Instructors</a>
    <a href="<%=request.getContextPath()%>/ClassRoom">Class Rooms</a>
    <a href="<%=request.getContextPath()%>/About.jsp">About</a>
</div>
<div>
    <c:if test="${errormessage!=''}">
        <h5><font color="red">${errormessage}</font></h5>
    </c:if>
</div>
<center><h2>Courses <br/> of <br/>${program.programname} (${program.programid})</h2></center>
<div align="center">
    <form  method="post">
        <table border="1" cellpadding="5" id="formtable">
                <tr>
                    <th>Course ID</th>
                    <td>
                        <c:if test="${course==null}">
                            <input type="text" name="courseid" size="45"
                                   value="<c:out value="${course.courseid}"/>"
                            />
                        </c:if>
                        <c:if test="${course!=null}">
                            <input type="hidden" name="courseid" size="45"
                                   value="<c:out value="${course.courseid}"/>"
                            />
                            ${course.courseid}
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th >Course Name</th>
                    <td>
                        <input type="text" name="coursename" size="45"
                               value="<c:out value='${course.coursename}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Start date </th>
                    <td><input type="date" name="startdate" size="45"
                                                                value="<fmt:formatDate value="${course.startdate}" pattern="yyyy-MM-dd"/>"
                    /></td>
                </tr>
            <tr>
                <th>End date </th>
                <td>
                    <input type="date" name="enddate" size="45"
                                                   value="<fmt:formatDate value="${course.enddate}" pattern="yyyy-MM-dd"/>"
            />
                </td>
            </tr>
            <tr>
                <th>Instructors</th>
                <td>
                    <select name = "instructorid" multiple>
                        <c:if test="${course == null}"><
                            option value="" disabled selected>-- select instructor --</option>
                            <c:forEach items="${instructorlist}" var="instructor">
                                <option value="${instructor.instructorid}"> ${instructor.firstname}, ${instructor.lastname}</option>
                            </c:forEach>
                        </c:if>
                        <c:if test="${course != null}">
                            <c:forEach items="${instructorlist}" var="instructor">
                                <c:if test="${fn:indexOf(course.listinstructorid,instructor.instructorid)>=0}">
                                    <option value="${instructor.instructorid}" selected> ${instructor.firstname}, ${instructor.lastname}</option>
                                </c:if>
                                <c:if test="${fn:indexOf(course.listinstructorid,instructor.instructorid)==-1}">
                                    <option value="${instructor.instructorid}" > ${instructor.firstname}, ${instructor.lastname}</option>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </select>
                </td>
            </tr>
            <tr>
                <th>Active </th>
                <td>
                    <select name="active">$
                    <c:if test="${course.active=='true'}">
                        <option value=true selected>Active</option>
                        <option value=false>Deactive</option>
                    </c:if>
                    <c:if test="${course.active=='false'}">
                        <option value=true>Active</option>
                        <option value=false selected>Deactive</option>
                    </c:if>
                    <c:if test="${course==null}">
                        <option value=true>Active</option>
                        <option value=false>Deactive</option>
                    </c:if>
                    </select>
                </td>
            </tr>
            <tr>
                <th >Syllabus Name</th>
                <td>
                    <input type="text" name="syllabus" size="45"
                           value="<c:out value='${course.syllabus}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center"  colspan="8">
                    <c:if test="${course!=null}">
                        <input type="submit" class="button button1" value="Save" onclick="return Validate();" formaction="<%=request.getContextPath()%>/Course/update?programid=<c:out value='${program.programid}'/>"/>
                    </c:if>
                    <c:if test="${course==null}">
                        <input id="button1" class="button button1" type="submit" value="Add"  onclick="return Validate();" formaction="<%=request.getContextPath()%>/Course/add?programid=<c:out value='${program.programid}'/>"/>
                    </c:if>
                    <input id="button2" class="button button1" type="submit" value="Reset to add new" onclick="return Validate();" formaction="<%=request.getContextPath()%>/Course/show?programid=<c:out value='${program.programid}'/>"/>
                </td>
            </tr>
            </table>
                <br><br>
                <div align="center">
                    <table border="1" cellpadding="5" id="table1">
                        <caption><h2>List of Courses</h2></caption>
                        <input type="hidden" name="courselist" value="<c:out value='${courselist}' />" />
                        <tr class="collapsible">
                            <th>Course ID</th>
                            <th>Course Name</th>
                            <th>Start date</th>
                            <th>End date</th>
                            <th>Instructors</th>
                            <th>Syllabus</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                        <c:forEach var="mycourse" items="${courselist}">
                            <tr align="center">
                                <td>${mycourse.courseid}</td>
                                <td>${mycourse.coursename}</td>
                                <td><fmt:formatDate value="${mycourse.startdate}" pattern="yyyy-MM-dd" /></td>
                                <td><fmt:formatDate value="${mycourse.enddate}" pattern="yyyy-MM-dd" /></td>
                                <td>
                                    <c:forEach items="${instructorlist}" var="instructor">
                                        <c:if test="${fn:indexOf(mycourse.listinstructorid,instructor.instructorid)>=0}">
                                            ${instructor.firstname}, ${instructor.lastname} <br/>
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td>${mycourse.syllabus}</td>
                                <td>
                                    <c:if test="${mycourse.active=='true'}">Active</c:if>
                                    <c:if test="${mycourse.active=='false'}">Deactive</c:if>
                                <td>
                                    <a href="<%=request.getContextPath()%>/Course/edit?programid=<c:out value='${mycourse.programid}'/>&courseid=<c:out value='${mycourse.courseid}' />">Edit</a>
                                    <a href="<%=request.getContextPath()%>/Course/delete?programid=<c:out value='${mycourse.programid}'/>&courseid=<c:out value='${mycourse.courseid}' />">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
    </form>
</div>

</body>
</html>