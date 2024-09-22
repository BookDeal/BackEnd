package com.BookDeal.BookDeal.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@Entity
@AllArgsConstructor
@Getter
@ToString
public class Users {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)//자동으로 id를 생성해주는 어노테이션
        private Long id;
        @Column(name = "username")
        private String userId;

        @Column(name = "password")
        private String userPw;



//        public void patch(Article article) {
//            if(article.title!=null){
//                this.title=article.title;
//            }
//            if(article.content!=null)
//                this.content= article.content;
        }


