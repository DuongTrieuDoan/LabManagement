<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Client</title>
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
        .button1 {border-radius: 2px;width: 220px;}
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
        * {
            box-sizing: border-box;
        }

        input[type=text], select, textarea {
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            resize: vertical;
        }

        label {
            padding: 12px 12px 12px 0;
            display: inline-block;
        }

        input[type=submit] {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            float: right;
        }

        input[type=submit]:hover {
            background-color: #45a049;
        }

        .container {
            border-radius: 5px;
            background-color: #f2f2f2;
            padding: 20px;
        }

        .col-25 {
            float: left;
            width: 25%;
            margin-top: 6px;
        }

        .col-75 {
            float: left;
            width: 75%;
            margin-top: 6px;
        }

        /* Clear floats after the columns */
        .row:after {
            content: "";
            display: table;
            clear: both;
        }

        /* Responsive layout - when the screen is less than 600px wide, make the two columns stack on top of each other instead of next to each other */
        @media screen and (max-width: 600px) {
            .col-25, .col-75, input[type=submit] {
                width: 100%;
                margin-top: 0;
            }
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
    <a class="active"  href="<%=request.getContextPath()%>/Client">Clients</a>
    <a href="<%=request.getContextPath()%>/Instructor">Instructors</a>
    <a href="<%=request.getContextPath()%>/ClassRoom">Class Rooms</a>
    <a href="<%=request.getContextPath()%>/About.jsp">About</a>
</div>
<div>
    <c:if test="${errormessage!=''}">
        <h5><font color="red">${errormessage}</font></h5>
    </c:if>
</div>
<center>
    <h2>Client</h2>
</center>
<div align="center">
    <form  method="post">
        <%--<table border="1" cellpadding="5" id="formtable">--%>

                <%--<tr>--%>
                    <%--<th>Client ID</th>--%>
                    <%--<td>--%>
                        <%--<c:if test="${client==null}">--%>
                            <%--<input type="text" name="clientid" size="45"--%>
                                   <%--value="<c:out value="${client.clientid}"/>"--%>
                            <%--/>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${client!=null}">--%>
                            <%--<input type="hidden" name="clientid" size="45"--%>
                                   <%--value="<c:out value="${client.clientid}"/>"--%>
                            <%--/>--%>
                            <%--${client.clientid}--%>
                        <%--</c:if>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<th >First Name</th>--%>
                    <%--<th>Last Name</th>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>--%>
                        <%--<input type="text" name="firstname" size="45"--%>
                               <%--value="<c:out value='${client.firstname}' />"--%>
                        <%--/>--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--<input type="text" name="lastname" size="45"--%>
                               <%--value="<c:out value='${client.lastname}' />"--%>
                        <%--/>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<th>Email</th>--%>
                    <%--<td>--%>
                        <%--<input type="email" name="emailaddress" size="45"--%>
                               <%--value="<c:out value='${client.emailaddress}' />"--%>
                        <%--/>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<th>Adress</th>--%>
                    <%--<td>--%>
                        <%--<input type="text" name="address" size="45"--%>
                               <%--value="<c:out value='${client.address}' />"--%>
                        <%--/>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<th>Phone</th>--%>
                    <%--<td>--%>
                        <%--<input type="tel" name="phone" size="45"--%>
                               <%--value="<c:out value='${client.phone}' />"--%>
                        <%--/>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td><b>Register date: </b><input type="date" name="registerdate" size="45"--%>
                                                                <%--value="<fmt:formatDate value="${client.registerdate}" pattern="yyyy-MM-dd"/>"--%>
                    <%--/></td>--%>
                    <%--<td><b>Active: </b>--%>
                        <%--<select name="active">$--%>
                        <%--<c:if test="${client.active=='true'}">--%>
                            <%--<option value=true selected>Active</option>--%>
                            <%--<option value=false>Deactive</option>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${client.active=='false'}">--%>
                            <%--<option value=true>Active</option>--%>
                            <%--<option value=false selected>Deactive</option>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${client==null}">--%>
                            <%--<option value=true>Active</option>--%>
                            <%--<option value=false>Deactive</option>--%>
                        <%--</c:if>--%>
                    <%--</select>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                    <%--<td colspan="2" align="center"  colspan="8">--%>
                        <%--<c:if test="${client!=null}">--%>
                            <%--<input type="submit" class="button button1" value="Save" onclick="return Validate();" formaction="<%=request.getContextPath()%>/Client/update"/>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${client==null}">--%>
                            <%--<input id="button1" class="button button1" type="submit" value="Add"  onclick="return Validate();" formaction="<%=request.getContextPath()%>/Client/add"/>--%>
                        <%--</c:if>--%>
                        <%--<input id="button2" class="button button1" type="submit" value="Reset to add new" onclick="return Validate();" formaction="<%=request.getContextPath()%>/Client/show"/>--%>
                    <%--</td>--%>
                <%--</tr>--%>
            <%--</table>--%>
        <div class="container">
                <div class="row">
                <div class="col-25">
                    <label for="clientid">Client ID</label>
                </div>
                <div class="col-75">
                    <c:if test="${client==null}">
                        <input type="text" id="clientid" name="clientid" placeholder="client id..">
                    </c:if>
                    <c:if test="${client!=null}">
                        <input type="text" id="clientid" name="clientid" placeholder="client id.." disabled value="${client.clientid}">
                    </c:if>
                </div>
            </div>
                <div class="row">
                    <div class="col-25">
                        <label for="fname">First Name</label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="fname" name="firstname" placeholder="Client's first name.." value="${client.firstname}">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="lname">Last Name</label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="lname" name="lastname" placeholder="Client's last name.." value="${client.lastname}">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="address">Address</label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="address" name="address" placeholder="Client's address.." value="${client.address}">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="email">Email</label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="email" name="emailaddress" placeholder="Client's email.."  value="${client.emailaddress}">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="phone">Phone</label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="phone" name="phone" placeholder="Client's phone.." value="${client.phone}">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="registerdate">Register </label>
                    </div>
                    <div class="col-75">
                        <input type="date" name="registerdate" id="registerdate" size="45" value="<fmt:formatDate value="${client.registerdate}" pattern="yyyy-MM-dd"/>"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="active">Active</label>
                    </div>
                    <div class="col-75">
                        <select name="active" id="active">$
                            <c:if test="${client.active=='true'}">
                                <option value=true selected>Active</option>
                                <option value=false>Deactive</option>
                            </c:if>
                            <c:if test="${client.active=='false'}">
                                <option value=true>Active</option>
                                <option value=false selected>Deactive</option>
                            </c:if>
                            <c:if test="${client==null}">
                                <option value=true>Active</option>
                                <option value=false>Deactive</option>
                            </c:if>
                        </select>
                    </div>
                </div>

                <div class="row">
                    <%--<input type="submit" value="Submit">--%>
                        <input id="button2"  type="submit" class="button button1" value="Reset to add new" onclick="return Validate();" formaction="<%=request.getContextPath()%>/Client/show"/>
                    <c:if test="${client!=null}">
                        <input type="submit"  class="button button1" value="Save" onclick="return Validate();" formaction="<%=request.getContextPath()%>/Client/update?clientid=${client.clientid}"/>
                    </c:if>
                    <c:if test="${client==null}">
                        <input id="button1"  type="submit" class="button button1" value="Add"  onclick="return Validate();" formaction="<%=request.getContextPath()%>/Client/add?clientid=${client.clientid}"/>
                    </c:if>

                </div>
        </div>
                <br><br>
                <div align="center">
                    <table border="1" cellpadding="5" id="table1">
                        <caption><h2>List of Clients</h2></caption>
                        <input type="hidden" name="clientlist" value="<c:out value='${clientlist}' />" />
                        <tr>
                            <th>Intructor ID</th>
                            <th>Full Name</th>
                            <th>Email</th>
                            <th>Address</th>
                            <th>Phone</th>
                            <th>Start date</th>
                            <th>Active</th>
                            <th>Actions</th>
                        </tr>
                        <c:forEach var="myclient" items="${clientlist}">
                            <tr align="center">
                                <td>${myclient.clientid}</td>
                                <td>${myclient.firstname}, ${myclient.lastname}</td>
                                <td>${myclient.emailaddress}</td>
                                <td>${myclient.address}</td>
                                <td>${myclient.phone}</td>
                                <td>${myclient.registerdate}</td>
                                <td>
                                    <c:if test="${myclient.active=='true'}">Active</c:if>
                                    <c:if test="${myclient.active=='false'}">Deactive</c:if>
                                <td>
                                    <a href="<%=request.getContextPath()%>/Client/edit?clientid=<c:out value='${myclient.clientid}' />">Edit</a>
                                    <a href="<%=request.getContextPath()%>/Client/delete?clientid=<c:out value='${myclient.clientid}' />">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
        </form>
</div>

</body>
</html>