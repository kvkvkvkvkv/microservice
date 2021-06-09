package com.jpop.bookservice.service;

import com.jpop.bookservice.dto.BookDto;
import com.jpop.bookservice.model.Book;
import javax.inject.Inject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {

  ModelMapper modelMapper;

  @Inject
  public BookMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public BookDto convertToDto(Book book) {
    return modelMapper.map(book, BookDto.class);
  }

  public Book convertToEntity(BookDto bookDto) {
    return modelMapper.map(bookDto, Book.class);
  }
}
