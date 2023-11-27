package at.vsa.backend;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/create")
    public boolean getAllUsers(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        return userService.createNewUser(username, email, password);
    }

    @GetMapping("user")
    public String helloUser() {
        return "Hello User";
    }

    @GetMapping("admin")
    public String helloAdmin() {
        return "Hello Admin";
    }

    @PostMapping("login")
    public String userLogin(@RequestParam String username, @RequestParam String password) {
        return String.valueOf(userService.authenticate(username, password));
    }

}