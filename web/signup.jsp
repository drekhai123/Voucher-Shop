<%-- 
    Document   : signup
    Created on : Aug 24, 2023, 3:36:57 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SignUp Page</title>
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

        input[type="text"],
        input[type="password"],
        input[type="email"] {
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
        
        /* Additional styles for the sign-up form */
        .signup-form {
            background-color: #444; /* Change background color for the sign-up form */
            padding: 20px;
            border-radius: 10px;
        }

        .signup-form label {
            display: block;
            margin-bottom: 10px;
        }

        .signup-form input[type="text"],
        .signup-form input[type="password"],
        .signup-form input[type="email"] {
            background-color: #ddd; /* Change input field background color */
            color: #000;
            border: 1px solid #000;
        }
    </style>
    </head>
    <body>
        <div class="signup-form">
        <h2>Sign Up</h2>
        <%! String error; %>
        <% error = (String) request.getAttribute("error"); 
        if (error != null) {
            out.print("<h2>"+error+"</h2>"); 
        }%>
        <form action="./login" method="POST">
            <label for="user">Username:</label>
            <input name="user" type="text"><br/>
            
            <label for="email">Email:</label>
            <input name="email" type="email"><br/>
            
            <label for="password">Password:</label>
            <input name="password" type="password"><br/>
            
            <input value="Sign Up" type="submit" name="action">
        </form>
    </div>
    </body>
</html>
