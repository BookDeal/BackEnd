package com.BookDeal.BookDeal.Service;

import com.BookDeal.BookDeal.Dto.UserDTO;
import com.BookDeal.BookDeal.Entity.Users;
import com.BookDeal.BookDeal.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class LoginService {
    @Autowired
    UserRepository userRepository;
    public Users create(UserDTO userDTO) {
        Users user =userDTO.toEntity();
        if(user.getId()!=null)
            return null;
        log.info("사용자 {}가 데이터베이스에 저장되었습니다.", user.getUserId());
        return userRepository.save(user);
    }
}
