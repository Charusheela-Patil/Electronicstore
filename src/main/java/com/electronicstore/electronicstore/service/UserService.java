package com.electronicstore.electronicstore.service;
//import com.icwd.electronic.store.dto.PageableResponse;
//import com.icwd.electronic.store.dto.UserDto;
//import org.springframework.stereotype.Service;
import com.electronicstore.electronicstore.dto.PageableResponse;
import com.electronicstore.electronicstore.dto.UserDto;
import com.electronicstore.electronicstore.entity.User;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface UserService {

   // public User create_user(User user);
        public UserDto createUser(UserDto userDto);

        public UserDto updateUser(UserDto userDto, String userid);

        public void deleteUser(String userid);

        public PageableResponse<UserDto> getAllUser (int pageNumber, int pageSize, String sortBy , String sortDir);

        public UserDto getUserById(String userid);

        public  UserDto getUserByEmail (String email);

        public List<UserDto> searchUser(String pattern);




}
