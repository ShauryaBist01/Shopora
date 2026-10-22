package com.minishop;

import java.sql.Connection;

public class DatabaseTest {

    public static void main(String[] args) {

        try {

            Connection connection =
                    DBConnection.getConnection();

            if (connection != null) {

                System.out.println(
                        "Database connection successful!"
                );

                connection.close();

            } else {

                System.out.println(
                        "Database connection failed!"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Database connection failed!"
            );

            e.printStackTrace();
        }
    }
}