package com.electronicstore.electronicstore.service.impl;

import com.electronicstore.electronicstore.dto.PageableResponse;
import com.electronicstore.electronicstore.dto.ProductDto;
import com.electronicstore.electronicstore.entity.Product;
import com.electronicstore.electronicstore.exception.ResourceNotFoundException;
import com.electronicstore.electronicstore.helper.Helper;
import com.electronicstore.electronicstore.repository.ProductRepo;
import com.electronicstore.electronicstore.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override

    public ProductDto create(ProductDto productDto) {

        Product product = modelMapper.map(productDto, Product.class);

        //product Id

        String productId = UUID.randomUUID().toString();
        product.setProductId(productId);
        //added date
        product.setAddedDate(new Date());
        Product saveProduct = productRepository.save(product);
        return modelMapper.map(saveProduct,ProductDto.class);
    }

    @Override
    public ProductDto update(ProductDto productDto, String productId) {

        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found of given Id "));
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setProductId(productDto.getProductId());
        product.setPrice(productDto.getPrice());
        product.setDiscountedPrice(productDto.getDiscountedPrice());
        product.setQuantity(productDto.getQuantity());
        product.setLive(productDto.isLive());
        product.setStock(productDto.isStock());
        product.setProductImageName(productDto.getProductImageName());
        Product updatedProduct = productRepository.save(product);

        return modelMapper.map(updatedProduct,ProductDto.class);
    }

    @Override
    public void delete(String productId) {

        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found of given Id "));
        productRepository.delete(product);

    }

    @Override
    public ProductDto get(String productId) {

        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found of given Id "));


        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAll(int pageNumber,int pageSize,String sortBy,String sortDirection) {

        Sort sort=(sortDirection.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> page = productRepository.findAll(pageable);
        return Helper.getPageableResponse(page,ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAllLive(int pageNumber,int pageSize,String sortBy,String sortDirection) {

        Sort sort=(sortDirection.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> page = productRepository.findByLiveTrue(pageable);
        return Helper.getPageableResponse(page,ProductDto.class);

    }

    @Override
    public PageableResponse<ProductDto> searchByTitle(String subTitle,int pageNumber,int pageSize,String sortBy,String sortDirection) {

        Sort sort=(sortDirection.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> page = productRepository.findByTitleContaining(subTitle,pageable);
        return Helper.getPageableResponse(page,ProductDto.class);


    }
}
