package com.minishop;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType(
                "text/html; charset=UTF-8"
        );

        response.setCharacterEncoding("UTF-8");


        PrintWriter out =
                response.getWriter();


        String idParameter =
                request.getParameter("id");


        if (idParameter == null) {

            out.println("""
                <!DOCTYPE html>

                <html>

                <head>

                    <meta charset="UTF-8">

                    <title>Product - MiniShop</title>

                    <link rel="stylesheet"
                          href="css/style.css">

                </head>

                <body>

                    <nav class="navbar">

                        <div class="logo">
                            🛍️ MiniShop
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

                    <section class="product-details-section">

                        <div class="empty-message">

                            <h3>
                                Product ID is missing.
                            </h3>

                            <br>

                            <a
                                href="index.jsp"
                                class="cart-button">

                                Back to Products

                            </a>

                        </div>

                    </section>

                </body>

                </html>
            """);

            return;
        }


        try {

            int id =
                    Integer.parseInt(idParameter);


            ProductDAO productDAO =
                    new ProductDAO();


            Product product =
                    productDAO.getProductById(id);


            if (product == null) {

                out.println("""
                    <!DOCTYPE html>

                    <html>

                    <head>

                        <meta charset="UTF-8">

                        <title>Product Not Found</title>

                        <link rel="stylesheet"
                              href="css/style.css">

                    </head>

                    <body>

                        <nav class="navbar">

                            <div class="logo">
                                🛍️ MiniShop
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


                        <section class="product-details-section">

                            <div class="empty-message">

                                <h3>
                                    Product not found.
                                </h3>

                                <br>

                                <a
                                    href="index.jsp"
                                    class="cart-button">

                                    Back to Products

                                </a>

                            </div>

                        </section>

                    </body>

                    </html>
                """);

                return;
            }


            out.println("""
                <!DOCTYPE html>

                <html lang="en">

                <head>

                    <meta charset="UTF-8">

                    <meta name="viewport"
                          content="width=device-width, initial-scale=1.0">

                    <title>
                        Product Details - MiniShop
                    </title>

                    <link rel="stylesheet"
                          href="css/style.css">

                </head>


                <body>


                <!-- Navigation -->

                <nav class="navbar">

                    <div class="logo">

                        🛍️ MiniShop

                    </div>


                    <div class="nav-links">

                        <a href="index.jsp">
                            Home
                        </a>

                        <a href="index.jsp#products">
                            Products
                        </a>

                        <a href="cart">
                            🛒 Cart
                        </a>

                    </div>

                </nav>


                <!-- Product Details -->

                <section class="product-details-section">

                    <div class="product-details-card">


                        <!-- Product Image -->

                        <div class="product-details-image">
            """);


            out.println(product.getEmoji());


            out.println("""
                        </div>


                        <!-- Product Information -->

                        <div class="product-details-info">

                            <h1>
            """);


            out.println(product.getName());


            out.println("""
                            </h1>

                            <p class="product-details-price">
                                ₹
            """);


            out.println(
                    String.format(
                            "%.0f",
                            product.getPrice()
                    )
            );


            out.println("""
                            </p>


                            <p class="product-details-description">
            """);


            out.println(product.getDescription());


            out.println("""
                            </p>


                            <div class="product-details-actions">

                                <a
                                    href="add-to-cart?id=
            """);


            out.println(product.getId());


            out.println("""
                                    "
                                    class="cart-button">

                                    Add to Cart

                                </a>


                                <a
                                    href="index.jsp"
                                    class="details-button">

                                    ← Back to Products

                                </a>

                            </div>

                        </div>

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
            """);


        } catch (NumberFormatException e) {

            out.println("""
                <!DOCTYPE html>

                <html>

                <head>

                    <meta charset="UTF-8">

                    <title>Invalid Product</title>

                    <link rel="stylesheet"
                          href="css/style.css">

                </head>

                <body>

                    <nav class="navbar">

                        <div class="logo">
                            🛍️ MiniShop
                        </div>

                    </nav>


                    <section class="product-details-section">

                        <div class="empty-message">

                            <h3>
                                Invalid product ID.
                            </h3>

                            <br>

                            <a
                                href="index.jsp"
                                class="cart-button">

                                Back to Products

                            </a>

                        </div>

                    </section>

                </body>

                </html>
            """);


        } catch (Exception e) {

            e.printStackTrace();


            out.println("""
                <!DOCTYPE html>

                <html>

                <head>

                    <meta charset="UTF-8">

                    <title>Error - MiniShop</title>

                    <link rel="stylesheet"
                          href="css/style.css">

                </head>

                <body>

                    <nav class="navbar">

                        <div class="logo">
                            🛍️ MiniShop
                        </div>

                    </nav>


                    <section class="product-details-section">

                        <div class="empty-message">

                            <h3>
                                Something went wrong.
                            </h3>

                            <p>
                                Please try again.
                            </p>

                            <br>

                            <a
                                href="index.jsp"
                                class="cart-button">

                                Back to Products

                            </a>

                        </div>

                    </section>

                </body>

                </html>
            """);
        }
    }
}