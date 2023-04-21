package com.shop3.shop.Service;

import com.shop3.shop.Entity.Category;
import com.shop3.shop.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category createCategory(Category category){
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        return categoryRepository.save(newCategory);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }

    public List<Category> getCategoryList(){
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategory(Long id){
        return categoryRepository.findById(id);
    }
}
