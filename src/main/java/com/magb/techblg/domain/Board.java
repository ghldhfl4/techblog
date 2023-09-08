package com.magb.techblg.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Board {

    @Id
    private int boardno;        // PK
    private String title;        // 제목
    private String content;      // 내용
    private String writer;       // 작성자
    private LocalDateTime modifiedDate;
    @CreatedDate
    private LocalDateTime createdDate; // DB의 table에서는 created_date로 매핑됨

}
