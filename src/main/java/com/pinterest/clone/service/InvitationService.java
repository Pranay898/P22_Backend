package com.pinterest.clone.service;

import com.pinterest.clone.entity.Invitation;
import com.pinterest.clone.entity.User;
import com.pinterest.clone.repository.InvitationRepository;
import com.pinterest.clone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InvitationService {

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Invitation> getMyInvitations(Long userId) {
        return invitationRepository.findByReceiverId(userId);
    }

    @Transactional
    public Invitation createInvitation(Long senderId, Long receiverId, String type, String message) {
        User sender = userRepository.findById(senderId).orElseThrow(() -> new RuntimeException("Sender not found"));
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        Invitation invitation = new Invitation();
        invitation.setSender(sender);
        invitation.setReceiver(receiver);
        invitation.setType(type);
        invitation.setMessage(message);
        invitation.setStatus("PENDING");

        return invitationRepository.save(invitation);
    }

    @Transactional
    public Invitation respondToInvitation(Long invitationId, String status) {
        Invitation invitation = invitationRepository.findById(invitationId)
                .orElseThrow(() -> new RuntimeException("Invitation not found"));

        invitation.setStatus(status);
        return invitationRepository.save(invitation);
    }
}
