package com.midwesttape.project.challengeapplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    // Deserialize, but no accidental serialization, including toString in case of any logging of the object.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private String password;
    private Address address;
}
