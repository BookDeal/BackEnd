package com.BookDeal.BookDeal.Dto;


import com.BookDeal.BookDeal.Entity.Users;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class UserDTO {

    private Long id;

    @Size(min = 3, max = 25)
    @NotEmpty(message = "사용자ID는 필수항목입니다.")
    private String username;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password;

    public Users toEntity() {
        return new Users(id, username, password);

    }
}
