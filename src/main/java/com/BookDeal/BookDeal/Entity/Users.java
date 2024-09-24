package com.BookDeal.BookDeal.Entity;

import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@Entity
@AllArgsConstructor
@Getter
@ToString
@Setter
public class Users {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)//자동으로 id를 생성해주는 어노테이션
        private Long id;
        @Column(name = "username")
        private String username;

        @Column(name = "password")
        private String password;


        }


