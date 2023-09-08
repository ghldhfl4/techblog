package com.magb.techblg;

import com.magb.techblg.domain.Board;
import com.magb.techblg.repository.BoardRepository;
import com.magb.techblg.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardApplicationTests {

	@Autowired
	BoardService boardService;

	@Autowired
	BoardRepository boardRepository;

	@Test
	void update() {
		Board board = new Board();
		board.setBoardno(1);
		board.setTitle("test");
		board.setContent("test");
		board.setWriter("test");
		board = boardService.writeBoard(board);
		System.out.println("생성된 게시글 ID : " + board.getTitle());
	}

	// 페이지네이션 테스트용 게시글 더미데이터 생성
	@Test
	void forUpdate() {
		Board board = new Board();
		for(int i = 0; i < 100; i++){
			board.setBoardno(i);
			board.setTitle("test" + i);
			board.setContent("test" + i);
			board.setWriter("test" + i);
			board = boardService.writeBoard(board);
			System.out.println("생성된 게시글 ID : " + board.getTitle());
		}

	}

}
