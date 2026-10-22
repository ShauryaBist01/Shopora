package com.minishop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductDAO {

    public Product getProductById(int id) {

        Product product = null;

        String sql =
                "SELECT id, name, price, description, emoji " +
                        "FROM products WHERE id = ?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            // Set product ID
            ps.setInt(1, id);

            // Execute SELECT query
            ResultSet rs = ps.executeQuery();

            // If product exists
            if (rs.next()) {

                product = new Product();

                product.setId(
                        rs.getInt("id")
                );

                product.setName(
                        rs.getString("name")
                );

                product.setPrice(
                        rs.getDouble("price")
                );

                product.setDescription(
                        rs.getString("description")
                );

                product.setEmoji(
                        rs.getString("emoji")
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return product;
    }

    public java.util.List<Product> getAllProducts() {

        java.util.List<Product> products =
                new java.util.ArrayList<>();

        String sql =
                "SELECT id, name, price, description, emoji " +
                        "FROM products";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                Product product = new Product();

                product.setId(
                        rs.getInt("id")
                );

                product.setName(
                        rs.getString("name")
                );

                product.setPrice(
                        rs.getDouble("price")
                );

                product.setDescription(
                        rs.getString("description")
                );

                product.setEmoji(
                        rs.getString("emoji")
                );

                products.add(product);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return products;
    }
}