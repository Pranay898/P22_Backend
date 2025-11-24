package com.pinterest.clone.controller;

import com.pinterest.clone.entity.Invitation;
import com.pinterest.clone.service.InvitationService;
import com.pinterest.clone.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/invitations")
public class InvitationController {

    @Autowired
    private InvitationService invitationService;

    @GetMapping
    public ResponseEntity<List<Invitation>> getMyInvitations(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(invitationService.getMyInvitations(userDetails.getId()));
    }

    @PostMapping
    public ResponseEntity<Invitation> createInvitation(@RequestBody Map<String, Object> payload,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long receiverId = Long.valueOf(payload.get("receiverId").toString());
        String type = (String) payload.get("type");
        String message = (String) payload.get("message");

        return ResponseEntity.ok(invitationService.createInvitation(userDetails.getId(), receiverId, type, message));
    }

    @PutMapping("/{id}/respond")
    public ResponseEntity<Invitation> respondToInvitation(@PathVariable Long id,
            @RequestBody Map<String, String> payload) {
        String status = payload.get("status");
        return ResponseEntity.ok(invitationService.respondToInvitation(id, status));
    }
}
