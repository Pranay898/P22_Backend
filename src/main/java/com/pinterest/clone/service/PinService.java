package com.pinterest.clone.service;

import com.pinterest.clone.entity.Board;
import com.pinterest.clone.entity.Pin;
import com.pinterest.clone.entity.User;
import com.pinterest.clone.repository.BoardRepository;
import com.pinterest.clone.repository.PinRepository;
import com.pinterest.clone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PinService {
    @Autowired
    PinRepository pinRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    public List<Pin> getAllPins() {
        return pinRepository.findAll();
    }

    public List<Pin> getPinsByUserId(Long userId) {
        return pinRepository.findByUserId(userId);
    }

    public Pin createPin(Pin pin, Long userId, Long boardId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        pin.setUser(user);
        pin.setCreatedAt(LocalDateTime.now());

        if (boardId != null) {
            Board board = boardRepository.findById(boardId).orElse(null);
            pin.setBoard(board);
        }

        return pinRepository.save(pin);
    }

    public Pin getPinById(Long id) {
        return pinRepository.findById(id).orElseThrow(() -> new RuntimeException("Pin not found"));
    }
    
    public List<Pin> searchPins(String query) {
        return pinRepository.findByTitleContainingIgnoreCase(query);
    }
    
    @Transactional
    public void deletePin(Long id, Long userId) {
        Pin pin = getPinById(id);
        if (!pin.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized to delete this pin");
        }
        pinRepository.delete(pin);
    }
}
