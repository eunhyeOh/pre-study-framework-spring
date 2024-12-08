package com.example.firstproject.dto.auth;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class SignForm {

    private String userId;
    private String password;
}
