package com.chasercorp.demo.service;

import com.chasercorp.demo.dto.AuthorResponseDTO;
import com.chasercorp.demo.dto.AuthorRequestDTO;
import com.chasercorp.demo.entity.Author;
import com.chasercorp.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorResponseDTO> getAllAuthors() {
        return authorRepository.findAll().stream().map(author -> {
            AuthorResponseDTO dto = new AuthorResponseDTO();
            dto.setId(author.getId());
            dto.setName(author.getName());
            return dto;
        }).collect(Collectors.toList());
    }

    public AuthorResponseDTO createAuthor(AuthorRequestDTO dto) {
        Author author = new Author();
        author.setName(dto.getName());
    
        Author saved = authorRepository.save(author);
    
        AuthorResponseDTO response = new AuthorResponseDTO();
        response.setId(saved.getId());
        response.setName(saved.getName());
    
        return response;
    }
}
