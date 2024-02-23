<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 20/02/2024
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Student Manager</title>
    <style>
        a {
            text-decoration: none;
        }
    </style>
</head>
<body>
<div>
    <div class="md:flex md:items-center md:justify-between">
        <div class="flex-1 min-w-0">
            <h2 style="margin-left: 10px" class="text-2xl font-bold leading-7 text-gray-900 sm:text-3xl sm:truncate">Student</h2>
        </div>
    </div>

    <div class="grid grid-cols-3 gap-6">

        <div class="col-span-3 xl:col-span-2">
            <div class="md:flex md:items-center md:justify-between">
            </div>

            <div class="mt-6 flex flex-col">
                <div class="-my-2 overflow-x-auto -mx-4 sm:-mx-6 lg:-mx-8">
                    <div class="py-2 align-middle inline-block min-w-full sm:px-6 lg:px-8">
                        <div class="shadow overflow-hidden border-b border-gray-200 sm:rounded-lg">
                            <a style="margin-left: 10px" href="/student?action=create" >Create new student</a>
                            <table class="min-w-full divide-y divide-gray-200">
                                <thead class="bg-gray-50">
                                <tr>
                                    <th scope="col" class="px-4 sm:px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">#</th>
                                    <th scope="col" class="px-4 sm:px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Name</th>
                                    <th scope="col" class="px-4 sm:px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">DateOfBirth</th>
                                    <th scope="col" class="px-4 sm:px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Email</th>
                                    <th scope="col" class="px-4 sm:px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Address</th>
                                    <th scope="col" class="px-4 sm:px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Phone</th>
                                    <th scope="col" class="px-4 sm:px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Classroom</th>
                                    <th scope="col" class="px-4 sm:px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items='${requestScope["studentList"]}' var="student">

                                        <tr class="odd:bg-white even:bg-gray-100">
                                            <td class="px-4 sm:px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">${student.getId()}</td>
                                            <td class="px-4 sm:px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">${student.getName()}</td>
                                            <td class="px-4 sm:px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">${student.getDateOfBirth()}</td>
                                            <td class="px-4 sm:px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">${student.getEmail()}</td>
                                            <td class="px-4 sm:px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">${student.getPhoneNumber()}</td>
                                            <c:forEach items='${requestScope["class"]}' var="class">
                                                <c:if test="${student.getClassId() == class.getId()}">
                                                    <td class="px-4 sm:px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">${class.getName()}</td>
                                                </c:if>
                                            </c:forEach>
                                            <td class="px-4 sm:px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900"><a href="/phone?action=edit&id=${student.getId()}" class="hover:text-indigo-500">Edit</a></td>
                                            <td class="px-4 sm:px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900"><a href="/phone?action=delete&id=${student.getId()}" class="hover:text-indigo-500">Delete</a></td>
                                        </tr>

                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-span-3 xl:col-span-1">

        </div>
    </div>
</div>

</body>
</html>
