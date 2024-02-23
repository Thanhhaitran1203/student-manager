<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 23/02/2024
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Update Student</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

</head>
<body>
<h2 style="margin-left: 10px">Update new student</h2>
<div>
    <form method="post">
        <div class="input-group input-group-sm mb-3">
            <span class="input-group-text" >Name</span>
            <input type="text" name="name" value="${student.getName()}" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" placeholder="Enter name">
        </div>
        <div class="input-group input-group-sm mb-3">
            <span class="input-group-text" >Email</span>
            <input type="text" name="email" value="${student.getEmail()}" placeholder="Enter email" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
        </div>
        <div class="input-group input-group-sm mb-3">
            <span class="input-group-text" >Date of birth</span>
            <input type="text" name="date" value="${student.getDateOfBirth()}" placeholder="Enter date of birth as YYYY-MM-YY" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
        </div>
        <div class="input-group input-group-sm mb-3">
            <span class="input-group-text" >Address</span>
            <input type="text" name="address" value="${student.getAddress()}" placeholder="Enter address" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
        </div>
        <div class="input-group input-group-sm mb-3">
            <span class="input-group-text" >Name</span>
            <input type="text" name="phoneNumber" value="${student.getPhoneNumber()}" placeholder="Enter phone number" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
        </div>
        <div class="input-group mb-3">
            <label class="input-group-text" for="category">Class room</label>
            <select class="form-select" name="classId" id="category">
                <c:forEach items="${classRoom}" var="classRoom">
                    <option value="${classRoom.id}">${classRoom.name}</option>
                </c:forEach>
            </select>
        </div>
        <input value="Submit" type="submit" class="btn btn-primary">
    </form>
</div>
</body>
</html>
