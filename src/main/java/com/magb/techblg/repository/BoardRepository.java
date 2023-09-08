package com.magb.techblg.repository;

import com.magb.techblg.domain.Board;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

    String UPDATE_BOARD = "UPDATE Board b " +
            "SET b.title = :#{#board.title}, " +
            "b.content = :#{#board.content} " +
//            "b.modifiedDate = NOW() " +
            "WHERE b.boardno = :#{#board.boardno}";
    String SEARCH_BOARD = "select b from Board b where " +
            "b.title like %:keyword%";
//            "b.content like %:keyword%";

    Board findBoardByBoardno(int boardno);

    Page<Board> findByTitleContaining(Pageable pageable, String keyword);
    Page<Board> findByContentContaining(Pageable pageable, String keyword);


    @Transactional
    @Modifying // (clearAutomatically = true)
    @Query(value = UPDATE_BOARD) // nativeQuery라는 속성을 이용하여 JPQL로 작성한 것인지 SQL로 작성한 것인지를 구분
    int updateBoard(@Param("board") Board board);


    @Transactional
    @Query(value = SEARCH_BOARD) // nativeQuery 사용 비추천
    Page<Board> findByKeywordContaining(@Param("keyword") String keyword, Pageable pageable);
    // querydsl을 사용해서 jpql 테스트
    // DBMS에서 비즈니스 로직은 지양할것

    //    메소드 시간측정
//    StopWatch stopWatch = new StopWatch();
//	stopWatch.start();
//    logic();
//	stopWatch.stop();
}
