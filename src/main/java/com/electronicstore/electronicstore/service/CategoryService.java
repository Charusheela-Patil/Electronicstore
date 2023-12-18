package com.electronicstore.electronicstore.service;

import com.electronicstore.electronicstore.dto.CategoryDto;
import com.electronicstore.electronicstore.dto.PageableResponse;

public interface CategoryService {

    //create

    CategoryDto create(CategoryDto categoryDto);

    //update

    CategoryDto update(CategoryDto categoryDto,String categoryId);

    //delete

    void delete(String categoryId);

    //get all

    PageableResponse<CategoryDto> getAll(int pageNumber,int pageSize,String sortBy, String sortDirection);

    //get single category detail

    CategoryDto get(String categoryId);


    //search
}
