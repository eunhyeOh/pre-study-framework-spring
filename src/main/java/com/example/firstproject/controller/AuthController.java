package com.example.firstproject.controller;

import com.example.firstproject.dto.ResultForm;
import com.example.firstproject.dto.auth.SignForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @PostMapping("/auth/signup")
    public ResultForm Signup(@RequestBody SignForm form){
        //userId가 등록되어 있는지 확인 후에 조건에 따라 user등록 후 result 반환
        ResultForm result = new ResultForm();
        result.setMsg("회원가입 성공");
        result.setStatusCode(200);

        return result;
    }

    @PostMapping("/auth/login")
    public ResultForm Login(@RequestBody SignForm form){
        //파라미터 데이터로 user테이블 조회후 데이터 여부에 따라 result 반환
        //있다면 인증 데이터 생성(아직 구현전!)
        ResultForm result = new ResultForm();
        result.setMsg("로그인 성공");
        result.setStatusCode(200);

        return result;
    }
}
