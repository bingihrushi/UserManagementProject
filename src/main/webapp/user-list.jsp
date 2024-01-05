<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*,com.manage.user.*" %>
<html>

<head>
    <title>User Management Application</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: tomato;
            padding: 10px;
        }

        header a {
            color: white;
            text-decoration: none;
            font-weight: bold;
        }
        header ul
        {
           list-style-type: none;
        }

        .container {
            width: 80%;
            margin: 10px auto;
        }

        .btn-success {
            background-color: maroon;
            color: white;
            text-decoration: none;
            padding: 10px 15px;
            border-radius: 5px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        .text-center {
            text-align: center;
        }
    </style>
</head>

<body>

    <header>
        <nav>
            <div>
                <a href="https://www.javaguides.net">User Management App</a>
            </div>

            <ul>
                <li><a href="list">Users</a></li>
            </ul>
        </nav>
    </header>
    <br>

    <div class="container">
        <h3 class="text-center">List of Users</h3><br>
        <hr><br>
        <div class="container text-left">
            <a href="new" class="btn btn-success">Add New User</a>
        </div>
        <br>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Country</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                
                <% List<User> listUser = (List<User>)request.getAttribute("listUser");
                if(listUser!=null) {
                for(User user:listUser){  %>
                <tr>
                   <td><%= user.getId() %></td>
                   <td><%= user.getName() %></td>
                   <td><%= user.getEmail() %></td>
                   <td><%= user.getCountry() %></td>
                   <td><a href="edit?id=<%=user.getId() %>">Edit</a>
                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   <a href="delete?id=<%= user.getId()%>">Delete</a></td>
                </tr>
                 <%}} %>
            </tbody>
        </table>
    </div>
</body>

</html>