package com.kartikeyasoft.user.controller;

import com.kartikeyasoft.user.model.UserProfile;
import com.kartikeyasoft.user.repository.UserProfileRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private final UserProfileRepository repo;

    public UserController(UserProfileRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/health")
    public String health() {
        return "User Service is UP";
    }

    @GetMapping
    public List<UserProfile> all() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> get(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public UserProfile create(@RequestBody UserProfile p) {
        return repo.save(p);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserProfile> update(
            @PathVariable Long id,
            @RequestBody UserProfile p) {

        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        p.setId(id);
        return ResponseEntity.ok(repo.save(p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repo.deleteById(id);

        return ResponseEntity.ok(
                Map.of("message", "Deleted successfully")
        );
    }
}
