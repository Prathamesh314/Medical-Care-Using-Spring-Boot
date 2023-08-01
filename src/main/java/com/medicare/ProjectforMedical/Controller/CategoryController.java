package com.medicare.ProjectforMedical.Controller;

import com.medicare.ProjectforMedical.Dto.CategoryRequest;
import com.medicare.ProjectforMedical.Dto.CategoryResponse;
import com.medicare.ProjectforMedical.Model.Category;
import com.medicare.ProjectforMedical.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.hibernate.id.IntegralDataTypeHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // create
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String createCategory(@RequestBody CategoryRequest categoryRequest){
        categoryService.createCategory(categoryRequest);
        return "Category is Created";
    }

    // get
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResponse> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{categoryID}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponse getCategoryById(@PathVariable Integer categoryID){
        return categoryService.getCategoryById(categoryID);
    }

    // put
    @PutMapping("/{categoryID}")
    @ResponseStatus(HttpStatus.OK)
    public String updateCategory(@RequestBody CategoryRequest categoryRequest,@PathVariable Integer categoryID){
        categoryService.updateCategory(categoryRequest,categoryID);
        return "Category has been updated Successfully";
    }

    // delete
    @DeleteMapping("/{categoryID}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteCategory(@PathVariable Integer categoryID){
        categoryService.deleteCategory(categoryID);
        return "Category has been deleted successfully";
    }


}
