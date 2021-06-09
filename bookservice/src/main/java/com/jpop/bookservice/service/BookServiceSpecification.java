package com.jpop.bookservice.service;

import com.jpop.bookservice.model.Book;
import java.util.List;

public interface BookServiceSpecification {

    List<Book> getBook();

    Book getBook(Long id);

    Book saveBook(Book book);

    Book updateBook(Book book, Long id);

    void deleteBook(Long id);
}
