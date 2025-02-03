package com.example.demo2.book.dto;

import com.example.demo2.book.entity.Book;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    @NotBlank(message = "제목은 필수 작성 사항입니다.")
    private String title;
    @NotBlank(message = "유저 아이디는 필수 작성 사항입니다.")
    private Long userId;

    public BookDTO(Book book) {
        this.title = book.getTitle();
        this.userId = book.getUser().getId();
    }
}
