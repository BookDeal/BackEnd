package com.BookDeal.BookDeal.Board.Respository;

import com.BookDeal.BookDeal.Board.Model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
}
