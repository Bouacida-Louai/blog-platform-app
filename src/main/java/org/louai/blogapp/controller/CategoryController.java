package org.louai.blogapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.louai.blogapp.domain.dtos.CategoryDto;
import org.louai.blogapp.domain.dtos.CreateCategoryRequest;
import org.louai.blogapp.domain.entities.Category;
import org.louai.blogapp.mappers.CategoryMapper;
import org.louai.blogapp.services.CategoryService;
import org.louai.blogapp.services.impl.CategoryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static java.util.Arrays.stream;

@RestController()
@RequestMapping(path = ("/api/v1/categories"))
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listeCateories(){
        List<CategoryDto> categories =categoryService.listCategories().stream()
                .map(categoryMapper::toDto)
                .toList();
        return ResponseEntity.ok(categories);

    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(
             @Valid @RequestBody CreateCategoryRequest createCategoryRequest){

        Category categoryToCreate =categoryMapper.toEntity(createCategoryRequest);
        Category savedCategory = categoryService.createCategory(categoryToCreate);

        return new ResponseEntity<>(
                categoryMapper.toDto(savedCategory), HttpStatus.CREATED);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
