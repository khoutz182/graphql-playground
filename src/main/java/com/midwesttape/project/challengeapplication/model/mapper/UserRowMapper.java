package com.midwesttape.project.challengeapplication.model.mapper;

import com.midwesttape.project.challengeapplication.model.Address;
import com.midwesttape.project.challengeapplication.model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * A {@link BeanPropertyRowMapper} that maps to a {@link User} and also to its' nested
 * {@link Address}, instead of inefficiently querying the database individually for
 * each entity.
 */
public class UserRowMapper extends BeanPropertyRowMapper<User> {

    /**
     * Default query this mapper is able to handle.
     */
    public static String SELECT_QUERY = "select " +
                "u.id, " +
                "u.firstName, " +
                "u.lastName, " +
                "u.username, " +
                "u.password, " +
                "a.id address_id, " +
                "a.address1, " +
                "a.address2, " +
                "a.city, " +
                "a.state, " +
                "a.postal " +
            "from User u " +
            "join Address a " +
            "on u.address_id = a.id " +
            "where u.id = ?";

    public UserRowMapper() {
        super(User.class);
    }

    @Override
    public User mapRow(ResultSet rs, int rowNumber) throws SQLException {
        User user = super.mapRow(rs, rowNumber);
        if (user == null) {
            return null;
        }

        Address address = new Address();
        address.setId(rs.getLong("address_id"));

        // A glaring issue with the ResultSet api is returning 0 when a value is null on numeric types.
        // Luckily the defined schema forbids nulls on address_id, but it's better to be safe than sorry.
        if (rs.wasNull()) {
            throw new SQLIntegrityConstraintViolationException("Incorrect relation, check foreign keys on User: " + user.getId());
        }
        address.setAddress1(rs.getString("address1"));
        address.setAddress2(rs.getString("address2"));
        address.setCity(rs.getString("city"));
        address.setState(rs.getString("state"));
        address.setPostal(rs.getString("postal"));
        user.setAddress(address);

        return user;
    }
}
