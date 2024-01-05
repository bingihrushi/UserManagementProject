<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="com.manage.user.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f2f2f2;
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100vh;
}

.container {
    background-color: #fff;
    width: 40%;
    padding: 20px;
    border: 2px solid black;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

form {
    max-width: 400px;
    margin: 0 auto;
}

label {
    display: block;
    margin-bottom: 8px;
}

input {
    width: 100%;
    padding: 8px;
    margin-bottom: 16px;
    box-sizing: border-box;
}

input[type="submit"]{
    background-color: tomato;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    display: block;
    margin: 0 auto;
}

</style>
</head>
<body>

  <% User user=(User)request.getAttribute("user"); %>


    
   <div class="container">
             <form action="<%= (user != null) ? "update" : "insert" %>" method="post">
        <h2>
        <%if(user!=null){ %>
             Edit User
          <%} else { %>
          Add New User
          <%} %>
        </h2>
        <%if(user!=null){ %>
        <input type="hidden" name="id" value="<%=user.getId()%>">
        <%} %>
        <label>Name</label>
        <input type="text" value="<%= (user != null) ? user.getName() : "" %>" name="name">
            <label>Email</label>
            <input type="text" value="<%= (user != null) ? user.getEmail() : "" %>" name="email">

            <label>Country</label>
            <input type="text" value="<%= (user != null) ? user.getCountry() : "" %>" name="country">

            <input type="submit">
        </form>
   </div>

</body>
</html>