package com.BookDeal.BookDeal.Board.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
@AllArgsConstructor
public class BoardResponse {
//    private Long userId;
//    private Long boardId;
    private String title;
    private String content;
    private String writer;


}
