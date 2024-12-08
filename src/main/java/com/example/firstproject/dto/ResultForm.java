package com.example.firstproject.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class ResultForm {

    private String msg;

    private Integer statusCode;

}
