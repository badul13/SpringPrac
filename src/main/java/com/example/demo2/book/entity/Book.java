package com.example.demo2.book.entity;

import com.example.demo2.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "newbook")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Book(String title, User user) {
        this.title = title;
        this.user = user;
    }
}
