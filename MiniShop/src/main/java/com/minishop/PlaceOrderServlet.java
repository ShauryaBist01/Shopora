package com.minishop;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/place-order")
public class PlaceOrderServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Get customer details
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String payment = request.getParameter("payment");

        // Get current session
        HttpSession session = request.getSession(true);

        // Get cart
        List<CartItem> cart =
                (List<CartItem>) session.getAttribute("cart");

        // Check whether cart is empty
        if (cart == null || cart.isEmpty()) {

            response.sendRedirect("index.html");
            return;
        }

        // Calculate total
        double grandTotal = 0;

        for (CartItem item : cart) {
            grandTotal += item.getTotal();
        }

        // Generate a simple order ID
        String orderId =
                "MS-" +
                        UUID.randomUUID()
                                .toString()
                                .substring(0, 8)
                                .toUpperCase();

        // Set response
        response.setContentType(
                "text/html;charset=UTF-8"
        );

        var out = response.getWriter();

        // Start page
        out.println("""
            <!DOCTYPE html>

            <html>

            <head>

                <meta charset="UTF-8">

                <meta name="viewport"
                      content="width=device-width, initial-scale=1.0">

                <title>Order Confirmed - MiniShop</title>

                <link rel="stylesheet"
                      href="css/style.css">

                <style>

                    .success-container {
                        max-width: 950px;
                        margin: 60px auto;
                        padding: 0 25px;
                    }

                    .success-card {
                        background: white;
                        border-radius: 18px;
                        padding: 45px;
                        text-align: center;

                        box-shadow:
                            0 8px 30px
                            rgba(0, 0, 0, 0.08);
                    }

                    .success-icon {
                        width: 90px;
                        height: 90px;

                        margin: 0 auto 25px;

                        border-radius: 50%;

                        background-color: #dcfce7;

                        display: flex;
                        align-items: center;
                        justify-content: center;

                        font-size: 45px;
                    }

                    .success-card h1 {
                        font-size: 36px;
                        margin-bottom: 12px;
                    }

                    .success-message {
                        color: #6b7280;
                        font-size: 17px;
                        margin-bottom: 25px;
                    }

                    .order-id {
                        display: inline-block;

                        background-color: #eff6ff;

                        color: #2563eb;

                        padding: 10px 18px;

                        border-radius: 8px;

                        font-weight: bold;

                        margin-bottom: 35px;
                    }

                    .confirmation-grid {
                        display: grid;

                        grid-template-columns:
                            1fr 1fr;

                        gap: 25px;

                        margin-top: 30px;

                        text-align: left;
                    }

                    .info-card {
                        background-color: #f9fafb;

                        border-radius: 12px;

                        padding: 25px;

                        border: 1px solid #e5e7eb;
                    }

                    .info-card h3 {
                        margin-bottom: 18px;

                        font-size: 19px;
                    }

                    .info-row {
                        margin-bottom: 10px;

                        color: #4b5563;

                        line-height: 1.5;
                    }

                    .info-row strong {
                        color: #111827;
                    }

                    .order-summary {
                        margin-top: 25px;

                        background-color: white;

                        border-radius: 12px;

                        padding: 25px;

                        border: 1px solid #e5e7eb;

                        text-align: left;
                    }

                    .order-summary h2 {
                        margin-bottom: 20px;
                    }

                    .success-table {
                        width: 100%;

                        border-collapse: collapse;
                    }

                    .success-table th {
                        background-color: #111827;

                        color: white;

                        padding: 14px;

                        text-align: left;
                    }

                    .success-table td {
                        padding: 14px;

                        border-bottom:
                            1px solid #e5e7eb;
                    }

                    .total-row {
                        font-size: 19px;
                        font-weight: bold;
                    }

                    .status-box {
                        margin-top: 25px;

                        padding: 20px;

                        border-radius: 12px;

                        background-color: #f0fdf4;

                        border: 1px solid #bbf7d0;

                        color: #166534;

                        text-align: left;
                    }

                    .confirmation-actions {
                        margin-top: 35px;
                    }

                    @media (max-width: 700px) {

                        .success-card {
                            padding: 25px;
                        }

                        .confirmation-grid {
                            grid-template-columns: 1fr;
                        }

                        .success-table {
                            font-size: 14px;
                        }

                        .success-table th,
                        .success-table td {
                            padding: 9px;
                        }
                    }

                </style>

            </head>

            <body>

            <!-- NAVBAR -->

            <nav class="navbar">

                <div class="logo">
                    🛍️ MiniShop
                </div>

                <div class="nav-links">

                    <a href="index.html">
                        Home
                    </a>

                    <a href="index.html#products">
                        Products
                    </a>

                    <a href="cart">
                        🛒 Cart
                    </a>

                </div>

            </nav>


            <!-- SUCCESS PAGE -->

            <div class="success-container">

                <div class="success-card">

                    <div class="success-icon">
                        ✓
                    </div>

                    <h1>
                        Order Confirmed!
                    </h1>

                    <p class="success-message">
                        Thank you for shopping with MiniShop.
                        Your order has been successfully placed.
                    </p>
            """);

        // Order ID
        out.println(
                "<div class='order-id'>" +
                        "Order ID: " +
                        orderId +
                        "</div>"
        );

        out.println("""
                    <div class="confirmation-grid">

                        <!-- CUSTOMER INFORMATION -->

                        <div class="info-card">

                            <h3>
                                👤 Customer Information
                            </h3>
            """);

        out.println(
                "<div class='info-row'>" +
                        "<strong>Name:</strong> " +
                        name +
                        "</div>"
        );

        out.println(
                "<div class='info-row'>" +
                        "<strong>Email:</strong> " +
                        email +
                        "</div>"
        );

        out.println(
                "<div class='info-row'>" +
                        "<strong>Phone:</strong> " +
                        phone +
                        "</div>"
        );

        out.println("""
                        </div>


                        <!-- DELIVERY INFORMATION -->

                        <div class="info-card">

                            <h3>
                                📦 Delivery Information
                            </h3>
            """);

        out.println(
                "<div class='info-row'>" +
                        "<strong>Address:</strong><br>" +
                        address +
                        "</div>"
        );

        out.println(
                "<div class='info-row'>" +
                        "<strong>Payment:</strong> " +
                        payment +
                        "</div>"
        );

        out.println("""
                        </div>

                    </div>


                    <!-- ORDER SUMMARY -->

                    <div class="order-summary">

                        <h2>
                            🛍️ Order Summary
                        </h2>

                        <table class="success-table">

                            <tr>

                                <th>
                                    Product
                                </th>

                                <th>
                                    Price
                                </th>

                                <th>
                                    Quantity
                                </th>

                                <th>
                                    Total
                                </th>

                            </tr>
            """);

        // Display products
        for (CartItem item : cart) {

            out.println(
                    "<tr>" +

                            "<td>" +
                            item.getName() +
                            "</td>" +

                            "<td>₹" +
                            item.getPrice() +
                            "</td>" +

                            "<td>" +
                            item.getQuantity() +
                            "</td>" +

                            "<td>" +
                            "<strong>₹" +
                            item.getTotal() +
                            "</strong>" +
                            "</td>" +

                            "</tr>"
            );
        }

        // Grand total
        out.println(
                "<tr class='total-row'>" +

                        "<td colspan='3'>" +
                        "Grand Total" +
                        "</td>" +

                        "<td>" +
                        "₹" +
                        grandTotal +
                        "</td>" +

                        "</tr>"
        );

        out.println("""
                        </table>

                    </div>


                    <!-- STATUS -->

                    <div class="status-box">

                        <strong>
                            ✓ Order Confirmed
                        </strong>

                        <br><br>

                        Your order has been received
                        successfully and will be processed
                        shortly.

                    </div>


                    <!-- ACTIONS -->

                    <div class="confirmation-actions">

                        <a href="index.html"
                           class="btn-primary">

                            Continue Shopping

                        </a>

                    </div>

                </div>

            </div>


            <!-- FOOTER -->

            <footer class="footer">

                <p>
                    © 2026 MiniShop. All rights reserved.
                </p>

            </footer>


            </body>

            </html>
            """);

        // Clear cart after successful order
        session.removeAttribute("cart");
    }
}