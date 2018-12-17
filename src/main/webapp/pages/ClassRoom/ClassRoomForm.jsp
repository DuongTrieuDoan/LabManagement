<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/momentjs/2.14.1/moment.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<html>
<head>
    <title>Class Room</title>
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
<script>
    $(function () {
        $('#datetimepicker1').datetimepicker({format: 'YYYY-MM-DD', multidate: true, multidateSeparator: ','});
    });
</script>

<body>
<div class="topnav">
    <a href="<%=request.getContextPath()%>/SchoolSession">School Session List</a>
    <a href="<%=request.getContextPath()%>/Program">Programs</a>
    <a href="<%=request.getContextPath()%>/Client">Clients</a>
    <a href="<%=request.getContextPath()%>/Instructor">Instructors</a>
    <a class="active" href="<%=request.getContextPath()%>/ClassRoom">Class Rooms</a>
    <a href="<%=request.getContextPath()%>/About.jsp">About</a>
</div>
<div>
    <c:if test="${errormessage!=''}">
        <h5><font color="red">${errormessage}</font></h5>
    </c:if>
</div>
<div class="container">
    <form  method="post">
    <div class="panel panel-primary">
        <div class="panel-heading">Class Room</div>
        <div class="panel-body">
            <div class="row">
                <div class='col-md-6'>
                    <div class="form-group">
                        <label class="control-label">Room ID</label>
                        <c:if test="${classroom==null}">
                            <input type="text" name="roomid" class="form-control"  value='${classroom.roomid}'/>
                        </c:if>
                        <c:if test="${classroom!=null}">
                            <input type="hidden" name="roomid" class="form-control"  value='${classroom.roomid}'/>
                            <input type="text" name="roomid1" class="form-control"  value='${classroom.roomid}' disabled/>
                        </c:if>
                    </div>
                </div>

                <div class='col-md-6'>
                    <div class="form-group">
                        <label class="control-label">Room Number</label>
                        <input type="text" name="roomnumber" class="form-control"  value='${classroom.roomnumber}'/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class='col-md-6'>
                    <div class="form-group">
                        <label class="control-label">Address</label>
                        <input type="text" name="address" class="form-control"  value='${classroom.address}'/>
                    </div>
                </div>
                <div class='col-md-6'>
                    <div class="form-group">
                        <label class="control-label">Capacity</label>
                            <input type="text" name="numberofseats" class="form-control bfh-number" max="15" min="5" value='${classroom.numberofseats}'>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class='col-md-6'>
                    <div class="form-group">
                        <label class="control-label">Unavailable dates</label>
                        <div class='input-group date' id='datetimepicker1'>
                            <input type='text' class="form-control" name="unavailabledatelist" value='${unavailabledatelist}'/>
                            <span class="input-group-addon">
                     <span class="glyphicon glyphicon-calendar"></span>
                     </span>
                        </div>
                    </div>
                </div>
            <div class='col-md-6'>
                <div class="form-group">
                    <label class="control-label">Active</label><br>
                    <select name="active">$
                        <c:if test="${classroom.active=='true'}">
                            <option value=true selected>Active</option>
                            <option value=false>Deactive</option>
                        </c:if>
                        <c:if test="${classroom.active=='false'}">
                            <option value=true>Active</option>
                            <option value=false selected>Deactive</option>
                        </c:if>
                        <c:if test="${classroom==null}">
                            <option value=true>Active</option>
                            <option value=false>Deactive</option>
                        </c:if>
                    </select>
                </div>
            </div>
        </div>
            <%--<input type="submit" class="btn btn-primary" value="Submit">--%>
        <c:if test="${classroom!=null}">
            <input type="submit" class="btn btn-primary" value="Save" formaction="<%=request.getContextPath()%>/ClassRoom/update?roomid=<c:out value='${classroom.roomid}' />"/>
        </c:if>
        <c:if test="${classroom==null}">
            <input id="button1" class="btn btn-primary" type="submit" value="Add"   formaction="<%=request.getContextPath()%>/ClassRoom/add"/>
        </c:if>
        <input id="button1" class="btn btn-primary" type="submit" value="Reset to add new"  formaction="<%=request.getContextPath()%>/ClassRoom/show"/>
        </div>

<%--<center>--%>
    <%--<h2>Class Room</h2>--%>
