package com.example.firstproject.dto.post;

import com.example.firstproject.entity.Post;
import lombok.*;

import java.time.LocalDateTime;

//@NoArgsConstructor
//@RequiredArgsConstructor // final 필드에 대한 생성자 추가
@AllArgsConstructor //모든 필드에 대한 생성자 추가
@Builder //빌트 패턴 사용
@Getter
@ToString
public class PostForm {
    private final long postId;
    private final String title;
    private final String content;
    private final String editPermission;
    private final String password;
    private final String regUserId;
    private final LocalDateTime regDate;
    private final String editUserId;
    private final LocalDateTime editDate;

    //Entity로 변환
    public Post toEntity() {
        return Post.builder()
                .postId(postId == 0? null:postId)
                .title(title)
                .content(content)
                .editPermission(editPermission)
                .password(password)
                .regUserId(regUserId)
                .regDate(regDate)
                .editUserId(editUserId)
                .editDate(editDate)
                .build();

        //return new Post(null, title, content, editPermission, password,regUserId, regDate, editUserId, editDate);
    }
}
