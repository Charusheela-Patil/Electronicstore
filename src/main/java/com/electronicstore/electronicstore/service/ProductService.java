package com.electronicstore.electronicstore.service;

import com.electronicstore.electronicstore.dto.PageableResponse;
import com.electronicstore.electronicstore.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto create(ProductDto productDto);

    //update
    ProductDto update(ProductDto productDto,String productId);

    //delete
    void delete(String productId);

    //get single
    ProductDto get(String productId);

    //get all
    PageableResponse<ProductDto> getAll(int pageNumber,int pageSize,String sortBy,String sortDirection );

    //get all : live
    PageableResponse<ProductDto>getAllLive(int pageNumber,int pageSize,String sortBy,String sortDirection);

    //search product
    PageableResponse<ProductDto> searchByTitle(String subTitle,int pageNumber,int pageSize,String sortBy,String sortDirection);


}
