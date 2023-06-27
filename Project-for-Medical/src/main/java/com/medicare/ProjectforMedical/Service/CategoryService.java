package com.medicare.ProjectforMedical.Service;

import com.medicare.ProjectforMedical.Dto.CategoryRequest;
import com.medicare.ProjectforMedical.Dto.CategoryResponse;
import com.medicare.ProjectforMedical.Model.Category;
import com.medicare.ProjectforMedical.Repository.CategoryRepository;
import com.medicare.ProjectforMedical.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void createCategory(CategoryRequest categoryRequest){
        Category category = Category.builder()
                .name(categoryRequest.getName())
                .description(categoryRequest.getDescription())
                .build();
        categoryRepository.save(category);
    }

    public List<CategoryResponse> getAllCategories(){
        return categoryRepository.findAll().stream().map(this::MapToResponse).toList();
    }

    public CategoryResponse getCategoryById(Integer id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category","ID",id));
        return MapToResponse(category);
    }

    public void updateCategory(CategoryRequest categoryRequest,int id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category","ID",id));
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());
        categoryRepository.save(category);
    }

    public void deleteCategory(int id){
        categoryRepository.deleteById(id);
    }

    public CategoryResponse MapToResponse(Category category){
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

}
