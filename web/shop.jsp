<%-- 
    Document   : shop
    Created on : Aug 24, 2023, 3:04:46 PM
    Author     : HP
--%>

<%@page import="java.util.List"%>
<%@page import="voucher.VoucherDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Voucher Shop</title>
        <link rel="stylesheet" href="css/homepage.css"> 
        <link rel="stylesheet" href="css/shop.css">
        <style>
        /* Add your CSS styling here */
        table {
            width: 100%;
            border-collapse: collapse;
        }
        .img-small {
            max-width: 100px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        /* Add more styling as needed */
    </style>
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
         <!-- Search Form -->
        <form action="voucher" method="POST" class="search-form">
            <input type="text" name="keyword" value="" class="search-input" placeholder="Search vouchers"/>
            <input type="submit" value="Search" name="action" class="search-button" />
        </form>
         
    <form action="voucher" method="post" class="filter-form">
        <label for="category">Select a Category:</label>
        <select name="category" id="category" class="select-box">
            <option value="1">Foods</option>
            <option value="2">Clothes</option>
            <option value="3">Services</option>
            <option value="4">Drinks</option>
            <option value="5">Entertainment</option>
            <!-- Add more categories here -->
        </select>
        <input type="submit" value="Filter" name="action" class="filter-button">
    </form>
         <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Description</th>
                <th>Price</th>
                <th>Image</th>
                <th>Details</th>
                <th>Delete</th>
            </tr>
        </thead>
        <tbody>
            <% List<VoucherDTO> voucherList = (List<VoucherDTO>)request.getAttribute("list");
            if (voucherList != null) {
               for (VoucherDTO voucher : voucherList) { %>
                <tr>
                    <td><%= voucher.getId()%></td>
                    <td><%= voucher.getTitle() %></td>
                    <td><%= voucher.getDescription() %></td>
                    <td>$<%= voucher.getPrice() %></td>
                    <td>
                        <img src="<%= voucher.getImage() %>" alt="<%= voucher.getTitle() %>" class="img-small">
                    </td>
                    <td><a href="voucher?action=details&id=<%= voucher.getId()%>" class="details-link">More details</a></td>
                    <td class="delete-cell">
                                <form action="voucher">
                                    <input type=hidden name=action value=delete>
                                    <input type=hidden name=id value="<%= voucher.getId()%>">
                                    <input type=submit value=Delete class="delete-button">
                                </form>
                            </td>
                    <td><input type="hidden" name="categoryID" value="<%= voucher.getCategoryID() %>" /></td> 
                    
                </tr>
            <% }
                } else{
                    out.print("Hello");
                    }
                %>
        </tbody>
    </table>
        <form action="voucher" method="POST">
                <input type=hidden name="action" value="create">
                <input type=submit value="Create" class="create-button">
            </form>
    </body>
</html>
