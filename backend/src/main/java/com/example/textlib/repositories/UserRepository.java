package com.example.textlib.repositories;

import com.example.textlib.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.ArrayList;

@Repository
public class UserRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findByEmail(String email) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE email = ?",
                (rs, rowNum) -> {
                    return new User(rs.getLong("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getBoolean("enabled")
                    );
                }, email);
    }
    public Long createUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
                PreparedStatement stmt = connection.prepareStatement("INSERT INTO users (id, email, " +
                            "password, enabled, role_id) VALUES(?, ?, ?, ?, ?)", new String[]{"id"});

                    stmt.setLong(1, user.getId());
                    stmt.setString(2, user.getEmail());
                    stmt.setString(3, user.getPassword());
                    stmt.setBoolean(4, user.getEnabled());
                    stmt.setLong(5, user.getRole().getId());

                    return stmt;
                }, keyHolder);
        return keyHolder.getKey().longValue();
    }
}
