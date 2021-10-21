package novi.nl.library.controller;

import novi.nl.library.exception.BadRequestException;
import novi.nl.library.model.User;
import novi.nl.library.payload.UserRequestDto;
import novi.nl.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping(value = "/users/{username}")
    public ResponseEntity<?> getUser(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.getUser(username));
    }

    @PostMapping(value = "/users")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRequestDto userRequestDto) {
        String newUsername = userService.createUser(userRequestDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(newUsername)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/users/{username}")
    public ResponseEntity<?> updateUser(@PathVariable("username") String username, @RequestBody User user) {
        userService.updateUser(username, user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/users/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/users/{username}/authorities")
    public ResponseEntity<?> getUserAuthorities(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.getAuthorities(username));
    }

    @PostMapping(value = "/users/{username}/authorities")
    public ResponseEntity<?> addUserAuthority(@PathVariable("username") String username, @RequestBody String authorityString) {
        try {
            userService.addAuthority(username, authorityString);
            return ResponseEntity.noContent().build();
        }
        catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    @DeleteMapping(value = "/users/{username}/authorities/{authority}")
    public ResponseEntity<?> deleteUserAuthority(@PathVariable("username") String username, @PathVariable("authority") String authorityString) {
        userService.removeAuthority(username, authorityString);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/users/{username}/password")
    public ResponseEntity<?> setPassword(@PathVariable("username") String username, @RequestBody String password) {
        userService.setPassword(username, password);
        return ResponseEntity.noContent().build();
    }

}
