package com.example.demo2.user.dto;

import com.example.demo2.book.dto.BookDTO;
import com.example.demo2.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotBlank(message = "이름은 필수 작성 사항입니다.")
    private String name;
    @NotBlank(message = "이메일은 필수 작성 사항입니다.")
    @Email(message = "이메일 양식에 맞지 않습니다.")
    private String email;
    private List<BookDTO> books;

    public UserDTO(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.books = user.getBooks().stream()
                .map(BookDTO::new)
                .collect(Collectors.toList());
    }
}
