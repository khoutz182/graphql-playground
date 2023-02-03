package com.midwesttape.project.challengeapplication.service;

import com.midwesttape.project.challengeapplication.model.User;
import com.midwesttape.project.challengeapplication.model.mapper.UserRowMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final JdbcTemplate template;

    public User user(final Long userId) {
        try {
            return template.queryForObject(
                UserRowMapper.SELECT_QUERY,
                new UserRowMapper(),
                userId
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void updateUser(Long userId, User user) {
        String updateStatement = "update User set "
                + "firstName = ?, "
                + "lastName = ?, "
                + "username = ? "
                + "where id = ?";

        Object[] parameters = new Object[]{
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                userId
        };

        try {
            template.update(updateStatement, parameters);
        } catch (DataAccessException e) {
            log.error("Failed to update user", e);
            throw e;
        }
    }
}