<%--</center>--%>
<%--<div align="center">--%>
    <%--<form  method="post">--%>
        <%--<table border="1" cellpadding="5" id="formtable">--%>
                <%--<tr>--%>
                    <%--<th>Room ID</th>--%>
                    <%--<td>--%>
                        <%--<c:if test="${classroom==null}">--%>
                            <%--<input type="text" name="roomid" size="45"--%>
                                   <%--value="<c:out value="${classroom.roomid}"/>"--%>
                            <%--/>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${classroom!=null}">--%>
                            <%--<input type="hidden" name="roomid" size="45"--%>
                                   <%--value="<c:out value="${classroom.roomid}"/>"--%>
                            <%--/>--%>
                            <%--${classroom.roomid}--%>
                        <%--</c:if>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<th >Room Number</th>--%>
                    <%--<td>--%>
                        <%--<input type="text" name="roomnumber" size="10"--%>
                               <%--value="<c:out value='${classroom.roomnumber}' />"--%>
                        <%--/>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<th>Adress</th>--%>
                    <%--<td>--%>
                        <%--<input type="text" name="address" size="70"--%>
                               <%--value="<c:out value='${classroom.address}' />"--%>
                        <%--/>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<th>Capacity</th>--%>
                    <%--<td>--%>
                        <%--<input type="number" name="numberofseats" size="30" min="5" max="25" value="<c:out value='${classroom.numberofseats}' />">--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                <%--<th>Unavailable dates</th>--%>
                <%--<td>--%>
                    <%--<input type="text" name="unavailabledatelist" size = "80" value="<c:out value='${unavailabledatelist}'/>" >--%>
                <%--&lt;%&ndash;<input type="text" class="form-control date" placeholder="Pick the multiple dates" name="unavailabledatelist" alue="<c:out value='${unavailabledatelist}'/>" >&ndash;%&gt;--%>
                <%--</td>--%>
            <%--</tr>--%>
                <%--<tr>--%>
                   <%--<th>Active </th>--%>
                    <%--<td>--%>
                    <%--<select name="active">$--%>
                        <%--<c:if test="${classroom.active=='true'}">--%>
                            <%--<option value=true selected>Active</option>--%>
                            <%--<option value=false>Deactive</option>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${classroom.active=='false'}">--%>
                            <%--<option value=true>Active</option>--%>
                            <%--<option value=false selected>Deactive</option>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${classroom==null}">--%>
                            <%--<option value=true>Active</option>--%>
                            <%--<option value=false>Deactive</option>--%>
                        <%--</c:if>--%>
                    <%--</select>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                    <%--<td colspan="2" align="center"  colspan="8">--%>
                        <%--<c:if test="${classroom!=null}">--%>
                            <%--<input type="submit" class="button button1" value="Save" formaction="<%=request.getContextPath()%>/ClassRoom/update"/>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${classroom==null}">--%>
                            <%--<input id="button1" class="button button1" type="submit" value="Add"   formaction="<%=request.getContextPath()%>/ClassRoom/add"/>--%>
                        <%--</c:if>--%>
                        <%--<input id="button1" class="button button1" type="submit" value="Reset to add new"  formaction="<%=request.getContextPath()%>/ClassRoom/show"/>--%>
                    <%--</td>--%>
                <%--</tr>--%>
            <%--</table>--%>
    <%--</form>--%>
                <br><br>
                <div align="center">
                    <caption><h2>List of ClassRooms</h2></caption>
                    <table border="1" cellpadding="5" id="table1">

                        <input type="hidden" name="classroomlist" value="<c:out value='${classroomlist}' />" />
                        <tr>
                            <th>Room ID</th>
                            <th>Room Number</th>
                            <th>Address</th>
                            <th>Capacity</th>
                            <th>Unavailable date</th>
                            <th>Active</th>
                            <th>Actions</th>
                        </tr>
                        <c:forEach var="myclassroom" items="${classroomlist}">
                            <tr align="center">
                                <td>${myclassroom.roomid}</td>
                                <td>${myclassroom.roomnumber}</td>
                                <td>${myclassroom.address}</td>
                                <td>${myclassroom.numberofseats}</td>
                                <td>
                                    <c:forEach items="${myclassroom.unavailabledatelist}" var="mydate">
                                        <fmt:formatDate value="${mydate}" pattern="yyyy-MM-dd" /><br/>
                                    </c:forEach>
                                </td>
                                <td>
                                    <c:if test="${myclassroom.active=='true'}">Active</c:if>
                                    <c:if test="${myclassroom.active=='false'}">Deactive</c:if>
                                <td>
                                    <a href="<%=request.getContextPath()%>/ClassRoom/edit?roomid=<c:out value='${myclassroom.roomid}' />">Edit</a>
                                    <a href="<%=request.getContextPath()%>/ClassRoom/delete?roomid=<c:out value='${myclassroom.roomid}' />">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>

    </form>
</div>


</body>
</html>