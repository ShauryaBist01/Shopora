<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="com.minishop.CartItem" %>

<%
    List<CartItem> cart =
            (List<CartItem>) session.getAttribute("cart");

    double grandTotal = 0;

    if (cart != null) {

        for (CartItem item : cart) {

            grandTotal += item.getTotal();

        }
    }
%>

<!DOCTYPE html>

<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Checkout - MiniShop</title>

    <link rel="stylesheet"
          href="css/style.css">

</head>


<body>


<!-- =========================================
     NAVIGATION
     ========================================= -->

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


<!-- =========================================
     CHECKOUT
     ========================================= -->

<section class="checkout-section">

    <div class="checkout-container">


        <h1>
            Checkout
        </h1>


        <p style="margin-bottom: 35px; color: #64748b;">
            Review your order and enter your delivery details.
        </p>


        <!-- =========================================
             ORDER SUMMARY
             ========================================= -->

        <h2 style="margin-bottom: 25px;">
            Order Summary
        </h2>


        <% if (cart == null || cart.isEmpty()) { %>

            <div class="empty-message">

                <h3>
                    Your cart is empty
                </h3>

                <p>
                    Add some products before checking out.
                </p>

                <br>

                <a href="index.jsp"
                   class="cart-button">

                    Continue Shopping

                </a>

            </div>

        <% } else { %>


            <div style="overflow-x: auto;">

                <table style="
                    width: 100%;
                    border-collapse: collapse;
                    margin-bottom: 30px;
                ">

                    <thead>

                        <tr style="
                            border-bottom: 2px solid #e2e8f0;
                            text-align: left;
                        ">

                            <th style="padding: 14px;">
                                Product
                            </th>

                            <th style="padding: 14px;">
                                Price
                            </th>

                            <th style="padding: 14px;">
                                Quantity
                            </th>

                            <th style="padding: 14px;">
                                Total
                            </th>

                        </tr>

                    </thead>


                    <tbody>

                    <% for (CartItem item : cart) { %>

                        <tr style="
                            border-bottom: 1px solid #e2e8f0;
                        ">

                            <td style="padding: 14px; font-weight: 600;">

                                <%= item.getName() %>

                            </td>


                            <td style="padding: 14px;">

                                ₹<%= String.format(
                                        "%.0f",
                                        item.getPrice()
                                ) %>

                            </td>


                            <td style="padding: 14px;">

                                <%= item.getQuantity() %>

                            </td>


                            <td style="padding: 14px; font-weight: 600;">

                                ₹<%= String.format(
                                        "%.0f",
                                        item.getTotal()
                                ) %>

                            </td>

                        </tr>

                    <% } %>


                        <tr>

                            <td colspan="3"
                                style="
                                    padding: 18px 14px;
                                    text-align: right;
                                    font-weight: bold;
                                    font-size: 18px;
                                ">

                                Grand Total

                            </td>


                            <td style="
                                padding: 18px 14px;
                                font-weight: bold;
                                font-size: 20px;
                                color: #2563eb;
                            ">

                                ₹<%= String.format(
                                        "%.0f",
                                        grandTotal
                                ) %>

                            </td>

                        </tr>

                    </tbody>

                </table>

            </div>


            <!-- =========================================
                 CUSTOMER FORM
                 ========================================= -->

            <h2 style="margin-bottom: 25px;">
                Customer Details
            </h2>


            <form action="place-order"
                  method="post">


                <!-- Name -->

                <div class="form-group">

                    <label for="name">
                        Full Name
                    </label>

                    <input
                        type="text"
                        id="name"
                        name="name"
                        placeholder="Enter your full name"
                        required>

                </div>


                <!-- Email -->

                <div class="form-group">

                    <label for="email">
                        Email Address
                    </label>

                    <input
                        type="email"
                        id="email"
                        name="email"
                        placeholder="Enter your email"
                        required>

                </div>


                <!-- Phone -->

                <div class="form-group">

                    <label for="phone">
                        Phone Number
                    </label>

                    <input
                        type="tel"
                        id="phone"
                        name="phone"
                        placeholder="Enter your phone number"
                        required>

                </div>


                <!-- Address -->

                <div class="form-group">

                    <label for="address">
                        Delivery Address
                    </label>

                    <textarea
                        id="address"
                        name="address"
                        placeholder="Enter your complete delivery address"
                        required></textarea>

                </div>


                <!-- Payment -->

                <div class="form-group">

                    <label for="payment">
                        Payment Method
                    </label>

                    <select
                        id="payment"
                        name="payment"
                        required>

                        <option value="">
                            Select payment method
                        </option>

                        <option value="Cash on Delivery">
                            Cash on Delivery
                        </option>

                        <option value="UPI">
                            UPI
                        </option>

                        <option value="Credit/Debit Card">
                            Credit / Debit Card
                        </option>

                    </select>

                </div>


                <!-- Submit -->

                <button
                    type="submit"
                    class="place-order-button">

                    Place Order

                </button>

            </form>


        <% } %>


    </div>

</section>


<!-- =========================================
     FOOTER
     ========================================= -->

<footer>

    <p>
        © 2026 MiniShop. All rights reserved.
    </p>

</footer>


</body>

</html>