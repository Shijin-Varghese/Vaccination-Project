package com.example.Dosify.Controller;


import com.example.Dosify.dto.RequestDTO.UserRequestDto;
import com.example.Dosify.dto.ResponseDTO.FindUserbyEmailResponseDto;
import com.example.Dosify.dto.ResponseDTO.UserResponseDto;
import com.example.Dosify.model.User;
import com.example.Dosify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody UserRequestDto userRequestDto){

        UserResponseDto userResponseDto = userService.addUser(userRequestDto);
        return new ResponseEntity(userResponseDto, HttpStatus.CREATED);
    }
    @GetMapping("/getUser")
    public ResponseEntity findUser(@RequestParam String email){
       try{
           FindUserbyEmailResponseDto user=userService.findUser(email);
           return new ResponseEntity<>(user, HttpStatus.FOUND);
       }
       catch(Exception e){
           return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    // update the name of the user mobno
     @PutMapping("/updatemob")
     public ResponseEntity updateMob(@RequestParam String email,@RequestParam String mob){
        try{
           userService.updateMob(email,mob);
           return new ResponseEntity<>("Mobile no updated",HttpStatus.OK);
        }
        catch(Exception e){
           return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }
     }
    // all the users who have not taken even a single dose
     @GetMapping("/userwithnodosetaken")
     public ResponseEntity userwithnodosetaken(){

        try{
            List<User>users= userService.userwithnodosetaken();
            return new ResponseEntity(users,HttpStatus.FOUND);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
         }
     }
    // all users who have taken does1 but not dose2
    @GetMapping("/userwithnodose2taken")
    public ResponseEntity userwithnodose2taken(){

        try{
            List<User>users= userService.userwithnodose2taken();
            return new ResponseEntity(users,HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    // all users who are fully vaccinated.
    @GetMapping("/userwithalldosetaken")
    public ResponseEntity userwithalldosetaken(){

        try{
            List<User>users=userService.userwithalldosetaken();
            return new ResponseEntity(users,HttpStatus.FOUND);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
