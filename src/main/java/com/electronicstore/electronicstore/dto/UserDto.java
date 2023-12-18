package com.electronicstore.electronicstore.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String userid;



    @Size(min = 4, max = 30,message = "Username must be min of 4 characters")
    private String name;


    @Email(message = "Enter valid Email")
    private String email;

    @NotEmpty
    @Size(min = 6,max = 10,message = "Enter correct password")
    private String password;

    @Size(min = 4,max = 6)
    private String gender;

    @NotBlank(message="The message is required")
    private String about;

    private String Image_name;




}

