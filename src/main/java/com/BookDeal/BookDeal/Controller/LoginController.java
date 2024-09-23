package com.BookDeal.BookDeal.Controller;

import com.BookDeal.BookDeal.Dto.UserDTO;
import com.BookDeal.BookDeal.Entity.Users;
import com.BookDeal.BookDeal.Service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    LoginService loginService;
//    @GetMapping("/login")
//    public S longin(){
//        return "login";
//    }
    @GetMapping("/signup")
    public ResponseEntity<String> signup() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/signup")
    public ResponseEntity<Users> createId(@RequestBody UserDTO userDTO, BindingResult bindingResult){

        log.info("trying signup");
        Users created = loginService.signUp(userDTO);

        return (created!=null)?(ResponseEntity.status(HttpStatus.OK).body(created)) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
