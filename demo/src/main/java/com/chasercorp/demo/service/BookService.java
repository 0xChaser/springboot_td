package com.chasercorp.demo.service;

import com.chasercorp.demo.dto.BookRequestDTO;
import com.chasercorp.demo.dto.BookResponseDTO;
import com.chasercorp.demo.entity.Author;
import com.chasercorp.demo.entity.Book;
import com.chasercorp.demo.repository.AuthorRepository;
import com.chasercorp.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public BookResponseDTO createBook(BookRequestDTO dto) {
        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + dto.getAuthorId()));

        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setAuthor(author);

        Book saved = bookRepository.save(book);
        return responseDTO(saved);
    }

    public BookResponseDTO getBookById(String id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        return responseDTO(book);
    }

    public List<BookResponseDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::responseDTO)
                .collect(Collectors.toList());
    }

    public BookResponseDTO updateBook(String id, BookRequestDTO dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        if (dto.getTitle() != null) book.setTitle(dto.getTitle());
        if (dto.getIsbn() != null) book.setIsbn(dto.getIsbn());

        if (dto.getAuthorId() != null) {
            Author author = authorRepository.findById(dto.getAuthorId())
                    .orElseThrow(() -> new RuntimeException("Author not found with id: " + dto.getAuthorId()));
            book.setAuthor(author);
        }

        Book updated = bookRepository.save(book);
        return responseDTO(updated);
    }

    public void deleteBook(String id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    private BookResponseDTO responseDTO(Book book) {
        BookResponseDTO dto = new BookResponseDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setIsbn(book.getIsbn());
        dto.setAuthorName(book.getAuthor() != null ? book.getAuthor().getName() : null);
        return dto;
    }
}

