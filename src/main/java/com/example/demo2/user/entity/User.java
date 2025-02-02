package com.example.demo2.user.entity;

import com.example.demo2.book.entity.Book;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "new_user")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    //유저 삭제시 책도 같이 삭제랑 부모없어지면 자식(고아)도 없애주는거
    private List<Book> books = new ArrayList<>();

    @Builder
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
