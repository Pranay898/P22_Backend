package com.pinterest.clone.controller;

import com.pinterest.clone.entity.Board;
import com.pinterest.clone.service.BoardService;
import com.pinterest.clone.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
@CrossOrigin(origins = "*")
public class BoardController {
    @Autowired
    BoardService boardService;

    @GetMapping("/user/{userId}")
    public List<Board> getUserBoards(@PathVariable Long userId) {
        return boardService.getBoardsByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<Board> createBoard(@RequestBody Board board, 
                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(boardService.createBoard(board, userDetails.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.getBoardById(id));
    }
}
