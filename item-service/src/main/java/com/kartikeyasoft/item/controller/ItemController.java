package com.kartikeyasoft.item.controller;
import com.kartikeyasoft.item.model.Item;
import com.kartikeyasoft.item.repository.ItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/items") @CrossOrigin
public class ItemController {
    private final ItemRepository repo;
    public ItemController(ItemRepository repo){this.repo=repo;}
    @GetMapping("/health") public String health(){return "Item Service is UP";}
    @GetMapping public List<Item> all(){return repo.findAll();}
    @GetMapping("/{id}") public ResponseEntity<Item> get(@PathVariable Long id){
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping public Item create(@RequestBody Item i){return repo.save(i);}
    @PutMapping("/{id}") public ResponseEntity<Item> update(@PathVariable Long id,@RequestBody Item i){
        if(!repo.existsById(id)) return ResponseEntity.notFound().build();
        i.setId(id); return ResponseEntity.ok(repo.save(i));
    }
    @DeleteMapping("/{id}") public ResponseEntity<?> delete(@PathVariable Long id){
        if(!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id); return ResponseEntity.ok(Map.of("message","Deleted successfully"));
    }
}
