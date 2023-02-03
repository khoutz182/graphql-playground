package com.midwesttape.project.challengeapplication.rest;

import com.midwesttape.project.challengeapplication.model.User;
import com.midwesttape.project.challengeapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public User user(@PathVariable final Long userId) {
        return userService.user(userId);
    }

    /**
     * Could be a {@link PatchMapping} since it's only updating part of a user. I went with PUT because:
     * <ul>
     *     <li>{@code id} cannot change</li>
     *     <li>{@code password} should have it's own /change-pass endpoint</li>
     *     <li>the related address should be updated on a separate controller/endpoint</li>
     *     <li>PUT seems more common to me, and we are updating the whole object if we ignore the intentionally omitted fields</li>
     * </ul>
     *
     * Example cli test:
     * <pre>
     * $ jo firstName=Bart lastName=Simpson username=bsimpson | curl --json @- -X PUT localhost:8080/v1/users/1
     * </pre>
     */
    @PutMapping("/{userId}")
    public void updateUser(@PathVariable final Long userId, @RequestBody User user) {
        userService.updateUser(userId, user);
    }
}
