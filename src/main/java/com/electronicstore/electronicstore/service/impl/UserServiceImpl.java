package com.electronicstore.electronicstore.service.impl;
import com.electronicstore.electronicstore.constants.AppConstants;

import com.electronicstore.electronicstore.dto.UserDto;
import com.electronicstore.electronicstore.exception.ResourceNotFoundException;
import com.electronicstore.electronicstore.helper.Helper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.electronicstore.electronicstore.dto.PageableResponse;
import com.electronicstore.electronicstore.entity.User;
import com.electronicstore.electronicstore.service.UserService;

import com.electronicstore.electronicstore.repository.userRepo;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private userRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("Entering Dao Call For Save or Create The UserData");
        String string = UUID.randomUUID().toString();
        userDto.setUserid(string);
        User user = this.modelMapper.map(userDto, User.class);
        User save = this.userRepo.save(user);
        log.info("Completed Dao Call For save The UserData : ");
        return this.modelMapper.map(save,UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userid) {
        log.info("Entering Dao Call For Update The UserData : {}", userid);
        User user = this.userRepo.findById(userid).orElseThrow(() -> new ResourceNotFoundException(AppConstants.NOT_FOUND+userid));
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        user.setImage_name(userDto.getImage_name());
        user.setGender(userDto.getGender());
        User save = this.userRepo.save(user);
        UserDto userDto1 = this.UserToDto(user);
        log.info("Completed Dao Call For Update The UserData : {}", userid);
        return userDto1;
    }

    @Override
    public void deleteUser(String userid) {
        log.info("Entering Dao Call For Delete UserData :{}",userid);
        User user = this.userRepo.findById(userid).orElseThrow(() -> new ResourceNotFoundException(AppConstants.NOT_FOUND+userid));
        log.info("Entering Dao Call For Delete  UserData :{}",userid);
        this.userRepo.delete(user);


    }

    @Override
    public PageableResponse<UserDto> getAllUser(int pageNumber,int pageSize,String sortBy, String sortDir) {
        log.info("Entering Dao Call For Get All UserData ");
        Sort sort= ( sortDir.equalsIgnoreCase("desc"))
                ?(Sort.by(sortBy).descending())
                :(Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<User> all = this.userRepo.findAll(pageable);
        PageableResponse<UserDto> response = Helper.getPageableResponse(all, UserDto.class);
        return response;
    }

    @Override
    public UserDto getUserById(String userid) {
        log.info("Entering Dao Call For Get The UserData :{}", userid);
        User user = this.userRepo.findById(userid).orElseThrow(() -> new ResourceNotFoundException(AppConstants.NOT_FOUND+userid));
        log.info("Completed Dao Call For Get The UserData :{}", userid);
        UserDto userDto1 = this.UserToDto(user);
        return userDto1;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        log.info("Entering Dao Call For Get The UserData :{}", email);
        User byEmail = this.userRepo.findByEmail(email);
        log.info("Completed Dao Call For Get The UserData :{}", email);
        UserDto userDto1 = this.UserToDto(byEmail);
        return userDto1;
    }

    @Override
    public List<UserDto> searchUser(String pattern) {
        log.info("Entering Dao Call For Search The UserData :{}", pattern);
        List<User> byUseridContaining = this.userRepo.findByUseridContaining(pattern);
        List<UserDto> collect = byUseridContaining.stream().map(user -> this.UserToDto(user)).collect(Collectors.toList());
        log.info("Completed Dao Call For Search The UserData :{}", pattern);
        return collect;
    }

    public UserDto UserToDto(User user){
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;
    }

    public User DtoToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }

}
