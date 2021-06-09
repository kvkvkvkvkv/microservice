package com.jpop.bookservice.service;

import com.jpop.bookservice.exception.BookNotFoundException;
import com.jpop.bookservice.model.Book;
import com.jpop.bookservice.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImplementation implements BookServiceSpecification {

  BookRepository bookRepository;

  @Inject
  public BookServiceImplementation(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public List<Book> getBook() {
    return (List<Book>)bookRepository.findAll();
  }

  @Override
  public Book getBook(Long id) {
    return bookRepository.findById(id).orElseThrow(
        () -> new BookNotFoundException("Book is unavailable!"));
  }

  @Override
  public Book saveBook(Book book) {
    return bookRepository.save(book);
  }

  @Override
  public Book updateBook(Book book, Long id) {

    Optional<Book> updateBook = bookRepository.findById(id);
    if (updateBook.isPresent()) {
      Book update = updateBook.get();
      update.setName(book.getName());
      update.setAuthor(book.getAuthor());
      return update;
    }
    book.setId(id);
    return saveBook(book);
  }

  @Override
  public void deleteBook(Long id) {
    if (getBook(id) != null) {
      bookRepository.deleteById(id);
    }
  }
}
