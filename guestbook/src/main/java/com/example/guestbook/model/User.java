package com.example.guestbook.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class User {

    private String id;
    private String username;
    private String pwd; // TODO Guestbook password 필드 제거 후 이름 변경

}
