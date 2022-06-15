package ru.job4j.srp;

public class Connection {
    private static Connection connection;

    private Connection() {
    }

    public Connection getConnection() {
        if (connection == null) {
            connection = new Connection();
        }
        return connection;
    }
}
