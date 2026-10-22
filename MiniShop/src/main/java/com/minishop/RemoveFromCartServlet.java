package com.minishop;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/remove-from-cart")
public class RemoveFromCartServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Get product ID from URL
        int id = Integer.parseInt(request.getParameter("id"));

        // Get current session
        HttpSession session = request.getSession(true);

        // Get cart from session
        List<CartItem> cart =
                (List<CartItem>) session.getAttribute("cart");

        // Remove the selected product
        if (cart != null) {

            cart.removeIf(item -> item.getId() == id);

            // Update cart in session
            session.setAttribute("cart", cart);
        }

        // Go back to cart
        response.sendRedirect("cart");
    }
}