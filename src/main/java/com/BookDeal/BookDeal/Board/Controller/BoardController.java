package com.BookDeal.BookDeal.Board.Controller;

import com.BookDeal.BookDeal.Board.Dto.BoardResponse;
import com.BookDeal.BookDeal.Board.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/viewAll")
    public ResponseEntity viewAll(){
        List<BoardResponse> boardList = boardService.selectAll();

        return ResponseEntity.status(HttpStatus.OK).body(boardList);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity viewId(@PathVariable("id") Long id) {
        try {
            BoardResponse boardResponse = boardService.select(id);
            return ResponseEntity.ok(boardResponse); // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
