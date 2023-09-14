<%-- 
    Document   : homepage
    Created on : Aug 23, 2023, 2:16:48 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link rel="stylesheet" href="css/homepage.css">
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
        <a href="./voucher" class="menu-item"><span class="menu-icon">🛍</span> Shop</a>
        <a href="#" class="menu-item"><span class="menu-icon">📞</span> Contact Us</a>
        <a href="viewcart.jsp" class="menu-item"><span class="menu-icon">🛒</span> Cart</a>
    </nav>
    
    <main>
        <section class="slideshow-container">
            <div class="slide fade">
                <img src="img/CGV.jpg" alt="Slide 1">
            </div>
            <div class="slide fade">
                <img src="img/Gongcha.jpg" alt="Slide 2">
            </div>
            <div class="slide fade">
                <img src="img/GoldenGate.jpg" alt="Slide 3">
            </div>
            
            <a class="prev">&#10094;</a>
            <a class="next">&#10095;</a>
        </section>
        <h2>Hot Deals</h2>
        <section class="product-grid">
            <!-- Placeholder for product items -->
            <div class="product-item">
                <img src="img/CGV.jpg" alt="Product 1">
                <h2>Product 1</h2>
                <p>Price: $20</p>
                <button class="add-to-cart" action="action">Add to Cart</button>
            </div>
            <div class="product-item">
                <img src="img/Gongcha.jpg" alt="Product 2">
                <h2>Product 2</h2>
                <p>Price: $30</p>
                <button class="add-to-cart">Add to Cart</button>
            </div>
            <div class="product-item">
                <img src="img/GoldenGate.jpg" alt="Product 3">
                <h2>Product 3</h2>
                <p>Price: $50</p>
                <button class="add-to-cart">Add to Cart</button>
            </div>
        </section>
    </main>
     <script src="javascript/homepage.js"></script>
    <footer>
        <p>&copy; 2023 Voucher Shop</p>
    </footer>
       
    </body>
</html>
