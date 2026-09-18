package com.kartikeyasoft.auth.controller;
import com.kartikeyasoft.auth.model.User;
import com.kartikeyasoft.auth.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController @RequestMapping("/auth") @CrossOrigin
public class AuthController {
    private final UserRepository repo;
    public AuthController(UserRepository repo){this.repo=repo;}

    @GetMapping("/health") public String health(){return "Auth Service is UP";}

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        if(repo.findByUsername(user.getUsername()).isPresent())
            return ResponseEntity.badRequest().body(Map.of("message","Username already exists"));
        User saved=repo.save(user);
        return ResponseEntity.ok(Map.of("message","Registration successful","userId",saved.getId()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User req){
        return repo.findByUsername(req.getUsername())
            .filter(u->u.getPassword().equals(req.getPassword()))
            .map(u->ResponseEntity.ok(Map.of("message","Login successful","userId",u.getId(),"username",u.getUsername())))
            .orElseGet(()->ResponseEntity.status(401).body(Map.of("message","Invalid username or password")));
    }
}
