package com.BookDeal.BookDeal.Dto;


import com.BookDeal.BookDeal.Entity.Users;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String userId;
    private String userPw;
//    private String name;
//    private String email;
//    private Date join_date;

    public Users toEntity() {
        return new Users(id, userId, userPw);

    }
}
