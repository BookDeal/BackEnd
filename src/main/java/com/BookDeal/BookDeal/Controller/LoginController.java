package com.BookDeal.BookDeal.Controller;

import com.BookDeal.BookDeal.Dto.UserDTO;
import com.BookDeal.BookDeal.Entity.Users;
import com.BookDeal.BookDeal.JwtTokenProvider;
import com.BookDeal.BookDeal.Service.SignUpService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    SignUpService loginService;
//    @GetMapping("/login")
//    public S longin(){
//        return "login";
//    }
    @GetMapping("/signup")
    public ResponseEntity<String> signup() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PostMapping("/signup")
    public ResponseEntity<?> createId(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        log.info("Trying signup");
        log.info(String.valueOf(userDTO.getUsername().length()));
        // 유효성 검사 결과 처리
        if (bindingResult.hasErrors()) {
            // 유효성 검사 오류가 있을 경우, 오류 메시지를 반환
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        try {
            Users created = loginService.signUp(userDTO);
            return (created != null) ?
                    ResponseEntity.status(HttpStatus.OK).body(created) :
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            log.error("Error during signup", e);
            // 서비스에서 발생한 예외에 대한 적절한 응답 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("가입 처리 중 오류가 발생했습니다.");
        }
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider; // JWT 토큰을 생성하기 위한 클래스

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO loginDTO, HttpServletResponse response) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
            );

            // 인증에 성공하면 JWT 토큰 생성
            String token = jwtTokenProvider.createToken(authentication);
            ResponseCookie cookie = ResponseCookie.from("jwt", token)
                    .httpOnly(false)
                    .secure(false) // HTTPS에서만 사용
                    .path("/")
                    .maxAge(3600) // 쿠키 유효 기간 설정 (1시간)
                    .build();
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
            return ResponseEntity.ok("로그인 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 인증 실패
        }

}

    @GetMapping("/info")
    public ResponseEntity<String> getUserInfo(HttpServletRequest request) {
        String token = null;

        // 쿠키에서 token 값을 가져오기
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("jwt".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        // 토큰이 없거나 유효하지 않은 경우 처리
        if (token == null || !jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(401).body("Unauthorized"); // 인증 실패 처리
        }

        // 토큰에서 사용자 이름 가져오기
        Claims claims = Jwts.parser()
                .setSigningKey(jwtTokenProvider.getSecretKey()) // 비밀 키 설정
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject(); // 사용자 이름 가져오기

        return ResponseEntity.ok(username); // 사용자 이름 반환
    }

}
