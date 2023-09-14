<%-- 
    Document   : login
    Created on : Aug 20, 2023, 12:14:14 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <style>
        body {
            background-color: #000;
            color: #fff;
            text-align: center;
        }

        h1 {
            color: #fff;
        }

        form {
            margin-top: 20px;
        }

        .input-group {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        input[type="text"],
        input[type="password"] {
            background-color: #fff;
            color: #000;
            border: 1px solid #000;
            padding: 10px;
            border-radius: 10px;
            width: 60%;
            margin: 10px auto; /* Add margin to center-align inputs and create space */
        }

        input[type="submit"] {
            background-color: #000;
            color: #fff;
            border: 1px solid #fff;
            padding: 10px 20px;
            border-radius: 10px;
            margin-top: 10px;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #333;
        }

        img {
            margin-top: 20px;
        }
    </style>
    </head>
    <body>
         <h1>Please login</h1>
        <%! String err; %>
        <% err = (String) request.getAttribute("error"); 
        if (err != null) {
            out.print("<h2>"+err+"</h2>"); 
        }%>
        <img src="img/logovoucher.png" width="200">
        <form action="./login" name="" method="POST">
        <div class="input-group">
            <label for="username">Username:</label>
            <input name="user" id="username" type="text">
        </div>
        <div class="input-group">
            <label for="password">Password:</label>
            <input name="password" id="password" type="password">
        </div>
        <input value="Login" type="submit"><br/>
    </form>
        <form action="signup.jsp" method="POST">
            <p>Does not have account??</p><input value="Signup" type="submit">
        </form>
    </body>
</html>
