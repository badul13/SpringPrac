package com.example.demo2.service;

import com.example.demo2.base.GeneralException;
import com.example.demo2.base.constant.Code;
import com.example.demo2.DTO.BookDTO;
import com.example.demo2.entity.Book;
import com.example.demo2.repository.BookRepository;
import com.example.demo2.entity.User;
import com.example.demo2.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookDTO createBook(BookDTO bookDto) {
        User user = userRepository.findById(bookDto.getUserId())
                .orElseThrow(() -> new GeneralException(Code.NOT_FOUND));

        Book book = Book.builder()
                .title(bookDto.getTitle())
                .user(user)
                .build();
        bookRepository.save(book);
        return bookDto;
    }

    public BookDTO getBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new GeneralException(Code.NOT_FOUND));
        return new BookDTO(book);
    }

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(BookDTO::new)
                .toList();
    }
    //얜일단 예외처리보류

    @Transactional
    public BookDTO updateBook(Long id, BookDTO bookDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new GeneralException(Code.NOT_FOUND));

        book.setTitle(bookDto.getTitle());
        return new BookDTO(book);
    }

    @Transactional
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new GeneralException(Code.NOT_FOUND));
        bookRepository.delete(book);
    }


}
