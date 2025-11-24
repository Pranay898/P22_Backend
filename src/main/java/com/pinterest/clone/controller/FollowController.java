package com.pinterest.clone.controller;

import com.pinterest.clone.entity.User;
import com.pinterest.clone.service.FollowService;
import com.pinterest.clone.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class FollowController {

    @Autowired
    private FollowService followService;

    @PostMapping("/{id}/follow")
    public ResponseEntity<?> followUser(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        followService.followUser(userDetails.getId(), id);
        return ResponseEntity.ok().body("User followed successfully");
    }

    @DeleteMapping("/{id}/follow")
    public ResponseEntity<?> unfollowUser(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        followService.unfollowUser(userDetails.getId(), id);
        return ResponseEntity.ok().body("User unfollowed successfully");
    }

    @GetMapping("/{id}/followers")
    public ResponseEntity<Set<User>> getFollowers(@PathVariable Long id) {
        return ResponseEntity.ok(followService.getFollowers(id));
    }

    @GetMapping("/{id}/following")
    public ResponseEntity<Set<User>> getFollowing(@PathVariable Long id) {
        return ResponseEntity.ok(followService.getFollowing(id));
    }
}
