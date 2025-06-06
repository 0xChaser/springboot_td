package com.chasercorp.demo.controller;

import com.chasercorp.demo.dto.AuthorResponseDTO;
import com.chasercorp.demo.dto.AuthorRequestDTO;
import com.chasercorp.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Authors")
@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Operation(summary = "Get All Authors")
    @ApiResponse(responseCode = "200", description = "Authors retrieved successfully")
    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @Operation(summary = "Create an Author")
    @ApiResponse(responseCode = "200", description = "Author created successfully")
    @PostMapping
    public ResponseEntity<AuthorResponseDTO> createAuthor(@Valid @RequestBody AuthorRequestDTO dto) {
        return ResponseEntity.ok(authorService.createAuthor(dto));
    }
}
