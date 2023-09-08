package com.magb.techblg.service;

import com.magb.techblg.domain.Board;
import com.magb.techblg.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public Page<Board> getBoardList(Pageable pageable){
        Page<Board> boardList;
        return boardList = boardRepository.findAll(pageable);
    }
    public Board writeBoard(Board board){
        Board boardWrite;

        return boardWrite = boardRepository.save(board);
    }

    public int updateBoard(Board board){
        int boardUpdate;
        System.out.println("service board title : " + board.getTitle());
        return boardUpdate = boardRepository.updateBoard(board);
    }

    public Board findBoardByBoardno(int boardno) {
        Board boardWrite;

        return boardWrite = boardRepository.findBoardByBoardno(boardno);
    }

    public void deleteBoard(int boardno) {
        boardRepository.deleteById(boardno);
    }

    public Page<Board> getBoardSearchList(Pageable pageable, String keyword, String searchType) {
        Page<Board> boardList;
        if("title".equals(searchType)){
            return boardList = boardRepository.findByTitleContaining(pageable, keyword);
        } else if("content".equals(searchType)) {
            return boardList = boardRepository.findByContentContaining(pageable, keyword);
        } else{
            log.debug("search title or content = {}",keyword);
            return boardList = boardRepository.findByKeywordContaining(keyword, pageable);
        }
    }
}
