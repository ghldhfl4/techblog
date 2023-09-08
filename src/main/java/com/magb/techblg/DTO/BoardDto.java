package com.magb.techblg.DTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BoardDto {
    private int boardno;        // PK
    private String title;        // 제목
    private String content;      // 내용
    private String writer;       // 작성자
    private LocalDateTime createdDate;

    @Builder
    public BoardDto(int boardno, String title, String content, String writer, LocalDateTime createdDate) {
        this.boardno = boardno;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }
}
