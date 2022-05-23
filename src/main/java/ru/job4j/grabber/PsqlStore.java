package ru.job4j.grabber;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store, AutoCloseable {
    private Connection cc;

    public PsqlStore(Properties cfg) {
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
            cc = DriverManager.getConnection(
                    cfg.getProperty("url"),
                    cfg.getProperty("username"),
                    cfg.getProperty("password")
            );
        } catch (Exception exc) {
            throw new IllegalStateException();
        }
    }

    @Override
    public void save(Post post) {
        try (PreparedStatement statement = cc.prepareStatement(
                "insert into post(name, text, link, created) values(?, ?, ?, ?) on conflict(link) do nothing",
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getDescription());
            statement.setString(3, post.getLink());
            statement.setTimestamp(4, Timestamp.valueOf(post.getCreated()));
            statement.execute();
            try (ResultSet generatedKey = statement.getGeneratedKeys()) {
                if (generatedKey.next()) {
                    post.setId(generatedKey.getInt(1));
                }
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> result = new ArrayList<>();
        try (PreparedStatement statement = cc.prepareStatement("select * from post")) {
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    result.add(getFromDB(set));
                }
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return result;
    }

    @Override
    public Post findById(int id) {
        Post post = null;
        try (PreparedStatement statement = cc.prepareStatement(
                "select * from post where id = ?")) {
            statement.setInt(1, id);
            try (ResultSet set = statement.executeQuery()) {
                if (set.next()) {
                    post = getFromDB(set);
                }
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return post;
    }

    private Post getFromDB(ResultSet set) throws SQLException {
        return new Post(
                set.getInt("id"),
                set.getString("name"),
                set.getString("link"),
                set.getString("text"),
                set.getTimestamp("created").toLocalDateTime()
        );
    }

    @Override
    public void close() throws Exception {
        if (cc != null) {
            cc.close();
        }
    }

    public static void main(String[] args) {
        Post post1 = new Post("Java developer", "http://1",
                "Очень нужен", LocalDateTime.now());
        Post post2 = new Post("Android developer", "http://2",
                "С прямыми руками", LocalDateTime.now());
        Properties cfg = new Properties();
        try (InputStream in = PsqlStore.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            cfg.load(in);
            try (PsqlStore store = new PsqlStore(cfg)) {
                store.save(post1);
                store.save(post2);
                store.getAll().forEach(System.out::println);
                System.out.println();
                System.out.println(store.findById(post1.getId()));
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
