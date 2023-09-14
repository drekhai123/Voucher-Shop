<%-- 
    Document   : voucherdetails
    Created on : Aug 25, 2023, 11:47:25 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Voucher Details</title>
        <link rel="stylesheet" href="css/homepage.css"> 
        <link rel="stylesheet" href="css/shop.css">
    </head>
    <body>
         <header>
            <h1>Welcome to Voucher Shop</h1>
            <div style="display:fles; gap:10px">
                Hello, ${usersession.getUsername()}
            </div>
             <!-- Logout Form -->
            <form action="login" method="POST" class="logout-form">
                <input type=hidden name="action" value="logout">
                <input type="submit" value="Log out" class="logout-button" />
            </form>
        </header>

    <nav class="menu-bar">
        <a href="homepage.jsp" class="menu-item"><span class="menu-icon">🏠</span> Home</a>
        <a href="voucher" class="menu-item"><span class="menu-icon">🛍</span> Shop</a>
        <a href="#" class="menu-item"><span class="menu-icon">📞</span> Contact Us</a>
        <a href="viewcart.jsp" class="menu-item"><span class="menu-icon">🛒</span> Cart</a>
    </nav>
            <table class="voucher-details">
            <tr>
                <td class="label">Voucher Id</td>
                <td>${requestScope.object.id}</td>
            </tr>
            <tr>
                <td class="label">Title</td>
                <td>${requestScope.object.title}</td>
            </tr>
            <tr>
                <td class="label">Description</td>
                <td>${requestScope.object.description}</td>
            </tr>
            <tr>
                <td class="label">Price</td>
                <td>${requestScope.object.price}</td>
            </tr>
             <tr>
                <td class="label">Image</td>
                 <td>
                     <img src="${requestScope.object.image}" alt="${requestScope.object.title}" class="img-small">
                 </td>
            </tr>
            
        </table>         
             
         <form action="voucher" method="POST">
             <input type=hidden name="id" value="${requestScope.object.id}">
             <input type=hidden name="action" value="edit">
             <input type=submit value="Edit" class="edit-button">
         </form>
    </body>
</html>
