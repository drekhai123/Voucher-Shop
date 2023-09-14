<%-- 
    Document   : voucheredit
    Created on : Aug 25, 2023, 12:17:34 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Voucher</title>
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
        <form action="voucher" method="POST" class="styled-form">
            <input type="hidden" name="action" value="${requestScope.nextaction}" />
            <table class="styled-table">
                <tr>
                    <th>ID</th>
                    <td><input type="text" name="id" value="${requestScope.object.id}" class="input-field" /></td>
                    <th>Voucher Name</th>
                    <td><input type="text" name="title" value="${requestScope.object.title}" class="input-field" /></td>
                    <th>Description</th>
                    <td><input type="text" name="description" value="${requestScope.object.description}" class="input-field" /></td>
                    <th>Price</th>
                    <td><input type="text" name="price" value="${requestScope.object.price}" class="input-field" /></td>
                    <th>Image</th>
                    <td><input type="text" name="image" value="${requestScope.object.image}" class="input-field" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td colspan="7"><input type="submit" value="Save" class="submit-button" /></td>
                </tr>
            </table>
        </form>
    </body>
</html>
