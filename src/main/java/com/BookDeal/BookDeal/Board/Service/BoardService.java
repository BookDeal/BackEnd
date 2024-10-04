package com.BookDeal.BookDeal.Board.Service;

import com.BookDeal.BookDeal.Board.Dto.BoardResponse;
import com.BookDeal.BookDeal.Board.Model.Board;
import com.BookDeal.BookDeal.Board.Respository.BoardRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Builder
@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;


    public BoardResponse select(Long boardId){

        Board board = boardRepository.findById(boardId)
                .orElseThrow(()-> new RuntimeException("존재하지 않는 글입니다."));

        BoardResponse boardResponse = BoardResponse.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .build();
        return boardResponse;

    }

    public List<BoardResponse> selectAll(){
        List<Board> boardList =boardRepository.findAll();

        List<BoardResponse> result=boardList.stream()
                .map(board -> select(board.getId()))
                .collect(Collectors.toList());
        return result;
    }
}
