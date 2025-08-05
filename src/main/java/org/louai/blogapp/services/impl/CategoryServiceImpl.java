package org.louai.blogapp.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.louai.blogapp.domain.entities.Category;
import org.louai.blogapp.repositories.CategoryRepository;
import org.louai.blogapp.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private  final CategoryRepository categoryRepository;

    @Override
    public List<Category> listCategories() {

        return  categoryRepository.findAllWithPostCount();
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {

        if (categoryRepository.existsByNameIgnoreCase(category.getName())){
            throw new IllegalArgumentException("category already exist with name "+ category.getName());
        }
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(UUID id) {
        Optional<Category> category =categoryRepository.findById(id);
        if (category.isPresent()){
            if (!category.get().getPosts().isEmpty()){
                throw new IllegalStateException("category has posts associated with it");
            }
            categoryRepository.deleteById(id);
        }



}
}
