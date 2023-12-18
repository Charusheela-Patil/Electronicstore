package com.electronicstore.electronicstore.helper;

import com.electronicstore.electronicstore.dto.PageableResponse;
import com.electronicstore.electronicstore.dto.UserDto;
import com.electronicstore.electronicstore.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class Helper {

    public static <U,  V>PageableResponse<V> getPageableResponse(Page<U>page,Class<V>type){
        List<U> users = page.getContent();
        List<V> userDtoList = users.stream().map(object ->new ModelMapper().map(object ,type)).collect(Collectors.toList());

        PageableResponse<V> response = new PageableResponse();
        response.setContent(userDtoList);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElement(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());
        return response;
    }
}
