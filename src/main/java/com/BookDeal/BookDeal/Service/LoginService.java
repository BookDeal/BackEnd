package com.BookDeal.BookDeal.Service;

import com.BookDeal.BookDeal.Dto.UserDTO;
import com.BookDeal.BookDeal.Entity.Users;
import com.BookDeal.BookDeal.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users signUp(UserDTO userDTO) {
        Users user =userDTO.toEntity();
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        if(user.getId()!=null)
            return null;
        log.info("사용자 {}가 데이터베이스에 저장되었습니다.", user.getPassword());
        return userRepository.save(user);
    }
}
