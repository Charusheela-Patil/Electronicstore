package com.electronicstore.electronicstore.service.impl;

import com.electronicstore.electronicstore.helper.Helper;
import com.electronicstore.electronicstore.dto.CategoryDto;
import com.electronicstore.electronicstore.dto.PageableResponse;
import com.electronicstore.electronicstore.entity.Category;
import com.electronicstore.electronicstore.exception.ResourceNotFoundException;
import com.electronicstore.electronicstore.repository.CategoryRepo;
import com.electronicstore.electronicstore.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
     private CategoryRepo categoryRepository;

    @Autowired
     private ModelMapper modelMapper;


    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category saveCategory = categoryRepository.save(category);
        return modelMapper.map(saveCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, String categoryId)
    {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found exception"));

        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryImage(categoryDto.getCategoryImage());
        Category updateCategory = categoryRepository.save(category);
        return modelMapper.map(updateCategory,CategoryDto.class);
    }

    @Override
    public void delete(String categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found exception"));

        categoryRepository.delete(category);

    }

    @Override
    public PageableResponse<CategoryDto> getAll(int pageNumber,int pageSize,String sortBy, String sortDirection)
    {
        Sort sort= ( sortDirection.equalsIgnoreCase("desc"))
                ?(Sort.by(sortBy).descending())
                :(Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);

        Page<Category> category = categoryRepository.findAll(pageable);

        PageableResponse<CategoryDto> response = Helper.getPageableResponse(category, CategoryDto.class);

        return response;
    }

    @Override
    public CategoryDto get(String categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found exception"));

        return modelMapper.map(category,CategoryDto.class);
    }
}
