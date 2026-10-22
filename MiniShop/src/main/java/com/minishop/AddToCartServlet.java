package com.minishop;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String idParameter =
                request.getParameter("id");

        if (idParameter == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        try {

            int productId =
                    Integer.parseInt(idParameter);

            // Get product from MySQL

            ProductDAO productDAO =
                    new ProductDAO();

            Product product =
                    productDAO.getProductById(productId);

            // Check if product exists

            if (product == null) {
                response.sendRedirect("index.jsp");
                return;
            }

            // Get current session

            HttpSession session =
                    request.getSession();

            // Get existing cart

            List<CartItem> cart =
                    (List<CartItem>)
                            session.getAttribute("cart");

            // Create cart if it doesn't exist

            if (cart == null) {

                cart =
                        new ArrayList<>();

                session.setAttribute(
                        "cart",
                        cart
                );
            }

            // Check if product is already in cart

            boolean found = false;

            for (CartItem item : cart) {

                if (item.getId()
                        == product.getId()) {

                    item.increaseQuantity();

                    found = true;

                    break;
                }
            }

            // Add new product to cart

            if (!found) {

                CartItem cartItem =
                        new CartItem(
                                product.getId(),
                                product.getName(),
                                product.getPrice()
                        );

                cart.add(cartItem);
            }

            // Go to cart

            response.sendRedirect("cart");

        } catch (NumberFormatException e) {

            response.sendRedirect("index.jsp");

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect("index.jsp");
        }
    }
}