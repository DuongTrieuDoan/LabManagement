<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Instructor</title>
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
            background-image: url(images/background.jpg);
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
    <a href="<%=request.getContextPath()%>/Program">Programs</a>
    <a href="<%=request.getContextPath()%>/Client">Clients</a>
    <a class="active" href="<%=request.getContextPath()%>/Instructor">Instructors</a>
    <a href="<%=request.getContextPath()%>/ClassRoom">Class Rooms</a>
    <a href="<%=request.getContextPath()%>/About.jsp">About</a>
</div>
<div>
    <c:if test="${errormessage!=''}">
        <h5><font color="red">${errormessage}</font></h5>
    </c:if>
</div>
<center><h2>Instructor list</h2></center>
<div align="center">
    <form  method="post">
        <table border="1" cellpadding="5" id="formtable">

                <tr>
                    <th>Instructor ID</th>
                    <td>
                        <c:if test="${instructor==null}">
                            <input type="text" name="instructorid" size="45"
                                   value="<c:out value="${instructor.instructorid}"/>"
                            />
                        </c:if>
                        <c:if test="${instructor!=null}">
                            <input type="hidden" name="instructorid" size="45"
                                   value="<c:out value="${instructor.instructorid}"/>"
                            />
                            ${instructor.instructorid}
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th >First Name</th>
                    <th>Last Name</th>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="firstname" size="45"
                               value="<c:out value='${instructor.firstname}' />"
                        />
                    </td>
                    <td>
                        <input type="text" name="lastname" size="45"
                               value="<c:out value='${instructor.lastname}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td>
                        <input type="email" name="emailaddress" size="45"
                               value="<c:out value='${instructor.emailaddress}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Adress</th>
                    <td>
                        <input type="text" name="address" size="45"
                               value="<c:out value='${instructor.address}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Phone</th>
                    <td>
                        <input type="tel" name="phone" size="45"
                               value="<c:out value='${instructor.phone}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td><b>Start date: </b><input type="date" name="startdate" size="45"
                                                                value="<fmt:formatDate value="${instructor.startdate}" pattern="yyyy-MM-dd"/>"
                    /></td>
                    <td><b>Active: </b><select name="active">$
                        <c:if test="${instructor.active=='true'}">
                            <option value=true selected>Active</option>
                            <option value=false>Deactive</option>
                        </c:if>
                        <c:if test="${instructor.active=='false'}">
                            <option value=true>Active</option>
                            <option value=false selected>Deactive</option>
                        </c:if>
                        <c:if test="${instructor==null}">
                            <option value=true>Active</option>
                            <option value=false>Deactive</option>
                        </c:if>
                    </select>
                    </td>
                </tr>
                    <td colspan="2" align="center"  colspan="8">
                        <c:if test="${instructor!=null}">
                            <input type="submit" class="button button1" value="Save" onclick="return Validate();" formaction="<%=request.getContextPath()%>/Instructor/update"/>
                        </c:if>
                        <c:if test="${instructor==null}">
                            <input id="button1" class="button button1" type="submit" value="Add"  onclick="return Validate();" formaction="<%=request.getContextPath()%>/Instructor/add"/>
                        </c:if>
                        <input id="button2" class="button button1" type="submit" value="Reset to add new" onclick="return Validate();" formaction="<%=request.getContextPath()%>/Instructor/show"/>
                    </td>
                </tr>
            </table>
                <br><br>
                <div align="center">
                    <table border="1" cellpadding="5" id="table1">
                        <caption><h2>List of Instructors</h2></caption>
                        <input type="hidden" name="clientlist" value="<c:out value='${clientlist}' />" />
                        <tr class="collapsible">
                            <th>Intructor ID</th>
                            <th>Full Name</th>
                            <th>Email</th>
                            <th>Address</th>
                            <th>Phone</th>
                            <th>Start date</th>
                            <th>Active</th>
                            <th>Actions</th>
                        </tr>
                        <c:forEach var="myinstructor" items="${instructorlist}">
                            <tr align="center">
                                <td>${myinstructor.instructorid}</td>
                                <td>${myinstructor.firstname}, ${myinstructor.lastname}</td>
                                <td>${myinstructor.emailaddress}</td>
                                <td>${myinstructor.address}</td>
                                <td>${myinstructor.phone}</td>
                                <td>${myinstructor.startdate}</td>
                                <td>
                                    <c:if test="${myinstructor.active=='true'}">Active</c:if>
                                    <c:if test="${myinstructor.active=='false'}">Deactive</c:if>
                                <td>
                                    <a href="<%=request.getContextPath()%>/Instructor/edit?instructorid=<c:out value='${myinstructor.instructorid}' />">Edit</a>
                                    <a href="<%=request.getContextPath()%>/Instructor/delete?instructorid=<c:out value='${myinstructor.instructorid}' />">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
        </form>
</div>

</body>
</html>