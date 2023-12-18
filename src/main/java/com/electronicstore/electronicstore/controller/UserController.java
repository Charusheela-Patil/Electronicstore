package com.electronicstore.electronicstore.controller;

import com.electronicstore.electronicstore.constants.AppConstants;
import com.electronicstore.electronicstore.dto.PageableResponse;
import com.electronicstore.electronicstore.dto.UserDto;
import com.electronicstore.electronicstore.entity.User;
import com.electronicstore.electronicstore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * @author CharushilaPatil
     * @apiNote To create a User Data
     * @param userDto
     * @return user
     * @since 1.0v
     */
    @PostMapping("/create_user")
    public ResponseEntity<UserDto>  create_User(@Valid @RequestBody UserDto userDto) {
        log.info("Entering the request for save User data");
        UserDto user1 = this.userService.createUser(userDto);
        log.info("Completed request for save user data");
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    /**
     * @author charushilaPatil
     * @apiNote Update user data in database
     * @param userDto
     * @param userid
     * @return
     * @since 1.0v
     * */

    @PostMapping("/update/{userid}")
    public ResponseEntity<UserDto> updateuser(@Valid @RequestBody UserDto userDto,@PathVariable String userid){
        log.info("Entering the request for update UserData :{}", userid);
        UserDto userDto1 = this.userService.updateUser(userDto, userid);
        log.info("Completed the request for update UserData :{}", userid);
        return new ResponseEntity<>(userDto1,HttpStatus.CREATED);
    }

    /**
     *@author CharushilaPatil
     *@apiNote Get All Users
     *@return
     *@since 1.0v
     **/

    @GetMapping("/getalluser")
    public ResponseEntity<PageableResponse<UserDto>>getalluser(
            @RequestParam (value = "pageNumber" , defaultValue = AppConstants.PAGE_NUMBER,required = false) int pageNumber,
            @RequestParam (value = "pageSize",defaultValue = AppConstants.PAGE_SIZE) int pageSize,
            @RequestParam(value="sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR, required = false)String sortDir){
            log.info("Entering the request for GetAllUsers");
            PageableResponse<UserDto> allUser = this.userService.getAllUser(pageNumber,pageSize,sortBy,sortDir);
           log.info("Completed the request for GetAllUsers");
            return new ResponseEntity<>(allUser,HttpStatus.OK);
    }

    /**
     * @author CharushilaPatil
     * @apiNote Get a single user By userid
     * @param userid
     * @return
     * @since 1.0v
     * */

    @GetMapping("/getuser/{userid}")
    public ResponseEntity<UserDto> getuser(@PathVariable String userid){
        log.info("Entering the request for get UserData :{}", userid);
        UserDto userById = this.userService.getUserById(userid);
        log.info("Completed the request for get UserData :{}", userid);
        return new ResponseEntity<>(userById,HttpStatus.OK);
    }

    /**
     * @author Charushilapatil
     * @apiNote Delete a user by Userid
     * @param userid
     * @return
     * @since 1.0v
     * */
    @DeleteMapping("/userdeleted/{userid}")
    public ResponseEntity<String> deleteuser(@PathVariable String userid){
        log.info("Entering the request for delete the UserData :{}", userid);
        this.userService.deleteUser(userid);
        log.info("Completed the request for delete the UserData :{}", userid);
        return new ResponseEntity<>(AppConstants.DELETED_SUCCESSFULLY,HttpStatus.OK);
    }

    /**
     * @author CharushilaPatil
     * @apiNote Get a single user by email
     * @param email
     * @return
     * @since 1.0v
     * */

    @GetMapping("/getuserbyemail/{email}")
    public ResponseEntity<UserDto> getuserbyemail(@PathVariable String email){
        log.info("Entering the request for get the UserData :{}", email);
        UserDto userByEmail = this.userService.getUserByEmail(email);
        log.info("Completed the request for get the UserData :{}", email);
        return new ResponseEntity<>(userByEmail,HttpStatus.OK);
    }

    /**
     * @author CharushilaPatil
     * @apiNote Search User by containing any letter
     * @param pattern
     * @return
     * @since 1.0v
     * */
    @GetMapping("/searchuser/{pattern}")
    public ResponseEntity<List<UserDto>> searchuser(@PathVariable String pattern){
        log.info("Entering The Request For Search The UserData :{}", pattern);
        List<UserDto> userDtos = this.userService.searchUser(pattern);
        log.info("Completed The Request For Search The UserData :{}", pattern);
        return new ResponseEntity<>(userDtos,HttpStatus.OK);
    }


}
