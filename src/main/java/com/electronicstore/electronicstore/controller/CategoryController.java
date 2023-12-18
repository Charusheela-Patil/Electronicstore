package com.electronicstore.electronicstore.controller;

import com.electronicstore.electronicstore.dto.ApiResponse;
import com.electronicstore.electronicstore.dto.CategoryDto;
import com.electronicstore.electronicstore.dto.PageableResponse;
import com.electronicstore.electronicstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    /**
     * @author CharushilaPatil
     * @apiNote To create a category Data
     * @param categoryDto
     * @since 1.0v
     */
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto categoryDto1 = this.categoryService.create(categoryDto);

        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }


    /**
     * @author charushilaPatil
     * @apiNote Update category data in database
     * @param categoryDto
     * @param categoryId
     * @return
     * @since 1.0v
     * */

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable String categoryId, @RequestBody CategoryDto categoryDto){


        CategoryDto updateCategory = categoryService.update(categoryDto, categoryId);

        return new ResponseEntity<>(updateCategory,HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
     public ResponseEntity<ApiResponse> deleteCategory(@PathVariable String categoryId){
      categoryService.delete(categoryId);
        ApiResponse responseMessage= ApiResponse.builder().message("Category is deleted successfully").status(HttpStatus.OK).success(true).build();
        return new ResponseEntity<>(responseMessage,HttpStatus.OK);
    }

    @GetMapping("/")
      public ResponseEntity<PageableResponse<CategoryDto>> getAll(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "0",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy,
            @RequestParam(value ="sortDirection",defaultValue = "asc",required = false) String sortDirection){


        PageableResponse<CategoryDto> pageableResponse = categoryService.getAll(pageNumber, pageSize, sortBy, sortDirection);

        return new ResponseEntity<>(pageableResponse,HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getsingle(@PathVariable String categoryId){

        CategoryDto categoryDto = categoryService.get(categoryId);
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);

    }



}
