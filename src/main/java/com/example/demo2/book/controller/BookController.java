package com.example.demo2.book.controller;

import com.example.demo2.base.constant.Code;
import com.example.demo2.base.dto.DataResponseDTO;
import com.example.demo2.base.dto.ResponseDTO;
import com.example.demo2.book.dto.BookDTO;
import com.example.demo2.book.dto.BookIDRequestDTO;
import com.example.demo2.book.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

//    @PostMapping
//    public ResponseDTO createBook(@RequestBody BookDTO bookDto) {
//        bookService.createBook(bookDto);
//        return ResponseDTO.of(true, Code.OK);
//    }

    @PostMapping
    public ResponseEntity<DataResponseDTO<BookDTO>> createBook(@Valid @RequestBody BookDTO bookDto) {
        BookDTO createdBook = bookService.createBook(bookDto);
        return ResponseEntity.ok(DataResponseDTO.of(createdBook));
    }


    @PostMapping("/findbook")
    public DataResponseDTO<BookDTO> getBook(@RequestBody BookIDRequestDTO request) {
        return DataResponseDTO.of(bookService.getBook(request.getId()));
    }

    @GetMapping("/all")
    public ResponseEntity<DataResponseDTO<List<BookDTO>>> getAllBooks() {
        List<BookDTO> books = bookService.getAllBooks();
        return ResponseEntity.ok(DataResponseDTO.of(books));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponseDTO<BookDTO>> updateBook(@PathVariable Long id, @Valid @RequestBody BookDTO bookDto) {
        BookDTO updatedBook = bookService.updateBook(id, bookDto);
        return ResponseEntity.ok(DataResponseDTO.of(updatedBook));
    }

    @DeleteMapping("/{id}")
    public ResponseDTO deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseDTO.of(true, Code.OK);
    }
}
