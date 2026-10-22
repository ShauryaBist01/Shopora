package com.minishop;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

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


        HttpSession session =
                request.getSession();


        List<CartItem> cart =
                (List<CartItem>)
                        session.getAttribute("cart");


        double grandTotal = 0;


        if (cart != null) {

            for (CartItem item : cart) {

                grandTotal += item.getTotal();

            }
        }


        out.println("""
            <!DOCTYPE html>

            <html lang="en">

            <head>

                <meta charset="UTF-8">

                <meta name="viewport"
                      content="width=device-width, initial-scale=1.0">

                <title>Cart - MiniShop</title>

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


            <!-- Cart -->

            <section class="cart-section">

                <div class="cart-container">

                    <h1>
                        🛒 Your Shopping Cart
                    </h1>
        """);


        if (cart == null || cart.isEmpty()) {

            out.println("""
                    <div class="empty-message">

                        <h3>
                            Your cart is empty
                        </h3>

                        <p>
                            Add some products to get started.
                        </p>

                        <br>

                        <a
                            href="index.jsp"
                            class="cart-button">

                            Continue Shopping

                        </a>

                    </div>
            """);

        } else {


            out.println("""
                    <div style="
                        background: white;
                        border-radius: 16px;
                        overflow: hidden;
                        box-shadow:
                            0 8px 25px rgba(15, 23, 42, 0.08);
                    ">

                        <div style="
                            display: grid;
                            grid-template-columns:
                                2fr 1fr 1.5fr 1fr 1fr;
                            gap: 15px;
                            padding: 20px 25px;
                            background: #f8fafc;
                            border-bottom: 1px solid #e2e8f0;
                            font-weight: bold;
                        ">

                            <div>
                                Product
                            </div>

                            <div>
                                Price
                            </div>

                            <div>
                                Quantity
                            </div>

                            <div>
                                Total
                            </div>

                            <div>
                                Action
                            </div>

                        </div>
            """);


            for (CartItem item : cart) {


                out.println("""
                        <div style="
                            display: grid;
                            grid-template-columns:
                                2fr 1fr 1.5fr 1fr 1fr;
                            gap: 15px;
                            align-items: center;
                            padding: 22px 25px;
                            border-bottom: 1px solid #e2e8f0;
                        ">
                """);


                out.println(
                        "<div style=\"font-weight: 600;\">"
                                + item.getName()
                                + "</div>"
                );


                out.println(
                        "<div>₹"
                                + String.format(
                                "%.0f",
                                item.getPrice()
                        )
                                + "</div>"
                );


                out.println("""
                            <div style="
                                display: flex;
                                align-items: center;
                                gap: 10px;
                            ">
                """);


                out.println(
                        "<a "
                                + "href=\"update-cart?id="
                                + item.getId()
                                + "&action=decrease\" "
                                + "class=\"quantity-button\">"
                                + "−"
                                + "</a>"
                );


                out.println(
                        "<strong>"
                                + item.getQuantity()
                                + "</strong>"
                );


                out.println(
                        "<a "
                                + "href=\"update-cart?id="
                                + item.getId()
                                + "&action=increase\" "
                                + "class=\"quantity-button\">"
                                + "+"
                                + "</a>"
                );


                out.println("""
                            </div>
                """);


                out.println(
                        "<div style=\"font-weight: 600;\">₹"
                                + String.format(
                                "%.0f",
                                item.getTotal()
                        )
                                + "</div>"
                );


                out.println(
                        "<div>"
                                + "<a "
                                + "href=\"remove-from-cart?id="
                                + item.getId()
                                + "\" "
                                + "class=\"remove-button\">"
                                + "Remove"
                                + "</a>"
                                + "</div>"
                );


                out.println("""
                        </div>
                """);

            }


            out.println("""
                    </div>


                    <div class="cart-total">

                        <h2>
                            Grand Total:
                            <span style="color: #2563eb;">
                """);


            out.println(
                    "₹"
                            + String.format(
                            "%.0f",
                            grandTotal
                    )
            );


            out.println("""
                            </span>
                        </h2>

                        <div style="
                            display: flex;
                            justify-content: flex-end;
                            gap: 15px;
                            flex-wrap: wrap;
                        ">

                            <a
                                href="index.jsp"
                                class="details-button">

                                ← Continue Shopping

                            </a>

                            <a
                                href="checkout.jsp"
                                class="checkout-button">

                                Proceed to Checkout →

                            </a>

                        </div>

                    </div>
            """);

        }


        out.println("""
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
    }
}