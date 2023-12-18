package com.electronicstore.electronicstore.repository;

import com.electronicstore.electronicstore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,String>{
}

