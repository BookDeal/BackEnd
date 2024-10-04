package com.BookDeal.BookDeal.Board.Service;


import com.BookDeal.BookDeal.Board.Dto.BoardResponse;
import com.BookDeal.BookDeal.Board.Model.Board;
import com.BookDeal.BookDeal.Board.Respository.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BoardServiceTest {

    @InjectMocks
    private BoardService boardService; // 테스트 대상 클래스

    @Mock
    private BoardRepository boardRepository; // Mock 객체

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Mock 초기화
    }

    @Test
    void select_ExistingBoard_ReturnsBoardResponse() {
        // given: Mock이 반환할 값 정의
        Long boardId = 1L;
        Board mockBoard = new Board();
        mockBoard.setId(boardId);
        mockBoard.setTitle("Test Title");
        mockBoard.setContent("Test Content");
        mockBoard.setWriter("Test Writer");

        when(boardRepository.findById(boardId)).thenReturn(Optional.of(mockBoard)); // Mock의 동작 정의

        // when: select 메서드 호출
        BoardResponse boardResponse = boardService.select(boardId);

        // then: 결과 검증
        assertNotNull(boardResponse);
        assertEquals("Test Title", boardResponse.getTitle());
        assertEquals("Test Content", boardResponse.getContent());
        assertEquals("Test Writer", boardResponse.getWriter());
    }

    @Test
    void select_NonExistingBoard_ThrowsException() {
        // given: 존재하지 않는 boardId 정의
        Long nonExistingBoardId = 999L;

        when(boardRepository.findById(nonExistingBoardId)).thenReturn(Optional.empty()); // Mock의 동작 정의

        // when & then: 예외 발생 검증
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            boardService.select(nonExistingBoardId);
        });

        assertEquals("존재하지 않는 글입니다.", thrown.getMessage());
    }

    @Test
    void selectAll() {
        //given
        Long boardId = 1L;
        Board mockBoard = new Board();
        mockBoard.setId(boardId);
        mockBoard.setTitle("Test Title");
        mockBoard.setContent("Test Content");
        mockBoard.setWriter("Test Writer");

        Long boardId2 = 2L;
        Board mockBoard2 = new Board();
        mockBoard2.setId(boardId2);
        mockBoard2.setTitle("Test Title2");
        mockBoard2.setContent("Test Content2");
        mockBoard2.setWriter("Test Writer2");

        List<Board> mockBoardList=new ArrayList<>();
        mockBoardList.add(mockBoard);
        mockBoardList.add(mockBoard2);
        //when
        when(boardRepository.findById(boardId)).thenReturn(Optional.of(mockBoard));
        when(boardRepository.findById(boardId2)).thenReturn(Optional.of(mockBoard2));
        when(boardRepository.findAll()).thenReturn(mockBoardList);
        //then
        List<BoardResponse> boardResponses = boardService.selectAll();

        BoardResponse firstResponse = boardResponses.get(0);
        assertEquals("Test Title", firstResponse.getTitle());
        assertEquals("Test Content", firstResponse.getContent());
        assertEquals("Test Writer", firstResponse.getWriter());

        BoardResponse secondResponse = boardResponses.get(1);
        assertEquals("Test Title2", secondResponse.getTitle());
        assertEquals("Test Content2", secondResponse.getContent());
        assertEquals("Test Writer2", secondResponse.getWriter());

    }
}
