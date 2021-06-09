package com.jpop.bookservice.controller;

import com.jpop.bookservice.dto.BookDto;
import com.jpop.bookservice.model.Book;
import com.jpop.bookservice.service.BookMapper;
import com.jpop.bookservice.service.BookServiceSpecification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.inject.Inject;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    @Inject
    BookServiceSpecification bookServiceSpecification;

    @Inject
    BookMapper bookMapper;

    @GetMapping("/")
    public ResponseEntity<List<BookDto>> getBook(){
        return ResponseEntity.ok(bookServiceSpecification.getBook().stream().map(bookMapper::convertToDto).collect(Collectors.toList()));
    }

    @GetMapping("/{book_id}")
    public ResponseEntity<BookDto> getBook(@PathVariable("book_id") Long id){
        return ResponseEntity.ok(bookMapper.convertToDto(bookServiceSpecification.getBook(id)));
    }

    @PostMapping("/")
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto book){
        Book newBook = bookServiceSpecification.saveBook(bookMapper.convertToEntity(book));
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("{book_id}")
                .buildAndExpand(newBook.getId())
                .toUri();
        return ResponseEntity.created(location).body(bookMapper.convertToDto(newBook));
    }

    @PutMapping("/{book_id}")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto book,@PathVariable("book_id") Long id){
        Book updateBook = bookServiceSpecification.updateBook(bookMapper.convertToEntity(book),id);
        return new ResponseEntity<>(bookMapper.convertToDto(updateBook), HttpStatus.OK);
    }

    @DeleteMapping("/{book_id}")
    public ResponseEntity<BookDto> deleteBook(@PathVariable("book_id") Long id){
        bookServiceSpecification.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.GONE);
    }
}
