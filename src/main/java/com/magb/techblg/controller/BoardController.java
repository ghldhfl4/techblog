package com.magb.techblg.controller;

import com.magb.techblg.domain.Board;
import com.magb.techblg.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@Slf4j
@RequiredArgsConstructor // 필요한 필드에 대해서 생성자 생성
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping("/board/list")
    public String getBoardList(Model model, @PageableDefault(page = 0, size = 10, sort = "boardno", direction = Sort.Direction.DESC) Pageable pageable,
    String keyword, String searchType){
        Page<Board> boardList = null;
//        List<Board> boardList = null;

        if(keyword == null){
            log.debug("board created date = {}",keyword);
            boardList = boardService.getBoardList(pageable); // find와 get 차이점을 찾거나 통일할것
            // DDD의 성격을 반영할때 find로 네이밍
            model.addAttribute("boardList", boardList);
        }else {
            boardList = boardService.getBoardSearchList(pageable, keyword, searchType);
            model.addAttribute("boardList", boardList);
            log.debug("boardElastics = {}", boardList);
        }
        int nowPage = boardList.getPageable().getPageNumber()+1;
        log.debug("boardList = {}", boardList);

        model.addAttribute("nowPage", nowPage);
        return "/board/list";
    }

    @GetMapping("/board/view")
    public String getBoardView(@RequestParam int boardno, Model model) {
        Board board = boardService.findBoardByBoardno(boardno);
        model.addAttribute("board", board);
        return "board/view";
    }
    @GetMapping("/board/write")
    public String getBoardWrite(){
        return "/board/write";
    }

    @PostMapping("/board/writedo")
    public String postBoardWritedo(Board board){

        Board boardWrite = boardService.writeBoard(board);
        System.out.println("boardWrite : " + boardWrite.getContent());
        return "redirect:/board/list";
    }

    @GetMapping("/board/update")
    public String getBoardUpdate(@RequestParam int boardno, Model model){

        Board board = boardService.findBoardByBoardno(boardno);
        model.addAttribute("board", board);

        return "/board/update";
    }

    @PostMapping("/board/updatedo")
    public String getBoardUpdatedo(Board board){
        System.out.println("board title : " + board.getTitle());
        System.out.println("board no : " + board.getBoardno());
        int boardupdate = boardService.updateBoard(board);
        System.out.println("boardupdate : " + boardupdate);
        return "redirect:/board/view?boardno=" + board.getBoardno();
    }

    @PostMapping("/board/deletedo")
    public String deleteBoard(@RequestParam(required = true) int boardno) {
        System.out.println("delete boardno : " + boardno);
        boardService.deleteBoard(boardno);
        return "redirect:/board/list";
    }
}
