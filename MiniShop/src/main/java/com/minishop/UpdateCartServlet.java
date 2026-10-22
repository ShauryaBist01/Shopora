package com.minishop;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/update-cart")
public class UpdateCartServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        String action = request.getParameter("action");

        HttpSession session = request.getSession(true);

        List<CartItem> cart =
                (List<CartItem>) session.getAttribute("cart");

        if (cart != null) {

            for (CartItem item : cart) {

                if (item.getId() == id) {

                    if ("increase".equals(action)) {
                        item.increaseQuantity();
                    }

                    if ("decrease".equals(action)) {
                        item.decreaseQuantity();
                    }

                    break;
                }
            }

            session.setAttribute("cart", cart);
        }

        response.sendRedirect("cart");
    }
}