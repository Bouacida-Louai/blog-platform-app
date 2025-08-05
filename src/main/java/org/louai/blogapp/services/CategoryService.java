package org.louai.blogapp.services;

import org.louai.blogapp.domain.dtos.CategoryDto;
import org.louai.blogapp.domain.dtos.CreateCategoryRequest;
import org.louai.blogapp.domain.entities.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<Category> listCategories();
    Category createCategory(Category category);
    void deleteCategory(UUID id);
}
