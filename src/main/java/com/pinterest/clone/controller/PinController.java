package com.pinterest.clone.controller;

import com.pinterest.clone.entity.Pin;
import com.pinterest.clone.service.PinService;
import com.pinterest.clone.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pins")
@CrossOrigin(origins = "*")
public class PinController {
    @Autowired
    PinService pinService;

    @GetMapping
    public List<Pin> getAllPins() {
        return pinService.getAllPins();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pin> getPinById(@PathVariable Long id) {
        return ResponseEntity.ok(pinService.getPinById(id));
    }

    @PostMapping
    public ResponseEntity<Pin> createPin(@RequestBody Pin pin, 
                                         @RequestParam(required = false) Long boardId,
                                         @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(pinService.createPin(pin, userDetails.getId(), boardId));
    }
    
    @GetMapping("/user/{userId}")
    public List<Pin> getUserPins(@PathVariable Long userId) {
        return pinService.getPinsByUserId(userId);
    }
    
    @GetMapping("/search")
    public List<Pin> searchPins(@RequestParam String query) {
        return pinService.searchPins(query);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePin(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        pinService.deletePin(id, userDetails.getId());
        return ResponseEntity.ok().build();
    }
}
