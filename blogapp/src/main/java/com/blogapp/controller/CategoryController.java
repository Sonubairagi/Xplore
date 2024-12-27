package com.blogapp.controller;

import com.blogapp.payload.CategoryDto;
import com.blogapp.service.CategoryService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    //http://localhost:8080/api/v1/category/addCategory
    @PostMapping("/addCategory")
    public ResponseEntity<CategoryDto> addCategory(
            @Valid @RequestBody CategoryDto categoryDto
    ){
        logger.info("Success! Entering Category object: {}", categoryDto);
        CategoryDto category = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/v1/category/deleteCategory/{categoryId}
    @DeleteMapping("/deleteCategory/{categoryId}")
    public ResponseEntity<String> deleteCategory(
            @PathVariable Long categoryId
    ){
        if(categoryId != null){
            logger.info("Successfully getting category id: {}",categoryId);
            String category = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(category, HttpStatus.OK);
        }else{
            logger.error("Failed! Category id is not getting: {}", categoryId);
            return new ResponseEntity<>("Category id not getting...", HttpStatus.BAD_REQUEST);
        }
    }

    //http://localhost:8080/api/v1/category/updateCategory/{categoryId}
    @PutMapping("/updateCategory/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable Long categoryId,
            @RequestBody CategoryDto categoryDto
    ){
        logger.info("Update category details: {}", categoryDto);
        CategoryDto category = null;
        if(categoryId != null){
            logger.info("Success! Updated Category id is: {}",categoryId);
            category = categoryService.updateCategory(categoryId,categoryDto);
            return new ResponseEntity<>(category, HttpStatus.OK);
        }else {
            logger.error("Failed! Category id is not correct: {}", categoryId);
            return new ResponseEntity<>(category, HttpStatus.BAD_REQUEST);
        }
    }

    //http://localhost:8080/api/v1/category
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategorys(){
        List<CategoryDto> categorys = categoryService.listOfCategorys();
        logger.info("Getting all the categorys objects: {}",categorys);
        return new ResponseEntity<>(categorys, HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/category/{categoryId}
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> findByCategoryId(
            @PathVariable Long categoryId
    ){
        CategoryDto categorys = null;
        if(categoryId != null){
            logger.info("Success! Get the category id is: {}",categoryId);
            categorys = categoryService.findCategorys(categoryId);
            return new ResponseEntity<>(categorys, HttpStatus.OK);
        }else{
            logger.error("Failed! Get the category id is not correct: {}", categoryId);
            return new ResponseEntity<>(categorys, HttpStatus.BAD_REQUEST);
        }

    }
}