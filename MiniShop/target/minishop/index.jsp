<%@ page import="java.util.List" %>
<%@ page import="com.minishop.Product" %>
<%@ page import="com.minishop.ProductDAO" %>

<%
    ProductDAO productDAO = new ProductDAO();
    List<Product> products = productDAO.getAllProducts();
%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <title>MiniShop - Online Store</title>

    <link rel="stylesheet"
          href="css/style.css">

</head>

<body>

<!-- Navigation -->

<nav class="navbar">

    <div class="logo">
        MiniShop
    </div>

    <div class="nav-links">

        <a href="index.jsp">
            Home
        </a>

        <a href="cart">
            🛒 Cart
        </a>

    </div>

</nav>


<!-- Hero Section -->

<section class="hero">

    <div class="hero-content">

        <h1>
            Welcome to MiniShop
        </h1>

        <p>
            Simple products. Simple shopping.
        </p>

        <a href="#products"
           class="hero-button">
            Shop Now
        </a>

    </div>

</section>


<!-- Products Section -->

<section class="products-section"
         id="products">

    <div class="section-heading">

        <h2>
            Our Products
        </h2>

        <p>
            Browse our collection
        </p>

    </div>


    <div class="product-grid">

        <% if (products.isEmpty()) { %>

            <div class="empty-message">

                <h3>
                    No products available
                </h3>

                <p>
                    Please check the database.
                </p>

            </div>

        <% } else { %>


            <% for (Product product : products) { %>

                <div class="product-card">


                    <!-- Product Icon -->

                    <div class="product-image">

                        <%= product.getEmoji() %>

                    </div>


                    <!-- Product Information -->

                    <div class="product-info">

                        <h3>
                            <%= product.getName() %>
                        </h3>

                        <p class="price">

                            ₹<%= String.format(
                                    "%.0f",
                                    product.getPrice()
                            ) %>

                        </p>


                        <p class="description">

                            <%= product.getDescription() %>

                        </p>


                        <!-- Buttons -->

                        <div class="product-actions">

                            <a
                                href="product?id=<%= product.getId() %>"
                                class="details-button">

                                View Details

                            </a>


                            <a
                                href="add-to-cart?id=<%= product.getId() %>"
                                class="cart-button">

                                Add to Cart

                            </a>

                        </div>

                    </div>

                </div>

            <% } %>


        <% } %>

    </div>

</section>


<!-- Footer -->

<footer>

    <p>
        © 2026 MiniShop. All rights reserved.
    </p>

</footer>

</body>

</html>