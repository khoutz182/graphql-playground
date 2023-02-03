package com.midwesttape.project.challengeapplication.dao;

import com.midwesttape.project.challengeapplication.model.Address;
import com.midwesttape.project.challengeapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created this as a {@link UserService} equivalent but for {@link Address} instead,
 * just in case this was being looked for.
 */
@Repository
@RequiredArgsConstructor
public class AddressDao {

    private final JdbcTemplate template;

    public Address getAddress(long id) {
        try {
            return template.queryForObject(
                    "select * from Address where id = ?",
                    new BeanPropertyRowMapper<>(Address.class),
                    id
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
