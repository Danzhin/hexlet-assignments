package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookMapper mapper;

    public BookDTO create(BookCreateDTO dto) {
        var author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new ConstraintViolationException("", Collections.emptySet()));
        var book = mapper.map(dto);
        bookRepository.save(book);
        return mapper.map(book);
    }

    public List<BookDTO> readAll() {
        var books = bookRepository.findAll();
        return books.stream().map(mapper::map).toList();
    }

    public BookDTO readById(Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(""));
        return mapper.map(book);
    }

    public BookDTO update(Long id, BookUpdateDTO dto) {
        var author = authorRepository.findById(dto.getAuthorId().get())
                .orElseThrow(() -> new ConstraintViolationException("", Collections.emptySet()));
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(""));
        mapper.update(dto, book);
        bookRepository.save(book);
        return mapper.map(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
    // END
}
