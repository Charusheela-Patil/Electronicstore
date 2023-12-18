package com.electronicstore.electronicstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {



    private String categoryId;


    @NotBlank(message = "description is required")
    @Size(min = 4, message = "title must be of minimum 4 characters")
    private String title;


    @NotBlank
    private String description;

    @NotBlank
    private String categoryImage;
}
